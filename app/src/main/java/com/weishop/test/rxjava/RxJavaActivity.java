
package com.weishop.test.rxjava;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.weishop.test.data.Course;
import com.weishop.test.data.Student;
import com.weishop.test.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends Activity implements View.OnClickListener {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rxjava);
        findViewById(R.id.get).setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.content);

    }


    @Override
    public void onClick(View v) {
//        String str = "hello world ddd bbb";
//        testFrom(str);
        testChange2StudentName();
        testChange2CourseName();

    }


    Observer<String> observer = new Observer<String>() {
        @Override
        public void onNext(String s) {
            System.out.println("Observer onNext s=" + s);
        }

        @Override
        public void onCompleted() {
            System.out.println("Observer onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("Observer onError e=" + e);
        }
    };

    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onNext(String s) {
            System.out.println("Subscriber onNext s=" + s);
        }

        @Override
        public void onCompleted() {
            System.out.println("Subscriber onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("Subscriber onError e=" + e);
        }
    };

    void testObservable() {
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });
//        observable.subscribe(subscriber);


        //just
        Observable<String> just = Observable.just("Hello", "Hi", "Aloha");
//        just.subscribe(subscriber);//底层循环调用onsubscriber,onsubsriber会调用到此处注册的suscriber


        //数组
        String[] array = {"Hello", "Hi", "Aloha"};

        Observable<String> from = Observable.from(array);
        from.subscribe(subscriber);


    }

    void testNoAll() {
        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {
                System.out.println("call s=" + s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
                System.out.println("call throwable=" + throwable);
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                System.out.println("call onCompletedAction");
            }
        };

        String[] array = {"Hello", "Hi", "Aloha"};

        Observable<String> observable = Observable.from(array);

// 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);
// 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAction, onErrorAction);
// 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }

    void testSample() {
        String[] names = {"zhangsan", "lisi", "wangwu"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {
                        System.out.println("name=" + name);
                    }
                });
    }


    /**
     * Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
     * Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
     * Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
     * Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
     * 另外， Android 还有一个专用的 AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。
     */
    void testMutilThread() {
        //有问题
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("Observable thread=" + Thread.currentThread().getName());
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String o) {
                        System.out.println("subscribe thread=" + Thread.currentThread().getName());
                    }
                });


//        Observable.just(1, 2, 3, 4)
//                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程,既是事件源产生的线程
//                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程,事件消费的线程
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer number) {
//                        System.out.println("number=" + number+",thread="+Thread.currentThread().getName());
//                        mTextView.setText(number+"");
//                    }
//                });
    }

    /**
     * 变换
     * func有返回值
     * action没有返回值
     */
    void testChange() {
        Observable.just("images/logo.png") // 输入类型 String
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String filePath) { // 参数类型 String
                        return getBitmapFromPath(filePath); // 返回类型 Bitmap
                    }
                })
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) { // 参数类型 Bitmap
                        showBitmap(bitmap);
                    }
                });
    }

    private void showBitmap(Bitmap bitmap) {

    }

    private Bitmap getBitmapFromPath(String filePath) {

        return null;
    }

    Student[] getStudents() {


        Student s1 = new Student();
        s1.name = "张三";
        Course course1 = new Course();
        course1.courseName = "数学";
        Course course2 = new Course();
        course2.courseName = "语文";

        ArrayList<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);


        Student s2 = new Student();
        s2.name = "李四";
        Course course21 = new Course();
        course21.courseName = "自然";
        Course course22 = new Course();
        course22.courseName = "英语";

        ArrayList<Course> courses2 = new ArrayList<>();
        courses2.add(course21);
        courses2.add(course22);

        s2.courses = courses2;

        Student[] students = {s1, s2};

        return students;
    }


    void testChange2StudentName() {

        Student[] students = getStudents();
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String name) {
                System.out.println("学生:" + name);
            }
        };
        Observable.from(students)
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        System.out.println("student:" + student);
                        return student.name;
                    }
                })
                .subscribe(subscriber);
    }

    void testChange2CourseName() {

        Student[] students = getStudents();
        Subscriber<ArrayList<Course>> subscriber = new Subscriber<ArrayList<Course>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ArrayList<Course> courses) {
                for (Course c :
                        courses) {
                    System.out.println("课程:" + c.courseName);
                }

            }
        };
        Observable.from(students)
                .map(new Func1<Student, ArrayList<Course>>() {
                    @Override
                    public ArrayList<Course> call(Student student) {
                        System.out.println("call");
                        return student.courses;
                    }
                })
                .subscribe(subscriber);
    }


    //----------------------------------------------------------------------------------------

    /**
     * 简单写法
     */
    void testNormal() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello, world!");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("s=" + s);
            }
        };

        observable.subscribe(subscriber);

    }


    /**
     * 只有一个回调
     */
    void testSimple() {

        Action1<Integer> action1 = new Action1<Integer>() {
            @Override
            public void call(Integer i) {
                System.out.println("i=" + i);
            }
        };

        Observable.just("hello world").map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return s.hashCode();
            }
        }).subscribe(action1);
    }

    /**
     * map可以修改输出参数类型
     *
     * @param str
     */
    void testMap(String str) {
        query(str).map(new Func1<List<String>, List<String>>() {
            @Override
            public List<String> call(List<String> strings) {
                List<String> list = new ArrayList<String>(strings.size());

                for (int i = 0; i < strings.size(); i++) {
                    list.add(strings.get(i) + "_fr");
                }
                return list;
            }
        }).subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> strings) {
                for (String s :
                        strings) {
                    System.out.println("orginal s=" + s);
                }

            }
        });


        //lambda表达式

        query(str).map(strings -> {
            List<String> list = new ArrayList<String>(strings.size());
            for (int i = 0; i < strings.size(); i++) {
                list.add(strings.get(i) + "_fr");
            }
            return list;
        }).subscribe(urls -> {
            for (String url :
                    urls) {
                System.out.println("url=" + url);
            }
        });
    }

    /**
     * 简化遍历操作
     *
     * @param str
     */
    void testFrom(String str) {

        Observable.from(str.split(" ")).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + "from_";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("str=" + s);

            }
        });

        //lambda表达式
        Observable.from(str.split(" ")).map(url -> {

            return url + "_fr";
        }).subscribe(url -> System.out.println(url));

    }

    /**
     * 输出一个observable
     *
     * @param str
     */
    void testFlatMap(String str) {
        //遍历每个，生成observable
        query(str).flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("ss=" + s);
            }
        });

        //lambda表达式
        query(str).flatMap(strings -> Observable.from(strings)).subscribe(s -> System.out.println());

    }


    private Observable<List<String>> query(final String s) {

        Observable<List<String>> observable = Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                String[] split = s.split(" ");
                List<String> strings = Arrays.asList(split);
                subscriber.onNext(strings);
                subscriber.onCompleted();
            }
        });

        return observable;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
