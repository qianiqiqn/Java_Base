package com.study.design.decorate;

import com.study.design.decorate.beverage.Beverage;
import com.study.design.decorate.beverage.Espresso;
import com.study.design.decorate.beverage.HouseBlend;
import com.study.design.decorate.condiment.Mocha;
import com.study.design.decorate.condiment.Soy;
import com.study.design.decorate.condiment.Whip;

/**
 * 装饰者模式
 */
public class Order1 {
    public static void main(String[] args) {
        Beverage beverage = new Espresso("小杯",1.0);
        System.out.println(beverage.getDescription() + ": $" + beverage.cost());

        Beverage beverage2 = new Espresso("小杯",0.9);
        System.out.println(beverage2.getDescription() + ": $" + beverage2.cost());

        beverage2 = new Mocha(beverage2);
        System.out.println(beverage2.getDescription() + ": $" + beverage2.cost());

        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + ": $" + beverage2.cost());

        Beverage beverage3 = new HouseBlend("大杯",1.0);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        beverage3 = new Soy(beverage3);
        System.out.println(beverage3.getDescription() + ": $" + beverage3.cost());
    }
}
