package com.study.classin;

public abstract class Animal {

    private String a;

    public abstract void run(String action);

    public Animal(String a){
        this.a = a;
    }

    public void speak(String s){
        System.out.println(s);
    };

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
