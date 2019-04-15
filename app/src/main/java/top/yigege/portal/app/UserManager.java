package top.yigege.portal.app;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;

import top.yigege.portal.data.User;

/**
 * author: yigege
 * created on: 2019/4/15 20:43
 * description:用户管理类
 */
public class UserManager {

    private static UserManager userManager = new UserManager();

    /**登入的用户*/
    private User loginUser;

    /**
     * 返回用户管理
     * @return
     */
    public static UserManager getUserManager() {
        return userManager;
    }

    /**
     * 返回登入的用户
     * @return
     */
    public User getLoginUser() {
        return loginUser;
    }

    /**
     * 用户是否已登入
     * @return
     */
    public boolean isLogin() {
        return loginUser != null;
    }

    /**
     * 缓存用户到本地
     * @param user
     */
    public void cacheUser(User user) {
        Gson gson = new Gson();
        String user_json = gson.toJson(user);
        this.loginUser = user;
        PortalApplication.getApp().getSharedPreferences(User.class.getSimpleName(), Context.MODE_PRIVATE).edit().putString("login_user", user_json).commit();

    }

    /**
     * 加载缓存用户
     * @return
     */
    public User loadCacheUser() {
        Gson gson = new Gson();
        String user_json= PortalApplication.getApp().getSharedPreferences(User.class.getSimpleName(), Context.MODE_PRIVATE).getString("login_user",null);
        if(TextUtils.isEmpty(user_json)){
            return null;
        }
        //pengyi add 加载缓存时也需要赋值给loginUser
        this.loginUser =  gson.fromJson(user_json,User.class);

        return loginUser;
    }

    /**
     * 清空缓存user
     */
    public void clearCacheUser(){

        PortalApplication.getApp().getSharedPreferences(User.class.getSimpleName(), Context.MODE_PRIVATE).edit().clear().commit();

    }
}
