package com.example.demo.test.service;

import com.example.demo.test.response.Response;
import com.example.demo.test.service.impl.TestFeignAImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liu cheng
 * @since 2019-09-09 16:13
 */
@FeignClient(value = "insurance", fallback = TestFeignAImpl.class)
public interface TestFeignA {

    @RequestMapping(value = "/web/mockTest2", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Response<String> feginA(@RequestParam Response<String > response);

    @RequestMapping(value = "/web/mockTest3", method = RequestMethod.POST)
    Response<String> feginB(@RequestBody Response<String > response);
}
