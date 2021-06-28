import com.handler.MyHandler;
import com.service.MyInterface;
import com.target.My;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Proxy;

public class Main {

    /*
    步骤总结：
        1.创建目标类
        2.注册目标类进行调用选择器（增强方法的书写）
        3.创建代理对象,并将其转型为目标接口类
        4.调用代理对象的方法
     */


    public static void main(String[] args) {
        //1.创建目标类
        My my = new My();
        //2.实现调用处理器接口实现类,主要就是调用其invoke
        MyHandler myHandler = new MyHandler(my);
        //3.获得代理对象
        MyInterface obj = (MyInterface)Proxy.newProxyInstance(my.getClass().getClassLoader(), my.getClass().getInterfaces(),myHandler);
        //4.代理对象后，实现方法
        String a=obj.print();
        System.out.println(a);
    }
}
