package com.study.design.decorate.condiment;

import com.study.design.decorate.beverage.Beverage;
import com.study.design.decorate.condiment.CondimentDecorator;

public class Whip extends CondimentDecorator {
    Beverage beverage;

    public Whip(Beverage beverage){
        super(beverage.getSize(), beverage.getDiscount());
        this.beverage = beverage;
    }


    @Override
    public double cost() {
        return 0.10 + beverage.cost() ;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "，加奶泡";
    }
}
