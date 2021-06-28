import com.factory.UsbKingFactory;
import com.handler.MySellHandler;
import com.service.UsbSell;

import javax.sound.midi.Soundbank;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.logging.Handler;

public class MainShop {
    public static void main(String[] args) {
        //创建代理对象，使用Proxy
        //1.创建目标对象
        UsbSell factory = new UsbKingFactory();
        //2.创建InvocationHandler对象
        InvocationHandler h = new MySellHandler(factory);
        //3.创建代理对象
        UsbSell proxy=(UsbSell)Proxy.newProxyInstance(factory.getClass().getClassLoader(), factory.getClass().getInterfaces(), h);
        //4.通过代理执行方法
        float price = proxy.sell(1);
        System.out.println("动态代理："+price);
    }
}
