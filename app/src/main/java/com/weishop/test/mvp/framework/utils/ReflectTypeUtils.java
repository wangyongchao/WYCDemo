package com.weishop.test.mvp.framework.utils;


import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * 泛型相关
 */
public class ReflectTypeUtils {

    private ReflectTypeUtils() {
    }

    /**
     * 返回泛型类的第{@param paramIndex}泛型类型
     * 比如Map<String, Object>, 若paramIndex为0,则返回String.class
     *
     * @param ownerType
     * @param declaredClass
     * @param paramIndex
     * @return
     */
    public static Type getParameterizedType(Type ownerType, Class<?> declaredClass,
                                            int paramIndex) {

        Class<?> clazz;
        ParameterizedType pt;
        Type[] ats = null;
        TypeVariable<?>[] tps = null;

        if (ownerType instanceof ParameterizedType) {
            pt = (ParameterizedType) ownerType;
            clazz = (Class<?>) pt.getRawType();
            ats = pt.getActualTypeArguments();
            tps = clazz.getTypeParameters();
        } else {
            clazz = (Class<?>) ownerType;
        }

        // 非匿名内部类
        if (declaredClass == clazz) {

            if (ats != null) {
                return ats[paramIndex];
            }

            return Object.class;
        }

        // 匿名内部类
        Type[] types = clazz.getGenericInterfaces();
        if (types != null) {
            for (Type t : types) {
                if (t instanceof ParameterizedType) {
                    Class<?> cls = (Class<?>) ((ParameterizedType) t).getRawType();
                    if (declaredClass.isAssignableFrom(cls)) {
                        try {
                            return getTrueType(getParameterizedType(t, declaredClass, paramIndex)
                                    , tps, ats);
                        } catch (Throwable ignored) {
                        }
                    }
                }
            }
        }

        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {
            if (declaredClass.isAssignableFrom(superClass)) {
                return getTrueType(
                        getParameterizedType(clazz.getGenericSuperclass(),
                                declaredClass, paramIndex), tps, ats);
            }
        }

        return null;
    }


    private static Type getTrueType(Type type, TypeVariable<?>[] typeVariables,
                                    Type[] actualTypes) {

        if (type instanceof TypeVariable<?>) {
            TypeVariable<?> tv = (TypeVariable<?>) type;
            String name = tv.getName();
            if (actualTypes != null) {
                for (int i = 0; i < typeVariables.length; i++) {
                    if (name.equals(typeVariables[i].getName())) {
                        return actualTypes[i];
                    }
                }
            }

            return tv;
        } else if (type instanceof GenericArrayType) {
            Type ct = ((GenericArrayType) type).getGenericComponentType();
            if (ct instanceof Class<?>) {
                return Array.newInstance((Class<?>) ct, 0).getClass();
            }
        }

        return type;
    }
}
