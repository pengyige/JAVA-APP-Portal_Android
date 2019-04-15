package top.yigege.portal.location;

import android.location.GpsStatus;
import android.location.Location;
import android.os.Bundle;

/**
 * author: yigege
 * created on: 2019/4/15 21:00
 * description:
 */
public interface MyLocationListener {

    /**
     * 最新的定位
     * @param location
     */
    void updateLastLocation(Location location);

    /**
     * 位置信息发生改变
     * @param location
     */
    void updateLocation(Location location);

    /**
     * 位置状态发生改变
     * @param provider
     * @param status
     * @param extras
     */
    void updateStatus(String provider, int status, Bundle extras);

    /**
     * GPS状态发生改变
     * @param gpsStatus
     */
    void updateGpsStatus(GpsStatus gpsStatus);

    /**
     * 定位错误
     */
    void locationError();
}
