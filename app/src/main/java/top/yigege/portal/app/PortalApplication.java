package top.yigege.portal.app;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import top.yigege.portal.util.NavigationController;


/**
 * author: yigege
 * created on: 2019/4/15 15:56
 * description:
 */
public class PortalApplication extends MultiDexApplication {

    /**单例模式 */
    private static PortalApplication app;



    /**维护所有activity*/
    private List<Activity> activityList = new LinkedList<>();

    /**缓存全局数据*/
    private Map<String,Object> map = new HashMap<>();

    /**全局上下文*/
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        mContext = getApplicationContext();
        /*activity加入生命周期控制*/
        registerActivityLifecycleCallbacks(NavigationController.getInstance());


    }

    /**
     * 得到application
     * @return
     */
    public static PortalApplication getApp() {
        return app;
    }

    /**
     * 得到上下文
     * @return
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * 得到所有activity,NavagationController也有维护
     * @return
     */
    public List<Activity> getActivityList() {
        return activityList;
    }

    /**
     * 返回维护的全局缓存值
     * @return
     */
    public Map<String, Object> getMap() {
        return map;
    }

    /**
     * 缓存数据
     * @param key
     * @param value
     */
    public  void put(String key, Object value){
        map.put(key, value);
    }

    /**
     *
     * @param key
     * @param <T>
     * @return
     */
    public  <T> T get(String key){
        T serializableData = null;
        if(map.containsKey(key)){
            serializableData = (T)map.get(key);
        }
        return serializableData;
    }

    /**
     * 清空
     */
    public  void clear(){
        map.clear();
    }


    /**
     * 添加activity
     * @param activity
     */
    public void addActivity(Activity activity)
    {
        activityList.add(activity);
    }

    /**
     * 移除activity
     * @param activity
     */
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 销毁所有activity
     */
    public void exit()
    {
        try {
            for (Activity activity:activityList)
            {
                if (activity != null) {
                    activity.finish(); //所有的活动是在这里被销毁的
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
