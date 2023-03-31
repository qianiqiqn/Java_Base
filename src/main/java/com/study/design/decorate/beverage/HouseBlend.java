package com.study.design.decorate.beverage;

import com.study.design.decorate.beverage.Beverage;

public class HouseBlend extends Beverage {

    public HouseBlend(String size,Double discount){
        super(size,discount);
        description = "综合";
    }


    @Override
    public double cost() {
        switch (size){
            case "小杯":
                money = 0.89;
                break;
            case "中杯":
                money = 1.09;
                break;
            case "大杯":
                money = 1.19;
                break;
            default:
                money = 0.89;
                break;
        }

        money = money * discount;

        return money;
    }
}
