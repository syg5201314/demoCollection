package sunning.democollection.learn._0504;

import android.os.Bundle;
import android.os.Looper;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import sunning.democollection.BaseActivity;
import sunning.democollection.learn._0504.bean.Course;
import sunning.democollection.learn._0504.bean.Student;
import sunning.global.common.ButtonFactory;

/**
 * Created by sunning on 16/5/4.
 */
public class RxJavaDemo extends BaseActivity {

    private PublishSubject<Integer> mCounterEmitter;

    private static final String TAG = RxJavaDemo.class.getName();
    String[] sts = new String[]{"1", "2", "333", "555"};

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        ButtonFactory buttonFactory = new ButtonFactory(this);
        setContentView(buttonFactory.create(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: " + v.toString() + "=");
            }
        }));
        testRXDemo();
    }

    void testRXDemo() {
        observable.subscribe(subscriber);
//        test4();
        test2();
        test7();
    }

    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            Log.e(TAG, "onCompleted: ");
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "Throwable: " + e.getMessage());
        }

        @Override
        public void onNext(String s) {
            Log.e(TAG, "onNext: " + s);
        }
    };

    Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Log.e(TAG, "onNext: " + s);
        }
    };

    Observable.OnSubscribe<String> s = new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {

        }
    };

    Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onStart();
            subscriber.onNext("哇啦");
            subscriber.onNext("走啦");
            subscriber.onNext("哇哈");
            subscriber.onCompleted();
        }
    });

    private void test1() {
        String[] strs = new String[]{"111", "222", "333"};
        Observable.from(strs).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "call: " + s);
            }
        });
    }

    private void test2() {
        Observable.just(1, 2, 3, 4)//被观察者
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(Schedulers.io()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {//观察者
                    @Override
                    public void call(Integer number) {
                        Log.d(TAG, "number:" + number);
                        ContentFrameLayout layout = (ContentFrameLayout) findViewById(android.R.id.content);
                        ButtonFactory buttonFactory = new ButtonFactory(RxJavaDemo.this);
                        Button btn = (Button) buttonFactory.create(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mCounterEmitter.onNext(5201314);
                                Log.e(TAG, "Button=========>Observable.just====" + Thread.currentThread().getName() + "==" + Looper.getMainLooper().getThread().getName());
                            }
                        });
                        btn.setText(Thread.currentThread().getName());
                        layout.addView(btn, 1);
                    }
                });
    }

    private void test3() {
        Observable.just("c://:download").map(new Func1<String, File>() {
            @Override
            public File call(String s) {
                return new File(s);
            }
        }).subscribe(new Action1<File>() {
            @Override
            public void call(File file) {
                Log.e(TAG, "call: " + file.getName());
            }
        });
    }

    private void test4() {
        Student[] stndent = new Student[]{Student.create("啦啦"), Student.create("嘻嘻嘻"), Student.create("我勒个去")};
        Observable.from(stndent)
                .flatMap(func1)
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        Log.e(TAG, "call: " + course.courseName);
                    }
                });
    }

    private Func1<Student, Observable<Course>> func1 = new Func1<Student, Observable<Course>>() {
        @Override
        public Observable<Course> call(Student student) {
            return Observable.from(student.courses);
        }
    };

    private void test5() {
        String[] sts = new String[]{"1", "2", "333", "555"};
        Observable.from(sts)
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return Integer.valueOf(s);
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e(TAG, "call: " + integer);
                    }
                });
        Subscriber<String> st = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };
    }


    private void test6() {
        Observable.from(sts).lift(new Observable.Operator<Integer, String>() {
            @Override
            public Subscriber<? super String> call(final Subscriber<? super Integer> subscriber) {
                return new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        subscriber.onNext(Integer.valueOf(s));
                    }
                };
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {

            }
        });
    }

    private void test7() {
        mCounterEmitter = PublishSubject.create();
        mCounterEmitter.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext: " + integer);
            }
        });
    }


}
