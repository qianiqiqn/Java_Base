package com.study.design.decorate.beverage;

public abstract class Beverage {

    String description = "饮料";

    String size = "小杯";

    Double discount = 1.0;

    Double money;

    public String getSize() {
        return size;
    }

    public Double getDiscount() {
        return discount;
    }


    public Beverage(String size,Double discount){
        this.size = size;
        this.discount = discount;
    }


    public String getDescription(){
        return size + description;
    }

    public abstract double cost();

}
