package top.yigege.portal.http;

import android.app.Activity;
import android.graphics.Color;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Subscriber;
import top.yigege.portal.app.PortalApplication;
import top.yigege.portal.util.NetworkUtil;
import top.yigege.portal.util.ToolTipDialogUtils;

/**
 * author: yigege
 * created on: 2019/4/15 19:51
 * description:
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

    /**加载对话框*/
    private SweetAlertDialog loadingProgress;

    /**活动*/
    private Activity activity;

    public BaseSubscriber() {

    }

    public BaseSubscriber(Activity activity) {
        this.activity = activity;
    }


    /**
     * 开始时回调
     */
    @Override
    public void onStart() {
        super.onStart();
        if (!NetworkUtil.isConnect(PortalApplication.getApp())) {
            ToolTipDialogUtils.showToolTip(activity,"当前网络不可用",Toast.LENGTH_LONG);
            return;
        }
        if (activity != null && !activity.isFinishing()) {
            loadingProgress = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
            loadingProgress.getProgressHelper().setBarColor(Color.parseColor("#e85052"));

            loadingProgress.setContentText("正在加载");
            loadingProgress.setCancelable(false);
            loadingProgress.show();

        }


    }

    /**
     * 完成时回调
     */
    @Override
    public void onCompleted() {
        if(loadingProgress!=null && loadingProgress.isShowing()){
            loadingProgress.dismiss();
        }
    }


    /**
     * 错误时回调
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (loadingProgress != null && loadingProgress.isShowing()) {
            loadingProgress.dismiss();
        }

        if (e instanceof HttpError) {
            HttpError he = (HttpError) e;
            switch (he.getCode()) {
                case HttpError.ERROR_OPERATE:
                    ToolTipDialogUtils.showToolTip(PortalApplication.getApp(),"网络错误",Toast.LENGTH_LONG);
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * 是否属于已处理请求异常
     * @param e
     * @return
     */
    public boolean bErrorExcuted(Throwable e) {
        if (e instanceof HttpError) {
            HttpError he = (HttpError) e;
            switch (he.getCode()) {
                case HttpError.ERROR_OPERATE:
                    break;
                default:
                    return false;
            }

            return true;
        }
        return false;
    }
}
