package com.weishop.test.mvp.framework;

import android.content.Context;

import com.weishop.test.mvp.framework.repo.IRepository;
import com.weishop.test.util.LogUtils;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class BasePresenter<V extends IView, Repo extends IRepository> implements IPresenter<V> {
    private Reference<V> weakReference;

    protected Repo mRepository;
    protected Context mContext;
    protected V mView;

    public BasePresenter(Context context) {
        this.mContext = context;
        mRepository = createRepository();
    }

    protected abstract Repo createRepository();

    @SuppressWarnings({"unchecked"})
    @Override
    public void attachView(V v) {
        weakReference = new WeakReference(v);
        //采用动态代理避免每次判空
        mView = (V) Proxy.newProxyInstance(v.getClass().getClassLoader(),
                v.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                        if (weakReference == null || weakReference.get() == null) {
                            LogUtils.e("invoke method " + method.getName() + " failed, the view " +
                                    "is" +
                                    " empty");
                            return null;
                        }
                        return method.invoke(weakReference.get(), objects);
                    }
                });

    }

    protected V getView() {
        return mView;
    }

    @Override
    public void detachView(V v) {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }

    }


}
