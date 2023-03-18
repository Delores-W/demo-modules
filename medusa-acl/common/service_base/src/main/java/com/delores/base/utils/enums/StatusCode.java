package com.delores.base.utils.enums;

/**
 * @author William
 * @date 2019-10-10 22:37
 * @description 统一的状态码定义
 */
public enum StatusCode {

    SUCCESS(0,"成功"),
    FAIL(-1,"失败"),
    SYSTEM_ERROR(-100, "System Error, Please contract administrator."),
    INVALID_PARAMS(201,"非法的参数!"),
    UserNamePasswordNotBlank(50000,"账户密码不能为空!"),
    AccessTokenNotBlank(50001,"accessToken必填，请在请求头header中塞入该字段"),

    TokenValidateExpireToken(60001,"Token已过期"),
    TokenValidateCheckFail(60002,"Token验证失败"),

    AccessTokenNotExist(70001,"Token不存在-请重新登录!"),
    AccessTokenInvalidate(70002,"无效的Token!"),

    AccessTokenNotExistRedis(80001,"Token不存在或已经过期-请重新登录!"),

    AccessSessionNotExist(90001,"用户没登录或登录Session已经过期-请重新登录!"),

    LoginFail(100000,"登录失败！"),
    CurrUserHasNotPermission(100001,"当前用户没有权限访问该资源或者操作！"),
    CurrUserNotLogin(100002,"当前用户没有登录，请先进行登录！"),
    UserAlreadyExists(500002, "用户已经存在"),
    UserNotExists(500003, "用户不存在"),
    UsernamePasswordNotMatch(50004, "用户名密码不匹配")
    ;

    private Integer code;
    private String msg;


    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
