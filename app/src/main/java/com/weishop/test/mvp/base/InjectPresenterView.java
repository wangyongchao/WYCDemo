package com.weishop.test.mvp.base;

/**
 * 需要手动注入Present
 *
 * @param <T>
 */
public interface InjectPresenterView<T extends IPresenter> extends IView {

    void setPresent(T t);


}
