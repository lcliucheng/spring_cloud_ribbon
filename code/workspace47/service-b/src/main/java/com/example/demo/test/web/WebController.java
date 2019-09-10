package com.example.demo.test.web;

import com.example.demo.test.response.Response;
import com.example.demo.test.service.TestFeignA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author liu cheng
 * @since 2019-09-09 16:07
 */
@RestController
@RequestMapping("/web")
public class WebController {

    @Autowired
    TestFeignA testFeignA;

    @GetMapping("/mockTest")
    public Response<String > diaoyongBiz() {
        return testFeignA.feginA();
    }

    @RequestMapping(value = "/mockTest2",method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<String> mockTest2(  Response<String> name) {
        return Response.success(name.getMsg()+"我是测试服务");
    }

    @RequestMapping(value = "/mockTest3", method = RequestMethod.POST)
    public Response<String> mockTest3(@RequestBody  Response<String> name) {
        return Response.success(name.getMsg()+"我是测试服务");
    }


}
