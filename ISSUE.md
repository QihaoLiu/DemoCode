# Issue

------------------------------------------------------------------------
## issue

------------------------------------------------------------------------
### 1.TimeoutException

04-15 07:53:37.480 +0000 22201 22210 E CrashHandler: java.util.concurrent.TimeoutException: android.view.ThreadedRenderer.finalize() timed out after 10 seconds

04-15 07:53:37.480 +0000 22201 22210 E CrashHandler: 	at android.view.ThreadedRenderer.nDeleteProxy(Native Method)

04-15 07:53:37.480 +0000 22201 22210 E CrashHandler: 	at android.view.ThreadedRenderer.finalize(ThreadedRenderer.java:893)

04-15 07:53:37.480 +0000 22201 22210 E CrashHandler: 	at java.lang.Daemons$FinalizerDaemon.doFinalize(Daemons.java:222)

04-15 07:53:37.480 +0000 22201 22210 E CrashHandler: 	at java.lang.Daemons$FinalizerDaemon.run(Daemons.java:209)

04-15 07:53:37.480 +0000 22201 22210 E CrashHandler: 	at java.lang.Thread.run(Thread.java:761)

一般是系统在gc时，调用对象的finalize超时导致，解决办法：

1.检查分析finalize的实现为什么耗时较高，修复它。

2.检查日志查看GC是否过于频繁，导致超时，减少内容开销，防止内存泄露。

解决方法1：

webview参数错误导致

if (Build.VERSION.SDK_INT >= 19){

webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    
}

解决方法2：

最简单的解决方法是在清单文件中添加hardwareAccelerated（控制硬件加速）属性

android:hardwareAccelerated="false"

https://blog.csdn.net/luoshengyang/article/details/42555483

解决方法3：

检查是否是自定义控件里面过多操作导致

### 1.can't deliver broadcast

05-31 22:57:08.456 23513 23513 E AndroidRuntime: android.app.RemoteServiceException: can't deliver broadcast

05-31 22:57:08.456 23513 23513 E AndroidRuntime: 	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1651)

05-31 22:57:08.456 23513 23513 E AndroidRuntime: 	at android.os.Handler.dispatchMessage(Handler.java:102)

05-31 22:57:08.456 23513 23513 E AndroidRuntime: 	at android.os.Looper.loop(Looper.java:154)

05-31 22:57:08.456 23513 23513 E AndroidRuntime: 	at android.app.ActivityThread.main(ActivityThread.java:6121)

05-31 22:57:08.456 23513 23513 E AndroidRuntime: 	at java.lang.reflect.Method.invoke(Native Method)

05-31 22:57:08.456 23513 23513 E AndroidRuntime: 	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:889)

05-31 22:57:08.456 23513 23513 E AndroidRuntime: 	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:779)

解决方法：

使用应用内的广播发送，而且比全局广播更具优势:

 1).广播只会在你的应用内发送，所以无需担心数据泄露，更加安全。
 
 2).其他应用无法发广播给你的应用，所以也不用担心你的应用有别人可以利用的安全漏洞。
 
 3).相比较全局广播，它不需要发送给整个系统，所以更加高效。
 
LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("custom-event-name"));

LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);

LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

注册广播只能通过代码注册的方式，注册广播后，一定要记得取消监听。

发送广播的时候务必使用LocalBroadcastManager.sendBroadcast(intent)，否则您接收不到广播。

## record

------------------------------------------------------------------------
### 1.home和launcher启动的intent

04-02 14:54:00.163 14313-14313/com.lqh.demo D/leo_test: Intent { act=androd.intent.action.MAIN cat=[android.intent.category.HOME] flg=0x10200000 cmp=com.lqh.demo/com.lqh.demo.activity.WelcomeActivity (has extras) }

04-02 14:54:00.163 14313-14313/com.lqh.demo D/leo_test: Bundle[{android.intent.extra.FROM_HOME_KEY=true}]

04-03 10:50:09.550 25000-25000/com.lqh.demo D/leo_test: Intent { act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER] flg=0x10200000 cmp=com.lqh.demo/com.lqh.demo.activity.WelcomeActivity bnds=[6,138][163,304] (has extras) }

04-03 10:50:09.550 25000-25000/com.lqh.demo D/leo_test: Bundle[mParcelledData.dataSize=36]

### 2.坐标转换

WGS84：一种大地坐标系，也是目前广泛使用的GPS全球卫星定位系统使用的坐标系。

GCJ02：由中国国家测绘局制订的地理信息系统的坐标系统，是由WGS84坐标系经过加密后的坐标系。

BD09：百度坐标系，在GCJ02坐标系基础上再次加密。其中BD09LL表示百度经纬度坐标，BD09MC表示百度墨卡托米制坐标。

全球通用第一种，如果使用高德或者百度，要转换到第一种才能通用

### 3.指定value获取

获取当前locale 

Resources resources = Utils.getApp().getResources();

Locale locale = resources.getConfiguration().locale;

指定语言

Configuration config = resources.getConfiguration();

config.locale = new Locale("en", "US");

Resources resourcesEn = new Resources(resources.getAssets(), resources.getDisplayMetrics(), config);

获取value

resourcesEn.getString(valuesId,config);

重置语言

config.locale = locale;

new Resources(resources.getAssets(), resources.getDisplayMetrics(), config);

