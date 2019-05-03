package top.yigege.portal.app;

/**
 * author: yigege
 * created on: 2019/4/15 20:58
 * description:
 */
public class GlobalConfig {

    private static final String SERVER_ROOT = "http://192.168.1.11:8080/portal/";

    /**获取注册验证码*/
    public static final String REGISTER_CODE = SERVER_ROOT+"sms_portal_app_getRegisterCode.action";

    /**注册用户*/
    public static final String REGISTER_USER = SERVER_ROOT+"user_register.action";
}
