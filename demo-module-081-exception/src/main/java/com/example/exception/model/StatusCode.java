package com.example.exception.model;

/**
 * @author William
 * @date 2020/9/7 11:36 AM
 * @description 状态码及对应的信息
 */
public enum StatusCode {
    SUCCESS(0,"Success"),
    FAIL(-1,"Fail"),
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
    SYSTEM_ERROR(-1, "System Error, Please contact administrator.")
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

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
