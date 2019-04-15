package top.yigege.portal.util;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

/**
 * author: yigege
 * created on: 2019/4/15 16:05
 * description:
 */
public class NavigationController implements Application.ActivityLifecycleCallbacks {

    /**维护所有activity*/
    private static final ArrayList<Activity> activities = new ArrayList<>();

    /**单例模式*/
    private static class SingletonHolder {
        private static final NavigationController INSTANCE = new NavigationController();
    }

    /***
     * 私有化构造
     */
    private NavigationController() {

    }

    /**
     * 返回实例
     * @return
     */
    public static NavigationController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 得到当前activity
     * @return
     */
    public Activity getCurrentActivity() {
        Activity activity = null;
        if (!activities.isEmpty()) {
            activity = activities.get(activities.size() - 1);
        }
        return activity;
    }

    /**
     * 打开Activity
     * @param activityClass
     * @param params
     */
    public void jumpTo(Class activityClass, Map<String,Object> params) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            String valueObj = null;
            Intent intent = new Intent(currentActivity, activityClass);
            if (params != null) {
                for (String key : params.keySet()) {
                    valueObj = params.get(key) == null ? "" : String.valueOf(params.get(key));
                    if (!TextUtils.isEmpty(valueObj)) {
                        intent.putExtra(key, valueObj);
                    }
                }
            }
            currentActivity.startActivity(intent);
        }
    }

    /**
     * 带返回值跳转
     * @param activityClass
     * @param bundle
     * @param requestCode
     */
    public void jumpToForResult(Class activityClass, Bundle bundle, int requestCode) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            Intent intent = new Intent(currentActivity, activityClass);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            currentActivity.startActivityForResult(intent,requestCode);
        }
    }

    /**
     * 跳转后是否关闭本活动
     * @param activityClass
     * @param bundle
     * @param isFinish
     */
    public void jumpTo(Class activityClass, Bundle bundle, boolean isFinish) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            Intent intent = new Intent(currentActivity, activityClass);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            currentActivity.startActivity(intent);
            if (isFinish) {
                currentActivity.finish();
            }
        }
    }
    /**
     * 清理所有activity
     */
    public void clear() {
        for (int i = activities.size() - 1; i >= 0; i--) {
            activities.get(i).finish();
            activities.remove(i);
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        Log.i(NavigationController.class.toString(),"onActivityCreated");
        activities.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.i(NavigationController.class.toString(),"onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.i(NavigationController.class.toString(),"onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.i(NavigationController.class.toString(),"onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.i(NavigationController.class.toString(),"onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Log.i(NavigationController.class.toString(),"onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.i(NavigationController.class.toString(),"onActivityDestroyed");
        activities.remove(activity);
    }
}
