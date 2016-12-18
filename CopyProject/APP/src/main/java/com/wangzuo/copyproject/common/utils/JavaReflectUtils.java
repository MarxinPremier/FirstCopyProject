package com.wangzuo.copyproject.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Java反射工具包
 * <p>
 * 描述：该工具类封装了Java反射的一些常用操作
 *
 * @author hejie
 */
public class JavaReflectUtils {

    /**
     * 根据全路径类名创建对象
     *
     * @param className
     * @return
     */
    public static Object createInstance(String className) {
        try {
            Class<?> onwClass = Class.forName(className);
            Object object = onwClass.newInstance();
            return object;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据包名+类名创建对象
     *
     * @param packName  包名
     * @param className 类名
     * @return
     */
    public static Object createInstance(String packName, String className) {
        try {
            Class<?> onwClass = Class.forName(packName + "." + className);
            Object object = onwClass.newInstance();
            return object;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据类名和静态属性名称获取静态属性值
     *
     * @param className 类名
     * @param fieldName 静态属性名称
     * @return
     */
    public static Object getStaticFieldValue(String className, String fieldName) {
        try {
            Class<?> myClass = Class.forName(className);
            Field field = myClass.getField(fieldName);
            return field.get(myClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrofit请求反射动态的加载方法
     *
     * @param t          方法所在对象
     * @param methodName 方法名字
     * @param e          请求方法参数，统一使用对象封装
     * @param <T>
     * @param <E>
     */
    public static <T, E> void retrofitRequestExecuteMethod(T t, String methodName, E e) {
        try {
            Method method = t.getClass().getMethod(methodName, e.getClass());
            method.invoke(t, e);
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
    }
}
