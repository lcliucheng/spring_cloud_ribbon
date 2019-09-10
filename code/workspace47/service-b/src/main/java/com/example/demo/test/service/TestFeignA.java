package com.example.demo.test.service;

import com.example.demo.test.response.Response;
import com.example.demo.test.service.impl.TestFeignAImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author liu cheng
 * @since 2019-09-09 16:13
 */
@FeignClient(value = "insurance",fallback = TestFeignAImpl.class)
public interface TestFeignA {

    @RequestMapping(value = "/admin/feginA", method = RequestMethod.GET)
    Response<String> feginA();
}
