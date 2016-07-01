package com.naga.java.beginning;

public class Inheritance {
 
    public static void main(String[] args) {
        Machine1 mach1 = new Machine1();
         
        mach1.start();
        mach1.stop();
         
        Car car1 = new Car();
         
        car1.start();
        car1.wipeWindShield();
        car1.showInfo();
        car1.stop();
         
         
    }
 
}