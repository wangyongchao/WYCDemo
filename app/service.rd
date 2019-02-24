https://developer.android.google.cn/guide/components/activities/process-lifecycle
foreground process:
1.Activity处于屏幕最前端正在与用户进行交互，onresume方法已经被调用过了。
2.BroadcastReceiver正在执行，onReceive方法已经调用过了。
3.Service正在执行代码，在其中的生命周期方法中。Service.onCreate(), Service.onStart(), or Service.onDestroy()

visible process
1.Activity正在运行但是没有在屏幕上显示，调用过onPause方法。
2.foregroud service正在运行，通过调用startForegroud
3.拥有一个service系统有特殊的用处，比如墙纸，输入法

service process
通过调用startservice，虽然对用户不可见，但是用户知道有特殊的用处，比如后台上传下载资源

cached process




后台任务考虑三个因素
1.任务是否可以延期执行，或者在某个时间执行
2.一旦任务开始执行，系统是否得保持进程处于激活状态
3.任务是否有系统触发开始执行

后台执行限制:

一.后台服务限制
系统通过以下几点区分前台app还是后台app
1.它有一个可见的activity，不论activity是start或者是pause
2.有一个foreground service
3.另一个app绑定到这个app，绑定到service或者contentprovider，例如绑定到现在的app的
ime，墙纸服务，通知，声音或者文字服务。
如果以上条件都不满足，就是后台app

当app处于前台的时候，它可以随意的创建前台服务或者后台服务。当app处于后台以后，它有几分钟窗口期去创建或者使用服务，当时间过去以后
app被认为是空闲的。同时，系统会停止后台服务，就像服务自己调用了stopself一样。
后台服务创建推荐使用JobScheduler

在Android8.0之前创建前台服务首先要通过starservice启动一个后台服务，然后startForeground()推送到前台。在8.0之后
系统不允许后台app创建服务，因此8.0提供了一个新的方法startForegroundService()创建前台服务，当系统创建好服务后，app有5秒钟的时间
调用startForeground()弹出通知。如果app不调用这个方法，系统会停止service并抛出anr。

二.广播限制
从7.0开始，CONNECTIVITY_ACTION广播如果是在manifest中注册的话，无法接收到。通过Context.registerReceiver()可以接收到。

ACTION_NEW_PICTURE ,ACTION_NEW_VIDEO 这个两个广播被移除掉了







