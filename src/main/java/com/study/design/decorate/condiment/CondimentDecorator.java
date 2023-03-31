package com.study.design.decorate.condiment;

import com.study.design.decorate.beverage.Beverage;

public abstract class CondimentDecorator extends Beverage {

    public CondimentDecorator(String size, Double discount) {
        super(size, discount);
    }

    @Override
    public abstract String getDescription();

}
