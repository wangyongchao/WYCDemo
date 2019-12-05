
package com.weishop.test.rxjava;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.weishop.test.R;
import com.weishop.test.util.LogUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends Activity implements View.OnClickListener {

    private TextView mTextView;
    private Subscription mSubscription;
    private DemoTest demoTest = new DemoTest();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rxjava);
        findViewById(R.id.get).setOnClickListener(this);
        findViewById(R.id.request).setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.content);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.request) {
            demoTest.request();  //注意这句代码
        } else {
            demoTest.test();
        }
    }


    /**
     * onSubscribe先被调用代表已经注册进去
     * onComplete,onErro之后的消息Observer不会收到
     * onComplete可以在Observable中多次调用，但是只会收到一次
     * onErro只能发送一次，发送多次会crash
     * Disposable会截断观察者接收事件,但不会截断发送者
     * Consumer代表只消费某个事件，可以根据方法确定
     */
    @SuppressLint("CheckResult")
    private void testObservable() {
        //subscribe 订阅
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.d("subscribe");
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                LogUtils.d("onComplete before");
                emitter.onComplete();
                LogUtils.d("onComplete after");
                emitter.onNext(4);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.d("Observer accept =" + integer);
            }
        });
//                subscribe(new Observer<Integer>() {
//            Disposable disposable;
//
//            @Override
//            public void onSubscribe(Disposable d) {
//                disposable = d;
//                LogUtils.d("Observer onSubscribe =" + d.toString());
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                LogUtils.d("Observer onNext =" + integer);
//                if (integer.intValue() == 2) {
//                    disposable.dispose();
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                LogUtils.d("Observer Throwable =" + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                LogUtils.d("Observer onComplete");
//            }
//        });

    }


    /**
     * 被观察者和观察者默认在一个线程
     * subscribeOn被观察者也就是上游所在的线程,多次指定上游的线程，只有第一次指定的有效
     * observeOn观察者所在的线程,每调用一次observeOn观察者所在的线程改变一下线程。
     * Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
     * Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
     * Schedulers.newThread() 代表一个常规的新线程
     * AndroidSchedulers.mainThread() 代表Android的主线程
     */
    @SuppressLint("CheckResult")
    private void testThread() {

        ObservableOnSubscribe observableOnSubscribe = new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.d("subscribe " + Thread.currentThread().getName());
                emitter.onNext(1);

            }
        };

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.d("consumer " + Thread.currentThread().getName() + ",value=" + integer.intValue());
            }
        };
        Observable observable = Observable.create(observableOnSubscribe);
        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        //运行在io线程，子线程
                        LogUtils.d("doOnNext " + Thread.currentThread().getName());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//下一个事件运行在主线程
                .subscribe(consumer);


    }

    /**
     * 对上游发送的每一个事件应用一个函数, 使得每一个事件都按照指定的函数去变化
     */
    @SuppressLint("CheckResult")
    private void testMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();

            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                LogUtils.d("apply=" + integer);
                return "this is string" + integer.intValue();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.d("s=" + s);

            }
        });


    }


    /**
     * 上游每发送一个事件, flatMap都将创建一个新的水管,
     * 然后发送转换之后的新的事件,
     * 下游接收到的就是这些新的水管发送的数据. 这里需要注意的是, flatMap并不保证事件的顺序
     * <p>
     * concatMap可以保证顺序
     */
    @SuppressLint("CheckResult")
    private void testFlatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);

            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                LogUtils.d("apply=" + integer);
                List<String> list = new ArrayList<String>();
                list.add("string value" + integer.intValue());
                list.add("string value" + integer.intValue());
                list.add("string value" + integer.intValue());
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.d("s=" + s);

            }
        });


    }

    @SuppressLint("CheckResult")
    private void testFlatMap2Lamda() {
        Observable.just("A", "B", "C")
                .flatMap(a -> {
                    return Observable.intervalRange(1, 3, 0, 1, TimeUnit.SECONDS)
                            .map(b -> '(' + a + ", " + b + ')');
                }).blockingSubscribe(s -> LogUtils.d("accept =" + s));

    }

    @SuppressLint("CheckResult")
    private void testFlatMap2() {
        Observable.just("A", "B", "C")
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String a) throws Exception {
                        return Observable.intervalRange(1, 3, 0, 1, TimeUnit.SECONDS)
                                .map(new Function<Long, String>() {
                                    @Override
                                    public String apply(Long b) throws Exception {
                                        return '(' + a + ", " + b + ')';
                                    }
                                });
                    }
                }).blockingSubscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Exception {
                LogUtils.d("accept =" + o);


            }
        });

    }


    private void testIterator() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            list.add("string value" + i);
        }
        Observable.fromIterable(list).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.d("s=" + s);
            }
        });
    }

    /**
     * 组合的过程是分别从 两根水管里各取出一个事件 来进行组合,
     * 并且一个事件只能被使用一次, 组合的顺序是严格按照事件发送的顺利 来进行的
     * <p>
     * 最终下游收到的事件数量 是和上游中发送事件最少的那一根水管的事件数量 相同.
     * 这个也很好理解, 因为是从每一根水管 里取一个事件来进行合并, 最少的 那个肯定就最先取完 ,
     * 这个时候其他的水管尽管还有事件 , 但是已经没有足够的事件来组合了, 因此下游就不会收到剩余的事件了
     * 1234
     * abc
     * 1a,2b,3c
     * 在主线程中默认先走observable1，然后组合实现1a,2b,3c，由于选择最短的observable1,4直接被忽略掉
     * <p>
     * 实际引用时候可以合并两个请求
     */
    @SuppressLint("CheckResult")
    private void testZip() {
        Observable<Integer> observable1 =
                Observable.create(new ObservableOnSubscribe<Integer>() {

                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        LogUtils.d("1");
                        Thread.sleep(1000);
                        emitter.onNext(2);
                        LogUtils.d("2");
                        Thread.sleep(1000);
                        emitter.onNext(3);
                        LogUtils.d("3");
                        Thread.sleep(1000);
                        emitter.onNext(4);
                        LogUtils.d("4");
                        Thread.sleep(1000);
                        emitter.onComplete();
                        LogUtils.d("observable1 onComplete");
                    }
                }).subscribeOn(Schedulers.io());
        Observable<String> observable2 =
                Observable.create(new ObservableOnSubscribe<String>() {

                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        LogUtils.d("A");
                        emitter.onNext("A");
                        Thread.sleep(1000);
                        LogUtils.d("B");
                        emitter.onNext("B");
                        LogUtils.d("C");
                        Thread.sleep(1000);
                        emitter.onNext("C");
                        LogUtils.d("observable2 onComplete");
                        emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.io());
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                LogUtils.d("apply integer=" + integer + ",s=" + s);
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("onSubscribe");
            }

            @Override
            public void onNext(String s) {
                LogUtils.d("onNext s=" + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete");
            }
        });

    }

    /**
     * 上下游事件不均衡
     * filter过滤上游事假
     * 一是从数量上进行治理, 减少发送进水缸里的事件
     * 二是从速度上进行治理, 减缓事件发送进水缸的速度
     */
    @SuppressLint("CheckResult")
    private void testBackpressure1() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {
                    LogUtils.d("onNext=" + i);
                    emitter.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io())
