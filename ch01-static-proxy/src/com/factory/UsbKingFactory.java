package com.factory;

import com.service.UsbSell;

public class UsbKingFactory implements UsbSell {
    @Override
    public float sell(int amount) {
        //一个128G的U盘是85元
        //后期根据amount，可以实现不同的价格，例如10000个单价是80；50000个是75
        return 85.0f;
    }
}
