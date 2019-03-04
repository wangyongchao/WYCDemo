import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；
 * <p>
 * 如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，
 * 若添加成功，则该任务会等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），
 * 则会尝试创建新的线程去执行这个任务；
 * <p>
 * 如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
 * <p>
 * 如果线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，
 * 直至线程池中的线程数目不大于corePoolSize；如果允许为核心池中的线程设置存活时间，
 * 那么核心池中的线程空闲时间超过keepAliveTime，线程也会被终止。
 * <p>
 * <p>
 * corePoolSize：核心池的大小，这个参数跟后面讲述的线程池的实现原理有非常大的关系。
 * 在创建了线程池后，默认情况下，线程池中并没有任何线程，而是等待有任务到来才创建线程去执行任务，
 * 除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法，从这2个方法的名字就可以看出，
 * 是预创建线程的意思，即在没有任务到来之前就创建corePoolSize个线程或者一个线程。默认情况下，
 * 在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
 * 当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
 * <p>
 * maximumPoolSize：线程池最大线程数，这个参数也是一个非常重要的参数，它表示在线程池中最多能创建多少个线程；
 * <p>
 * keepAliveTime：表示线程没有任务执行时最多保持多久时间会终止。默认情况下，
 * 只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用，
 * 直到线程池中的线程数不大于corePoolSize，即当线程池中的线程数大于corePoolSize时，
 * 如果一个线程空闲的时间达到keepAliveTime，则会终止，直到线程池中的线程数不超过corePoolSize。
 * 但是如果调用了allowCoreThreadTimeOut(boolean)方法，
 * 在线程池中的线程数不大于corePoolSize时，keepAliveTime参数也会起作用，直到线程池中的线程数为0；
 * <p>
 * <p>
 * 当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，
 * 如果还有任务到来就会采取任务拒绝策略，通常有以下四种策略：
 */

public class CustomExecutor extends ThreadPoolExecutor {
    public CustomExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit,
                          BlockingQueue<Runnable> blockingQueue,
                          ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, blockingQueue, threadFactory,
                rejectedExecutionHandler);
    }


    public static class CustomThreadFactory implements ThreadFactory {
        private AtomicInteger count = new AtomicInteger(1);

        class MyThread extends Thread {

            public MyThread(Runnable runnable, String s) {
                super(runnable, s);
            }


            @Override
            protected void finalize() throws Throwable {
                super.finalize();
                System.out.println("MyThread finalize");
            }
        }

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "Thread" + count.getAndAdd(1));
            System.out.println("newThread=" + thread.getName());
            return thread;
        }
    }

    public static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            System.out.println("rejectedExecution ");
        }
    }

    static class MyRunnable implements Runnable, Comparable {
        private int number;

        public MyRunnable(int number) {
            this.number = number;
        }

        @Override
        public void run() {

            System.out.println("number=" + number + ",thread=" + Thread.currentThread().getName());

        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("MyRunnable finalize" + this.number);
        }

        @Override
        public int compareTo(Object o) {
            MyRunnable runnable = (MyRunnable) o;
            return this.number - runnable.number;
        }
    }


    public static void main(String[] args) {
        CustomExecutor customExecutor = new CustomExecutor(1, 1, 10,
                TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>(), new CustomExecutor.CustomThreadFactory(),
                new CustomExecutor.CustomRejectedExecutionHandler());

        for (int i = 0; i < 1; i++) {
            customExecutor.execute(new MyRunnable(i));
        }
        int size = customExecutor.getQueue().size();
        System.out.println("size=" + size);



    }


}
