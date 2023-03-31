package com.study.classin;

public class Cat extends Animal{

    private String b;

    public Cat(String a,String b) {
        super(a);
        this.b = b;
    }

    @Override
    public void run(String action)
    {
        System.out.println(super.getA() +","+ this.b);
        System.out.println(action);

    }

}
