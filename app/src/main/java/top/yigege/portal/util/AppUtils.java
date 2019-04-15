package top.yigege.portal.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.UUID;

import top.yigege.portal.app.PortalApplication;

/**
 * author: yigege
 * created on: 2019/4/15 20:38
 * description:
 */
public class AppUtils {

    private static String ID;
    private static int screenWidth = 0;
    private static int screentHeight = 0;

    /**
     * 得到屏幕宽度
     * @return
     */
    public static int getScreenWidth(){

        if ( 0 == screenWidth) {
            DisplayMetrics dm = new DisplayMetrics();
            dm = PortalApplication.getContext().getResources().getDisplayMetrics();
            screenWidth = dm.widthPixels;
        }
        return screenWidth;
    }

    /**
     * 得到屏幕高度
     * @return
     */
    public static int getScreentHeight(){
        if ( 0 == screentHeight ) {
            DisplayMetrics dm = new DisplayMetrics();
            dm = PortalApplication.getContext().getResources().getDisplayMetrics();
            screentHeight = dm.heightPixels;
        }
        return screentHeight;
    }

    /**
     * 得到设备唯一id
     * @return
     */
    public static String getUniqueID() {
        if (ID == null) {
            ID = getUniquePsuedoID();
        }
        return ID;
    }

    /**
     * 隐藏软键盘
     * @param context
     * @param editText
     */
    public static void hideSoftInputIsShow(Context context, EditText editText) {
        if ((context == null) || (editText == null)) {
            return;
        }
        InputMethodManager input_method = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (input_method.isActive()) {
            input_method.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    /**
     * 打开软键盘
     * @param context
     * @param editText
     */
    public static void showSoftInputIsShow(Context context, EditText editText) {
        if ((context == null) || (editText == null)) {
            return;
        }
        InputMethodManager input_method = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (input_method.isActive()) {
            input_method.showSoftInput(editText, 0);
        }
    }

    private static String getUniquePsuedoID() {
        String serial = null;

        String m_szDevIDShort = "35" + Build.BOARD.length() % 10
                + Build.BRAND.length() % 10 +

                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +

                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +

                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +

                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +

                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +

                Build.USER.length() % 10; // 13 位

        try {
            serial = Build.class.getField("SERIAL").get(null)
                    .toString();
            // API>=9 使用serial号
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode())
                    .toString();
        } catch (Exception exception) {
            // serial需要一个初始化
            serial = "serial"; // 随便一个初始化
        }
        // 使用硬件信息拼凑出来的15位号码
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode())
                .toString();
    }


    public static void requirePermision(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            int readPhone = activity.checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
            int receiveSms = activity.checkSelfPermission(Manifest.permission.RECEIVE_SMS);
            int readSms = activity.checkSelfPermission(Manifest.permission.READ_SMS);
            int readContacts = activity.checkSelfPermission(Manifest.permission.READ_CONTACTS);
            int readSdcard = activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

            int requestCode = 0;
            ArrayList<String> permissions = new ArrayList<String>();
            if (readPhone != PackageManager.PERMISSION_GRANTED) {
                requestCode |= 1 << 0;
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (receiveSms != PackageManager.PERMISSION_GRANTED) {
                requestCode |= 1 << 1;
                permissions.add(Manifest.permission.RECEIVE_SMS);
            }
            if (readSms != PackageManager.PERMISSION_GRANTED) {
                requestCode |= 1 << 2;
                permissions.add(Manifest.permission.READ_SMS);
            }
            if (readContacts != PackageManager.PERMISSION_GRANTED) {
                requestCode |= 1 << 3;
                permissions.add(Manifest.permission.READ_CONTACTS);
            }
            if (readSdcard != PackageManager.PERMISSION_GRANTED) {
                requestCode |= 1 << 4;
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (requestCode > 0) {
                String[] permission = new String[permissions.size()];
                activity.requestPermissions(permissions.toArray(permission), requestCode);
                return;
            }
        }
    }


    /**
     * 得到版本名称
     * @return
     */
    public static String getVersionName() {
        PackageManager pm = PortalApplication.getApp().getPackageManager();//context为当前Activity上下文
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(PortalApplication.getApp().getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 得到版本code
     * @return
     */
    public static int getVersionCode() {
        PackageManager pm = PortalApplication.getApp().getPackageManager();//context为当前Activity上下文
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(PortalApplication.getApp().getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
