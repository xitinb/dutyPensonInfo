package cn.gzsendi.huangyj.util;

/**
 * 响应码
 * @author yangziqun
 * @version 1.0
 * @date 2019/10/16 17:54
 */
public enum ResponseCode {
    /** 服务器错误 */
    ERROR(0, "服务器错误"),
    /** 成功状态码 */
    SUCCESS(1, "成功"),
    /** 参数错误: 1001-1999 */
    PARAM_IS_INVALID(1001, "参数无效"),
    /** 用户错误: 2001-2999 */
    USER_NOT_LOGGED_IN(2001, "用户未登录"),
    USER_LOGIN_ERROR(2002, "账号或密码错误"),
    CAPTCHA_ERROR(2003, "验证码错误"),
    IP_HAS_BEEN_LOCKED(2004, "IP已被锁定!"),
    ACCOUNT_HAS_BEEN_LOCKED(2005, "账号已被锁定"),
    BIND_IP_ERROR(2006, "请求IP地址与绑定IP地址不同"),
    PASSWORD_EXPIRED(2007, "密码过期"),
    NO_DATA_FOUND(3001, "查询不到数据");

    private Integer code;
    private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

}
