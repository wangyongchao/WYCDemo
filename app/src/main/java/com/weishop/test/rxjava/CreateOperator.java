package com.weishop.test.rxjava;

import com.weishop.test.data.Course;
import com.weishop.test.data.Student;
import com.weishop.test.util.LogUtils;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangyongchao on 2019/12/27 15:58
 */
public class CreateOperator {

    public void test() {
        testSingle();
    }


    /**
     * Observable观察者
     * Observable
     * Observer
     */
    private void testObservable() {
        //Observable
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onComplete();
//                emitter.onError(new RuntimeException());
//                emitter.onNext(2);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                LogUtils.d("onNext =" + value);

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("onError");
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete");
            }
        });
    }


    /**
     * Flowable观察者，支持背压,限流
     * Publisher
     * Subscriber
     */
    private void testFlowable() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onComplete();
                emitter.onError(new RuntimeException());

            }
        }, BackpressureStrategy.ERROR)
                .subscribe(new FlowableSubscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     *
     */
    private void testFlowableJust() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onComplete();
                emitter.onError(new RuntimeException());

            }
        }, BackpressureStrategy.ERROR)
                .subscribe(new FlowableSubscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 只有onSuccess可onError事件，
     * 只能用onSuccess发射一个数据或一个错误通知，之后再发射数据也不会做任何处理，直接忽略
     * 要么成功，要么失败
     * Single
     * SingleObserver 观察者
     */
    private void testSingle() {
        Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
//                emitter.onSuccess(1);
                emitter.onError(new Exception());

            }
        }).subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("onSubscribe");
            }

            @Override
            public void onSuccess(Integer o) {
                LogUtils.d("onSuccess");

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("onError");

            }
        });
    }

    /**
     * 只有onComplete和onError事件，
     * 不发射数据，没有map，flatMap操作符。常常结合andThen操作符使用
     * Completable 被观察者
     * CompletableObserver 观察者
     */
    private void testCompletable() {
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                emitter.onComplete();
                emitter.onError(new RuntimeException());

            }
        }).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }

    /**
     * 没有onNext方法，同样需要onSuccess发射数据，
     * 且只能发射0或1个数据，多发也不再处理
     */
    private void testMayBe() {
        Maybe.create(new MaybeOnSubscribe<Integer>() {
            @Override
            public void subscribe(MaybeEmitter<Integer> emitter) throws Exception {
                emitter.onSuccess(1);
                emitter.onComplete();
                emitter.onError(new RuntimeException());
            }
        }).subscribe(new MaybeObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void testFlowableToSingle() {
        List<Student> students = buildStudents();
        Flowable.fromIterable(students).subscribe(new FlowableSubscriber<Student>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(Student student) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
        Flowable<Object> flowable = Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(FlowableEmitter<Object> emitter) throws Exception {

            }
        }, BackpressureStrategy.ERROR);
        Single<List<Student>> single = Flowable.fromIterable(students).toList();

    }

    private void testJust() {
        List<Student> students = buildStudents();
        Observable.just(students).subscribeOn(Schedulers.io()).subscribe(new Observer<List<Student>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Student> students) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    private List<Student> buildStudents() {
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
