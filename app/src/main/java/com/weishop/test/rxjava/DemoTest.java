package com.weishop.test.rxjava;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.weishop.test.data.Course;
import com.weishop.test.data.Student;
import com.weishop.test.util.LogUtils;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * https://www.jianshu.com/p/9b1304435564
 */
public class DemoTest {

    private Subscription subscription;

    public void test() {
        demo();
    }

    /**
     * 教程(7)讲flowable，Flowable在设计的时候采用了一种新的思路也就是响应式拉取的方式来更好的解决上下游流速不均衡的问题
     */
    public void testFlowable() {
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                LogUtils.d("emit 1");
                emitter.onNext(1);

                LogUtils.d("emit 2");
                emitter.onNext(2);

                LogUtils.d("emit 3");
                emitter.onNext(3);

                LogUtils.d("emit 4");
                emitter.onNext(4);

                LogUtils.d("onComplete");
                emitter.onComplete();

            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        Subscriber subscriber = new Subscriber<Integer>() {

            @Override
            public void onSubscribe(Subscription s) {
                LogUtils.d("onSubscribe");
//                s.cancel();
//                s.request(Integer.MAX_VALUE);

            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("onNext =" + integer);
            }

            @Override
            public void onError(Throwable t) {
                LogUtils.d("onError =" + t);
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete");
            }
        };

        flowable.subscribe(subscriber);

    }

    public void testFlowableRequest() {
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                LogUtils.d("emit 1");
                emitter.onNext(1);

                LogUtils.d("emit 2");
                emitter.onNext(2);

                LogUtils.d("emit 3");
                emitter.onNext(3);

                LogUtils.d("emit 4");
                emitter.onNext(4);

                LogUtils.d("emit onComplete");
                emitter.onComplete();

            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        Subscriber subscriber = new Subscriber<Integer>() {

            @Override
            public void onSubscribe(Subscription s) {
                LogUtils.d("onSubscribe");
                subscription = s;
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("onNext =" + integer);
            }

            @Override
            public void onError(Throwable t) {
                LogUtils.d("onError =" + t);
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete");
            }
        };

        flowable.subscribe(subscriber);

    }

    /**
     * Flowable提供了一个默认大小128的缓冲区，如果发射的大于128就会发生异常。
     */
    public void testFlowableBuffer() {
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 1; i <= 128; i++) {
                    LogUtils.d("emit i=" + i);
                    emitter.onNext(i);
                }
                LogUtils.d("emit onComplete");
                emitter.onComplete();

            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        Subscriber subscriber = new Subscriber<Integer>() {

            @Override
            public void onSubscribe(Subscription s) {
                LogUtils.d("subscriber onSubscribe");
                subscription = s;
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("onNext =" + integer);
            }

            @Override
            public void onError(Throwable t) {
                LogUtils.d("onError =" + t);
            }

            @Override
            public void onComplete() {
                LogUtils.d("subscriber onComplete");
            }
        };

