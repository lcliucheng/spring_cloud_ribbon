/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.openfeign.ribbon;

import java.io.IOException;
import java.net.URI;

import org.springframework.cloud.netflix.ribbon.SpringClientFactory;

import com.netflix.client.ClientException;
import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;

import feign.Client;
import feign.Request;
import feign.Response;

/**
 * @author Dave Syer
 */
public class LoadBalancerFeignClient implements Client {

    static final Request.Options DEFAULT_OPTIONS = new Request.Options();

    /**
     * mock 开关 0正常服务，1测试服务
     */
    private static int isMock = 0;

    /**
     * 暴露开关设置方法
     */
    public static void setIsMock(int isMockState) {
        isMock = isMockState;
    }

    public static int getIsMock() {
        return isMock;
    }

    private final Client delegate;
    private CachingSpringLoadBalancerFactory lbClientFactory;
    private SpringClientFactory clientFactory;

    public LoadBalancerFeignClient(Client delegate,
                                   CachingSpringLoadBalancerFactory lbClientFactory,
                                   SpringClientFactory clientFactory) {
        this.delegate = delegate;
        this.lbClientFactory = lbClientFactory;
        this.clientFactory = clientFactory;
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        try {
            URI asUri = URI.create(request.url());
            String clientName = asUri.getHost();
            String url = request.url();
            //判断开关为测试服务的时候
            if (isMock == 1) {
                //将正常服务的名字，改为测试服务
                String oldClientName = clientName;
                clientName += "-mock";
                int i = url.indexOf(oldClientName) + oldClientName.length();
                String headerStr = url.substring(0, i);
                String tailStr = url.substring(i, url.length());
                headerStr = headerStr.replaceAll(oldClientName, clientName);
                url = headerStr + tailStr;
            }
            //后面是请求的正常流程，可以保持不动，
            //也可以改变一下请求测略,当isMock为1时走测试服务，当请求为404时，自动切换会正常服务，
            // 保证服务的可靠性，这个暂时未修改
            URI uriWithoutHost = cleanUrl(url, clientName);
            FeignLoadBalancer.RibbonRequest ribbonRequest = new FeignLoadBalancer.RibbonRequest(
                    this.delegate, request, uriWithoutHost);

            IClientConfig requestConfig = getClientConfig(options, clientName);
            return lbClient(clientName).executeWithLoadBalancer(ribbonRequest,
                    requestConfig).toResponse();
        } catch (ClientException e) {
            IOException io = findIOException(e);
            if (io != null) {
                throw io;
            }
            throw new RuntimeException(e);
        }
    }

    IClientConfig getClientConfig(Request.Options options, String clientName) {
        IClientConfig requestConfig;
        if (options == DEFAULT_OPTIONS) {
            requestConfig = this.clientFactory.getClientConfig(clientName);
        } else {
            requestConfig = new FeignOptionsClientConfig(options);
        }
        return requestConfig;
    }

    protected IOException findIOException(Throwable t) {
        if (t == null) {
            return null;
        }
        if (t instanceof IOException) {
            return (IOException) t;
        }
        return findIOException(t.getCause());
    }

    public Client getDelegate() {
        return this.delegate;
    }

    static URI cleanUrl(String originalUrl, String host) {
        String newUrl = originalUrl.replaceFirst(host, "");
        StringBuffer buffer = new StringBuffer(newUrl);
        if ((newUrl.startsWith("https://") && newUrl.length() == 8) ||
                (newUrl.startsWith("http://") && newUrl.length() == 7)) {
            buffer.append("/");
        }
        return URI.create(buffer.toString());
    }

    private FeignLoadBalancer lbClient(String clientName) {
        return this.lbClientFactory.create(clientName);
    }

    static class FeignOptionsClientConfig extends DefaultClientConfigImpl {

        public FeignOptionsClientConfig(Request.Options options) {
            setProperty(CommonClientConfigKey.ConnectTimeout,
                    options.connectTimeoutMillis());
            setProperty(CommonClientConfigKey.ReadTimeout, options.readTimeoutMillis());
        }

        @Override
        public void loadProperties(String clientName) {

        }

        @Override
        public void loadDefaultValues() {

        }

    }
}
