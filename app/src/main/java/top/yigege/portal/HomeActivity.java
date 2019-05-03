package top.yigege.portal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import top.yigege.portal.app.UserManager;
import top.yigege.portal.app.base.BaseActivity;
import top.yigege.portal.data.User;
import top.yigege.portal.ui.login.WelcomeActivity;
import top.yigege.portal.util.NavigationController;
import top.yigege.portal.util.ToolTipDialogUtils;

/**
 * 开屏页面
 */
public class HomeActivity extends BaseActivity {

    @Override
    protected int initContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void initParms(Bundle bundle, Bundle savedInstanceState) {
        //设置状态栏为透明
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断是否有缓存用户
                final User user = UserManager.getUserManager().loadCacheUser();
                if (user != null) {
                    //跳转到页面
                    NavigationController.getInstance().jumpTo(MainActivity.class,null,true);
                }else {

                   NavigationController.getInstance().jumpTo(WelcomeActivity.class,null,true);
                }

            }
        },3000);
    }

    @Override
    protected void initListener() {

    }
}
