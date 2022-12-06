package com.lqh.base.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class DeviceUtils {

    public static String getImei(Context context, int slotID) {
        String imei = "";
        try {
            TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Class<?> telephonyClass = Class.forName(telephony.getClass().getName());
            Class<?>[] parameter = new Class[1];
            parameter[0] = int.class;
            Method getSimID = telephonyClass.getMethod("getImei", parameter);
            Object[] obParameter = new Object[1];
            obParameter[0] = slotID;
            Object ob_phone = getSimID.invoke(telephony, obParameter);
            if (ob_phone != null) {
                imei = ob_phone.toString();
            }
            if (imei.length() < 15) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    imei = telephony.getImei(0);
                    if (imei == null || imei.length() < 15) {
                        imei = telephony.getDeviceId();
                    }
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    imei = telephony.getDeviceId(0);
                    if (imei == null || imei.length() < 15) {
                        imei = telephony.getDeviceId();
                    }
                } else {
                    imei = telephony.getDeviceId();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imei;
    }

    public static String getMacByWifi(Context context) {
        String wifiMac = "";
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
                Iterator var12 = all.iterator();
                while (var12.hasNext()) {
                    NetworkInterface nif = (NetworkInterface) var12.next();
                    if (nif.getName().equalsIgnoreCase("wlan0")) {
                        byte[] macBytes = nif.getHardwareAddress();
                        if (macBytes != null) {
                            StringBuilder res1 = new StringBuilder();
                            byte[] var6 = macBytes;
                            int var7 = macBytes.length;
                            for (int var8 = 0; var8 < var7; ++var8) {
                                byte b = var6[var8];
                                res1.append(String.format("%02X:", new Object[]{Byte.valueOf(b)}));
                            }
                            if (res1.length() > 0) {
                                res1.deleteCharAt(res1.length() - 1);
                            }
                            wifiMac = res1.toString().toLowerCase();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                if (wifi != null) {
                    if (!wifi.isWifiEnabled()) {
                        wifi.setWifiEnabled(true);
                        wifi.setWifiEnabled(false);
                    }
                    WifiInfo info = wifi.getConnectionInfo();
                    if (info == null) {
                        wifiMac = info.getMacAddress();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return wifiMac;
    }

    public static String getMacByEth0() {
        String macSerial = null;
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/eth0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (!TextUtils.isEmpty(macSerial)) {
            macSerial = macSerial.toUpperCase();
        }
        return macSerial;
    }

    public static String getIP(final boolean useIPv4) {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (!ni.isUp()) continue;
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress inetAddress = addresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        boolean isIPv4 = hostAddress.indexOf(':') < 0;
                        if (useIPv4) {
                            if (isIPv4) return hostAddress;
                        } else {
                            if (!isIPv4) {
                                int index = hostAddress.indexOf('%');
                                return index < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, index).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

}
