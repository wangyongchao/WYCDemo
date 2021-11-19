package com.weishop.test.rxjava;

import androidx.core.util.Pair;

import com.weishop.test.data.Course;
import com.weishop.test.data.Student;
import com.weishop.test.util.LogUtils;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * https://www.jianshu.com/p/9b1304435564
 * Single implement SingleSource,对应的观察者是SingleObserver 代表单个数据项
 * Observable implement ObservableSource,对应的观察者是 Observer 代表多个数据项
 * Flowable implement Publisher,对应的观察者也就是订阅者是Subscriber  可以支持背压限流
 * Completable
 * Maybe
 */
public class DemoTest {

    private Subscription subscription;

    public void test() {
        testObservers();
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
//        List<Student> students = buildStudents();
//        Observable.just(students).map(new Function<List<Student>, Object>() {
//            @Override
//            public Object apply(List<Student> students) throws Exception {
//                return null;
//            }
//        }).subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//
//            }
//        });


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
        LogUtils.d("demo listFlowable=" + listFlowable);
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

    private void testZip() {
        Observable<String> firstNames = Observable.just("James", "Jean-Luc", "Benjamin", "222");
        Observable<String> lastNames = Observable.just("Kirk", "Picard", "Sisko");
        Observable.zip(firstNames, lastNames, new BiFunction<String,
                String, Pair<String, String>>() {
            @Override
            public Pair apply(String s, String s2) throws Exception {
                return new Pair<String, String>(s, s2);
            }
        }).subscribe(new Consumer<Pair<String, String>>() {
            @Override
            public void accept(Pair<String, String> pair) throws Exception {
            }
        });
        firstNames.zipWith(lastNames, new BiFunction<String, String, String>() {
            @Override
            public String apply(String first, String last) throws Exception {

                return first + " " + last;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.d(s);
            }
        });
//        firstNames.zipWith(lastNames, (first, last) -> first + " " + last)
//                .subscribe(item -> LogUtils.d(item));
    }

    /**
     * 合并两个上游数据
     * Observable.just(1, 2, 3);
     * Observable.just(4, 5, 6);
     * Observable.concat(observable1, observable2)
     * 合并完后是123456,按照顺序合并,先合并observable1,然后再合并observable2
     * <p>
     * firstOrError:返回一个Single对象，这个single对象中只保存了observable发射的第一个元素。
     * 或者observable中元素为空的时候抛出NoSuchElementException异常.
     */
    private void testContact1() {
        Observable<Integer> observable1 = Observable.just(1, 2, 3);
        Observable<Integer> observable2 = Observable.just(4, 5, 6);
        Observable<Integer> concat = Observable.concat(observable1, observable2);
        concat.filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                //返回true代表不过滤
                return false;
            }
        })
                .firstOrError().toObservable()
                .subscribe(new Observer<Integer>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d("onSubscribe ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.d("onNext integer=" + integer);
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

    private void testContact() {
        List<Student> students1 = buildStudents();
        Flowable<List<Student>> studentFlowable1 =
                Flowable.fromIterable(students1).toList().toFlowable();

        List<Student> students2 = buildStudents();
        Flowable<List<Student>> studentFlowable2 =
                Flowable.fromIterable(students2).toList().toFlowable();

        Flowable.concat(studentFlowable1, studentFlowable2)
//                .filter(new Predicate<List<Student>>() {
//                    @Override
//                    public boolean test(List<Student> students) throws Exception {
//                        LogUtils.d("test students=" + (students.isEmpty()));
//                        return !students.isEmpty();
//                    }
//                })
                .filter(students -> !students.isEmpty())
                .firstOrError()
                .toFlowable().subscribe(new Subscriber<List<Student>>() {
            @Override
            public void onSubscribe(Subscription s) {
                LogUtils.d("onSubscribe");
                s.request(Integer.MAX_VALUE);
            }

            @Override
            public void onNext(List<Student> students) {
                LogUtils.d("onNext students=" + students.size());
            }

            @Override
            public void onError(Throwable t) {
                LogUtils.d("onError");
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete");
            }
        });

    }

    /**
     * Single表示单个元素,只有onSuccess和onErro方法
     */
    public void testSingle() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            }
        });

        Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
                emitter.onSuccess(1);

            }
        }).subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }

    /**
     * 测试5中观察者
     */
    private void testObservers() {
        //Observable
        //Observable.create(new ObservableOnSubscribe<Integer>() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("onSubscribe =" + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                LogUtils.d("onNext =" + value);

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("onError =" + e);
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete");
            }
        });


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

    public List<Student> buildStudents2() {
        List<Student> studentsList = new ArrayList<>();
        for (int i = 10; i < 20; i++) {
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
