package com.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//实现了调用处理器接口：对目标对象执行的新方法
public class MyHandler implements InvocationHandler {
    private Object target;

    public MyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获得原数据
        method.invoke(target,args);
        //新的东西
        String st = "成功";
        return st;
    }
}
