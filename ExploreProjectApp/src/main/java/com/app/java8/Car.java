package com.app.java8;

public class Car implements Vehicle, FourWheeler {

  @Override
  public void print() {

    Vehicle.super.print();
    FourWheeler.super.print();
    Vehicle.blowHorn();
  }

}
