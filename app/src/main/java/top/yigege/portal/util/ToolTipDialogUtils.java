package top.yigege.portal.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import top.yigege.portal.R;

/**
 * author: yigege
 * created on: 2019/4/15 19:58
 * description:
 */
public class ToolTipDialogUtils {

    /**
     * 提示框
     * @param context 设备上下文
     * @param msg 提示信息
     * @param interval 持续时间
     */
    public static void showToolTip(Context context, String msg, int interval){

        Toast toast = new Toast(context);
        //设置Toast显示位置，居中，向 X、Y轴偏移量均为0
        toast.setGravity(Gravity.CENTER, 0, 0);
        //获取自定义视图
        View view = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_message_toast);
        //设置文本
        tvMessage.setText(msg);
        //设置视图
        toast.setView(view);
        //设置显示时长
        toast.setDuration(interval);
        //显示
        toast.show();
    }
}
