package com.example.demo.test.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * <p> 错误信息 </p>
 *
 * @author Tortoise
 * @since 2018-08-23
 */
@Getter
@Builder
public class ErrorInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 错误代码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 错误
     *
     * @return 错误
     */
    public static ErrorInfo error(ErrorCode errorCode) {
        return ErrorInfo.builder().code(errorCode.getValue()).msg(errorCode.getDesc()).build();
    }

    /**
     * 错误
     *
     * @param code 错误代码
     * @param msg  错误信息
     * @return 错误
     */
    public static ErrorInfo error(Integer code, String msg) {
        return ErrorInfo.builder().code(code).msg(msg).build();
    }

}