//                .filter(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer % 100==0;
//                    }
//                })
                .sample(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d("integer=" + integer);

                    }
                });

    }

    @SuppressLint("CheckResult")
    private void testBackpressure() {
        Observable<Integer> observable1 =
                Observable.create(new ObservableOnSubscribe<Integer>() {

                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        for (int i = 0; ; i++) {
                            emitter.onNext(i);
                        }
                    }
                }).subscribeOn(Schedulers.io());
        Observable<String> observable2 =
                Observable.create(new ObservableOnSubscribe<String>() {

                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("A");
                    }
                }).subscribeOn(Schedulers.io());
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                LogUtils.d("apply integer=" + integer + ",s=" + s);
                return integer + s;
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.d("accept s=" + s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.d("throwable message=" + throwable.getMessage());
                    }
                });

    }

    /**
     * 这是因为Flowable在设计的时候采用了一
     * 种新的思路也就是响应式拉取的方式来更好的解决上下游流速不均衡的问题,
     * request(long n) 告诉上游需要几个事件
     * <p>
     * 我们把request当做是一种能力, 当成下游处理事件的能力, 下游能处理几个就告诉上游我要几个,
     * 这样只要上游根据下游的处理能力来决定发送多少事件,
     * 就不会造成一窝蜂的发出一堆事件来, 从而导致OOM. 这也就完美的解决之前我们所学到的两种方式的缺陷,
     * 过滤事件会导致事件丢失, 减速又可能导致性能损失.
     * 而这种方式既解决了事件丢失的问题, 又解决了速度的问题, 完美 !
     * <p>
     * Flowable 默认存放128个事件
     * <p>
     * request(long n)每次调用请求几个发送几个
     * <p>
     * BackpressureStrategy.ERROR 下游没有接收能力，抛出异常
     * BackpressureStrategy.BUFFER 上游可以无限制的发送，忽略128个，和observable一样
     * BackpressureStrategy.drop 如果不及时取的话，会丢掉事件
     * BackpressureStrategy.LATEST会取最新的一个事件
     */
    private void testFlowable() {
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 1000; i++) {
                    LogUtils.d(i + "");
                    emitter.onNext(i);

                }

            }
        }, BackpressureStrategy.LATEST).subscribeOn(Schedulers.io());
        flowable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
//                s.request(128);  //注意这句代码
                mSubscription = s;
                LogUtils.d("onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("integer =" + integer.intValue());
            }

            @Override
            public void onError(Throwable t) {
                LogUtils.d("onError =" + t);
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete");
            }
        });
//        flowable.subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onSubscribe(Subscription s) {
//                LogUtils.d("onSubscribe");
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                LogUtils.d("integer =" + integer.intValue());
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

    }

    private void testInterval() {
        Flowable.interval(1, TimeUnit.MICROSECONDS)
                .onBackpressureBuffer()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        LogUtils.d("s");
                        s.request(Long.MAX_VALUE);
                    }


                    @Override
                    public void onNext(Long aLong) {
                        LogUtils.d("onNext long=" + aLong);
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.d("onError t=" + t);
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d("onComplete");
                    }
                });

    }

    private void testFlowable1() {
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(2);
//                emitter.onNext(3);
//                emitter.onNext(4);
//                emitter.onNext(5);
                LogUtils.d("subscribe request=" + emitter.requested());

            }
        }, BackpressureStrategy.ERROR);
        flowable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                mSubscription = s;
                LogUtils.d("onSubscribe");
                s.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("integer =" + integer.intValue());
//                mSubscription.request(1);
            }

            @Override
            public void onError(Throwable t) {
                LogUtils.d("onError =" + t);
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete");
            }
        });

    }

    private void testFlowable2() {
        Flowable
                .create(new FlowableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                        LogUtils.d("current requested: " + emitter.requested());
                    }
                }, BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        LogUtils.d("onSubscribe");
                        mSubscription = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.d("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.d("onError: " + t);
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d("onComplete");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