        flowable.subscribe(subscriber);

    }

    public void request() {
        if (subscription != null) {
            subscription.request(1);
        }
    }

    public void testObservable() {
//        Observable<String> just = Observable.just("A", "B", "C");
//        just.subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                System.out.println("s="+s);
//
//            }
//        });
        Single<List<String>> single = Observable.just("A", "B", "C").toList();
//        single.subscribe(new Consumer<List<String>>() {
//            @Override
//            public void accept(List<String> strings) throws Exception {
//                System.out.println("strings=" + strings.size());
//            }
//        });
//        single.subscribe(strings -> System.out.println(strings));
//        single.subscribe(System.out::println);
        Flowable<List<String>> listFlowable = single.toFlowable();
//        listFlowable.subscribe(new FlowableSubscriber<List<String>>() {
//            @Override
//            public void onSubscribe(Subscription s) {
//                LogUtils.d("onSubscribe");
//                s.request(1);
//
//            }
//
//            @Override
//            public void onNext(List<String> strings) {
//                LogUtils.d("strings=" + strings);
//
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                LogUtils.d("onError t=" + t);
//            }
//
//            @Override
//            public void onComplete() {
//                LogUtils.d("onComplete");
//            }
//        });
        Flowable<String> flowable = listFlowable.flatMap(Flowable::fromIterable);
//        listFlowable.flatMap(strings -> Flowable.fromIterable(strings));
//        listFlowable.flatMap(new Function<List<String>, Publisher<?>>() {
//            @Override
//            public Publisher<?> apply(List<String> strings) throws Exception {
//                return Flowable.fromIterable(strings);
//            }
//        });
        flowable.subscribe();
    }

    /**
     * doOnNext在调用onnext方法之前调用
     */
    public void testFilter() {
        Observable.just(1, 2, 3, 4, 5, 6)
//                .filter(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer % 2 == 0;
//                    }
//                })
                .filter(x -> x % 2 == 0)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println(integer);
//                    }
//                })
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d("doOnNext integer=" + integer);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.d("doOnComplete ");
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d("onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.d("onNext integer=" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d("onError e=" + e);
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d("onComplete");
                    }
                });
    }

    /**
     * Flowable.fromIterable从多个student变成
     */
    public void demo() {
//        List<Student> students = buildStudents();
//        Flowable<List<Student>> studentFlowable =
//                Flowable.fromIterable(students).toList().toFlowable();

        Flowable<List<Student>> listFlowable = demo2();
        LogUtils.d("demo listFlowable="+listFlowable);
        listFlowable
//       .flatMap(new Function<List<Student>, Publisher<?>>() {
//            @Override
//            public Publisher<?> apply(List<Student> students) throws Exception {
//                return Flowable.fromIterable(students);
//            }
//        })
                .flatMap(Flowable::fromIterable)
//                .filter(new Predicate<Student>() {
//                    @Override
//                    public boolean test(Student student) throws Exception {
//                        return student.getId() % 2 == 0;
//                    }
//                })
                .filter(student -> student.getId() % 2 == 0)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.d("doFinally");

                    }
                })
                .subscribe(new SingleObserver<List<Student>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d("onSubscribe");
                    }

                    @Override
                    public void onSuccess(List<Student> students) {
                        LogUtils.d("onSuccess students=" + students.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d("onError e=" + e);
                    }
                });


    }

    public Flowable<List<Student>> demo2() {
        List<Student> students = buildStudents();
        Flowable<List<Student>> studentFlowable =
                Flowable.fromIterable(students).toList().toFlowable();
        LogUtils.d("studentFlowable=" + studentFlowable);

        Flowable<List<Student>> listFlowable = studentFlowable.flatMap(new Function<List<Student>,
                Publisher<List<Student>>>() {
            @Override
            public Publisher<List<Student>> apply(List<Student> students) throws Exception {
                Flowable<List<Student>> listFlowable =
                        Flowable.fromIterable(students).doOnNext(new Consumer<Student>() {
                            @Override
                            public void accept(Student student) throws Exception {
                                LogUtils.d("fromIterable doOnNext =" + student.getName());
                            }
                        }).toList().toFlowable();
                LogUtils.d("listFlowable=" + listFlowable);
                return listFlowable;
            }
        }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                LogUtils.d("demo2 doOnComplete");
            }
        });
        LogUtils.d("demo2 listFlowable=" + listFlowable);
        return listFlowable;


    }


    public List<Student> buildStudents() {
        List<Student> studentsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setId(i);
            student.setName("学生" + i);
            studentsList.add(student);

            List<Course> courses = new ArrayList<>();
            for (int k = 0; k < 4; k++) {
                Course course = new Course();
                course.setCourseName("课程" + k);
                courses.add(course);
            }
            student.setCourses(courses);
        }
        return studentsList;

    }

}
