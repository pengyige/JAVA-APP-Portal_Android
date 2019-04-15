package top.yigege.portal.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import top.yigege.portal.app.PortalApplication;
import top.yigege.portal.util.ToolTipDialogUtils;

/**
 * author: yigege
 * created on: 2019/4/15 20:59
 * description:
 */
public class LocationHelper implements LocationListener {

    private static final String TAG = "LocationHelper";

    /**定位管理*/
    private LocationManager mLocationManager;

    /**监听*/
    private MyLocationListener mListener;

    /**设备上下文*/
    private Context mContext;

    public LocationHelper(Context context) {
        mContext = context.getApplicationContext();
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void initLocation(MyLocationListener listener) {
        mListener = listener;
        Location location;

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mListener.locationError();
            ToolTipDialogUtils.showToolTip(PortalApplication.getContext(),"请打开定位权限",Toast.LENGTH_LONG);
            return;
        }

        //优先使用网络定位
        if (mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                mListener.updateLastLocation(location);
            }else{
                mListener.locationError();
            }
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        }else  if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //GPS定位

            location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                mListener.updateLastLocation(location);
            }else{
                mListener.locationError();
            }
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }else {
            //手机定位没打开
            mListener.locationError();
            ToolTipDialogUtils.showToolTip(PortalApplication.getContext(),"请打开手机定位功能",Toast.LENGTH_LONG);

        }
    }

    /**
     * 移除定位监听
     */
    public void removeLocationUpdatesListener() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(this);
        }
    }


    /**
     * 定位坐标发生改变
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: ");
        if (mListener != null) {
            mListener.updateLocation(location);
        }
    }

    /**
     * 定位服务状态改变
     * @param provider
     * @param status
     * @param extras
     */
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG, "onStatusChanged: ");
        if (mListener != null) {
            mListener.updateStatus(provider, status, extras);
        }
    }

    /**
     * 定位服务开启
     * @param provider
     */
    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, "onProviderEnabled: " + provider);
    }

    /**
     * 定位服务关闭
     * @param provider
     */
    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, "onProviderDisabled: " + provider);
    }

}
