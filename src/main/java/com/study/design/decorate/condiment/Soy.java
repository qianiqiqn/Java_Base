package com.study.design.decorate.condiment;

import com.study.design.decorate.beverage.Beverage;

public class Soy extends CondimentDecorator {

    Beverage beverage;

    public Soy(Beverage beverage){
        super(beverage.getSize(), beverage.getDiscount());
        this.beverage = beverage;
    }


    @Override
    public double cost() {
        return 0.15 + beverage.cost() ;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "，加豆浆";
    }
}
