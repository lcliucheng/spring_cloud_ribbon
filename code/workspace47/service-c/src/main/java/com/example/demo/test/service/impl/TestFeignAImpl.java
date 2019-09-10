package com.example.demo.test.service.impl;

import com.example.demo.test.response.ErrorCode;
import com.example.demo.test.response.Response;
import com.example.demo.test.service.TestFeignA;
import org.springframework.stereotype.Component;

/**
 * @author liu cheng
 * @since 2019-09-09 16:14
 */
@Component
public class TestFeignAImpl implements TestFeignA {

    @Override
    public Response<String> feginA(Response<String> response) {
        return Response.failed(ErrorCode.SERVICE_CALL_ERROR);
    }

    @Override
    public Response<String> feginB(Response<String> response) {
        return Response.failed(ErrorCode.SERVICE_CALL_ERROR);
    }
}
