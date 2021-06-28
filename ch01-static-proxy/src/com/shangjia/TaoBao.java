package com.shangjia;

import com.factory.UsbKingFactory;
import com.service.UsbSell;

//是一个商家，代理金士顿u盘的销售
public class TaoBao implements UsbSell {
    //声明 商家代理的厂家具体是谁
    private  UsbSell factory = new UsbKingFactory();
    @Override
    //实现销售u盘功能
    public float sell(int amount) {
        //向厂家发送订单，告诉商家，我买了u盘，厂家发货
        float price = factory.sell(amount);//厂家的价格
        //商家加价,代理要增加价格
        price+=25;//增强功能，代理类在完成目标类调用方法后，增强了功能。
        //在目标类的方法调用后，你做其他功能，都是增强的意思。
        System.out.println("淘宝商家，给你返回一个优惠卷，或者红包");
        return price;
    }
}
