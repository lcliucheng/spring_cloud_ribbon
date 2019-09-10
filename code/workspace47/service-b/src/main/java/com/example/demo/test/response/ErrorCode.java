package com.example.demo.test.response;

import java.util.Objects;

/**
 * 错误代码枚举类
 *
 * @author Tortoise
 * @since 2018-08-23
 */
public enum ErrorCode {

    /* 未授权 */
    UNAUTHORIZED(401, "未授权"),

    /* 权限不足 */
    FORBIDDEN(403, "权限不足"),

    /* 系统错误 */
    SYSTEM_ERROR(-200, "系统错误"),

    /* 服务调用错误 */
    SERVICE_CALL_ERROR(-201, "服务调用错误"),

    /* 非法操作 */
    ILLEGAL_OPERATION(-202, "非法操作"),

    /* 参数格式错误 */
    DATA_VALID_ERROR(-300, "参数格式错误"),

    /* 数据不存在 */
    DATA_NON_EXISTENT(-404, "数据不存在"),

    /* 数据操作异常 */
    DATA_OPERATE_EXCEPTION(-500, "数据操作异常"),

    /* 登录失败 */
    LOGIN_ERROR(-600, "登录失败"),

    /* 凭证已过期 */
    TOKEN_EXPIRED(-601, "凭证已过期"),

    /* 凭证异常 */
    TOKEN_ERROR(-602, "凭证异常"),

    /* 设备用户不存在 */
    DU_NON_EXISTENT(-603, "设备未注册"),

    /* 设备用户已存在 */
    DU_EXISTENT(-604, "设备用户已存在"),

    /* 用户不存在 */
    USER_NON_EXISTENT(-605, "用户不存在"),

    /* 用户被禁用 */
    USER_DISABLED(-606, "用户被禁用"),

    /* 用户名或者密码错误 */
    USERNAME_OR_PASSWORD_ERROR(-607, "用户名或者密码错误"),

    /* 用户已被锁定 */
    USER_LOCKED_ERROR(-608, "用户已被锁定"),

    /* 请先绑定用户 */
    USER_UNBIND_ERROR(-609, "请先绑定用户"),

    /* 用户已绑定 */
    USER_IS_BIND_ERROR(-610, "用户已绑定"),

    /* QQ已绑定 */
    QQ_IS_BIND_ERROR(-611, "QQ已绑定"),

    /* 微信已绑定 */
    WECHAT_IS_BIND_ERROR(-612, "微信已绑定"),

    ;

    private Integer value;
    private String desc;

    ErrorCode(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 获取枚举对象
     *
     * @param value 具体值
     * @return 枚举对象
     */
    public static ErrorCode valueOf(Integer value) {
        Objects.requireNonNull(value, "The matching value cannot be empty");

        for (ErrorCode object : values()) {
            if (value.equals(object.getValue())) {
                return object;
            }
        }

        throw new IllegalArgumentException("No matching constant for [" + value + "]");
    }

    /**
     * 获取枚举对象的具体值
     *
     * @return 具体值
     */
    public Integer getValue() {
        return value;
    }

    /**
     * 获取枚举对象的描述
     *
     * @return 具体值
     */
    public String getDesc() {
        return desc;
    }
}
