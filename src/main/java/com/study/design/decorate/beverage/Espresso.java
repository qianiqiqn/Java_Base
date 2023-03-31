package com.study.design.decorate.beverage;

import com.study.design.decorate.beverage.Beverage;

public class Espresso extends Beverage {

    public Espresso(String size,Double discount){
        super(size,discount);
        description = "浓缩";
    }

    @Override
    public double cost() {

        switch (size){
            case "小杯":
                money = 2.0;
                break;
            case "中杯":
                money = 1.19;
                break;
            case "大杯":
                money = 1.29;
                break;
            default:
                money = 1.99;
                break;
        }

        money = money * discount;

        return money;
    }
}
