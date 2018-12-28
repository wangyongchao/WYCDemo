　　SurfaceView和View一大不同就是SurfaceView是被动刷新的，但我们可以控制刷新的帧率，
而View并且通过invalidate方法通知系统来主动刷新界面的，但是View的刷新是依赖于系统的VSYSC信号的，
其帧率并不受控制，而且因为UI线程中的其他一些操作会导致掉帧卡顿。而对于SurfaceView而言，它是在子线程中绘制图形，
根据这一特性即可控制其显示帧率，通过简单地设置休眠时间，即可，并且由于在子线程中，一般不会引起UI卡顿。

Thread.sleep(50);即可以控制1s内刷新20次
　　SurfaceView的双缓冲机制：即对于每一个SurfaceView对象而言，有两个独立的graphic buffer。在Android SurfaceView的双缓冲机制中是这样实现的：

在Buffer A中绘制内容，然后让屏幕显示Buffer A；在下一个循环中，在Buffer B中绘制内容，然后让屏幕显示Buffer B，
如此往复。而由于这个双缓冲机制的存在，可能会引起闪屏现象，。在第一个"lockCanvas-drawCanvas-unlockCanvasAndPost "循环中，
更新的是buffer A的内容；到下一个"lockCanvas-drawCanvas-unlockCanvasAndPost"循环中，更新的是buffer B的内容。
 如果buffer A与buffer B中某个buffer内容为空，当屏幕轮流显示它们时，就会出现画面黑屏闪烁现象。

解决方法
出现黑屏是因为buffer A与buffer B中一者内容为空，而且为空的一方还被post到了屏幕。于是有两种解决思路：

1.不让空buffer出现：每次向一个buffer写完内容并post之后，顺便用这个buffer的内容填充另一个buffer。
这样能保证两个 buffer的内容是同步的，缺点是做了无用功，耗费性能。

2.不post空buffer到屏幕：当准备更新内容时，先判断内容是否为空，
只有非空时才启动"lockCanvas-drawCanvas-unlockCanvasAndPost"这个流程。（上述模板和示例中即采用了这个方法）