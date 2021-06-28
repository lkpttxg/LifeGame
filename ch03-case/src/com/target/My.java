package com.target;

import com.service.MyInterface;

public class My implements MyInterface {
    @Override
    public String  print() {
        System.out.println("目标类方法的执行");
        return "1";
    }
}
