package com.weishop.test.mvp.framework.annotation;


import com.weishop.test.mvp.framework.repo.GsonParser;
import com.weishop.test.mvp.framework.repo.IParser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dqq on 2019/12/18.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER,ElementType.LOCAL_VARIABLE,ElementType.TYPE})
public @interface AutoParser {
    Class<? extends IParser> value() default GsonParser.class;
}
