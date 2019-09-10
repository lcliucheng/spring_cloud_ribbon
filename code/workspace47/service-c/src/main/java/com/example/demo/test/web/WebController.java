package com.example.demo.test.web;

import com.example.demo.test.response.Response;
import com.example.demo.test.service.TestFeignA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return testFeignA.feginA(Response.success("GET我去"));
    }

    @GetMapping("/mockTest2")
    public Response<String > diaoyongBiz2() {
        return testFeignA.feginB(Response.success("POST我去"));
    }

    @GetMapping("/setMockState")
    public Response<String > setMockState() {
        if(LoadBalancerFeignClient.getIsMock()==0){
            LoadBalancerFeignClient.setIsMock(1);
        }else{
            LoadBalancerFeignClient.setIsMock(0);
        }

        return Response.success(LoadBalancerFeignClient.getIsMock()+"");
    }





}
