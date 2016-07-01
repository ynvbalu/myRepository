package com.naga.java.beginning;
public class Car extends Machine1 {
     
     
    @Override
    public void start() {
        System.out.println("Car started");
    }
 
    public void wipeWindShield() {
        System.out.println("Wiping windshield");
    }
     
    public void showInfo() {
        System.out.println("Car name: " + name);
    }
}