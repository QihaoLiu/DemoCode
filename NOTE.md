# Note

------------------------------------------------------------------------
## ADB

------------------------------------------------------------------------
### 1.获取CPU参数信息

1.adb shell

2.cat proc/cpuinfo

### 2.查询设备是否为64位系统

adb shell "ps | grep zygote 

判断手机是32位还是64位,如果出现zygote64,那么就是64位的系统

### 3.抓取日志

adb logcat -v threadtime -b all > 1.txt

adb logcat -v time -b all > 1.txt

### 4.查询剩余内存

adb shell free

### 5.查看进程内存使用情况

adb shell dumpsys meminfo -a com.lqh.demo

### 6.查看进程前10个内存使用情况

adb shell top -m 10 -t

### 7.查看设备属性

adb shell getprop

### 8.查询线程名

adb shell ps 7664

### 9.查询当前窗口的activity

adb shell dumpsys activity activities


## SHA1

------------------------------------------------------------------------

C:\Android\.android>keytool -list -v -keystore debug.keystore

输入密钥库口令:

C:\Program Files\Java\jdk1.8.0_131\bin>keytool -v -list -keystore C:\Users\User\Desktop\key.jks

输入密钥库口令:

