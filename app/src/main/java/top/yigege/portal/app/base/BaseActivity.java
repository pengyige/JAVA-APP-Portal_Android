package top.yigege.portal.app.base;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.ButterKnife;
import rx.functions.Action1;
import top.yigege.portal.R;
import top.yigege.portal.app.PortalApplication;
import top.yigege.portal.util.SystemBar;
import top.yigege.portal.util.SystemBarTintManager;

/**
 * author: yigege
 * created on: 2019/4/15 16:25
 * description:公共的activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**系统状态烂管理*/
    SystemBarTintManager tintManager;

    /**是否root*/
    protected boolean root;

    /**上下文*/
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tintManager = SystemBar.setSystemBar(this, R.color.colorPrimary);
        //维护所有activity
        PortalApplication.getApp().addActivity(this);





        //设置布局
        setContentView(initContentView());
        ButterKnife.bind(this);
        this.mContext = this;
        Bundle bundle = getIntent().getExtras();



        //初始化参数
        initParms(bundle,savedInstanceState);
        //初始化数据
        initData();
        //初始化监听
        initListener();

        setRoot(true);

        //获取权限
        getAppPermissions();
    }

    /**
     * 初始化布局
     * @return
     */
    protected abstract int initContentView();

    /**
     * 初始化参数
     * @param bundle
     * @param savedInstanceState
     */
    protected abstract void initParms(Bundle bundle, Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected abstract void initListener();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        PortalApplication.getApp().removeActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * 获取App所需权限
     */
    private void getAppPermissions() {
        new RxPermissions(this).request(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WAKE_LOCK,
                Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(
                new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (!aBoolean) {//true表示获取权限成功（注意这里在android6.0以下默认为true）

                        } else {
                            //Log.i("permissions", Manifest.permission.READ_CALENDAR + "：" + 获取失败);
                        }
                    }
                });

    }



    public void setRoot(boolean root) {
        this.root = root;
    }

    public boolean isRoot() {
        return root;
    }
}
