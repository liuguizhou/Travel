package com.travel.liuyun.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;

import com.travel.liuyun.TravelApplication;

/**
 * Created by Administrator on 2016/11/21.
 */

public class AppInfo {

    /**
     * 为了方便用户在设备识别，获取SWDID按以下顺序方式进行 1.IMEI 2.WIFI MAC 3.蓝牙MAC 4.Android ID
     * 以上如果都获取不到可能性极小，碰到再去适配
     *
     * @return
     */
    public static String getDeviceUUID() {
        String imei = getDeviceID();
        if (!imei.equals("UNKNOWN")) {
            return imei;
        }
        String wifiMac = getMacAddress();
        if (!wifiMac.equals("UNKNOWN")) {
            return wifiMac;
        }
        String blueToothMac = getBluetoothMACAddress();
        if (!blueToothMac.equals("UNKNOWN")) {
            return blueToothMac;
        }
        return getAndroidID();
    }


    /**
     * Get Device ID (IMEI/MEID/ESN) .The IMEI for GSM and the MEID or ESN for
     * CDMA phones
     *
     * @return
     */
    private static String getDeviceID() {
        TelephonyManager tm = (TelephonyManager) TravelApplication.getInstance()
                .getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = null;
        if (hasPermission(android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.READ_PHONE_STATE)) {
            //有权限，则写你的业务逻辑
            deviceid = tm.getDeviceId();
            Log.e("lgz", "11 ");
        } else {
            //没权限，进行权限请求
//            requestPermission(Constants.CODE_CAMERA, Manifest.permission.CAMERA);
            deviceid = null;
            Log.e("lgz", "123");
        }
        return (deviceid == null ? "UNKNOWN" : deviceid);
    }

    private static boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(TravelApplication.getInstance().getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    /**
     * 在设备恢复出厂设置后，该值可能会改变
     *
     * @return
     */
    public static String getAndroidID() {
        String androidid = Settings.Secure.getString(TravelApplication.getInstance().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return (androidid == null ? "UNKNOWN" : androidid);
    }

    /**
     * Get Mac Address
     *
     * @return
     */
    private static String getMacAddress() {
        WifiManager wifi = (WifiManager) TravelApplication.getInstance()
                .getSystemService(Context.WIFI_SERVICE);
        String macAddress = wifi.getConnectionInfo().getMacAddress();
        return (macAddress == null ? "UNKNOWN" : macAddress);
    }

    /**
     * Get Bluetooth MAC address
     *
     * @return
     */
    private static String getBluetoothMACAddress() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        return (adapter == null ? "UNKNOWN" : adapter.getAddress());
    }


    /**
     * versionName
     *
     * @return
     */
    public static String getAppVersionName() {
        try {
            TravelApplication application = TravelApplication.getInstance();
            String pkName = application.getPackageName();
            String versionName = application.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            return versionName;
        } catch (Exception e) {
        }
        return "1.0";
    }

    /**
     * 获取设备的版本号
     *
     * @return
     */
    public static String getDeviceVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 屏幕分辨率
     *
     * @return
     */
    public static String getScreenSize() {
        DisplayMetrics dm = TravelApplication.getInstance().getResources().getDisplayMetrics();
        return String.valueOf(dm.widthPixels) + '*'
                + String.valueOf(dm.heightPixels);
    }

}
