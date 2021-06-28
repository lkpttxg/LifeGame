package com.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.security.PrivateKey;
import java.util.prefs.PreferenceChangeEvent;

//必须实现InvocationHandler接口，完成代理类要做的功能(1.调用目标方法，2.功能增强)
public class MySellHandler implements InvocationHandler {
    private Object target = null;

    //动态代理，目标对象是活动的，不是固定的，需要传入进来
    //传入是谁，给谁代理。
    public MySellHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res = null;
        //执行目标方法
        res = method.invoke(target,args);

        if(res!=null){
            Float price = (Float)res;
            price+=25;
            res=price;
        }

        //增加的价格
        return res;
    }
}
