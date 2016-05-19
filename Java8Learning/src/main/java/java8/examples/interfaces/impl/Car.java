package java8.examples.interfaces.impl;

import java8.examples.interfaces.FourWheeler;
import java8.examples.interfaces.Vehicle;

public class Car implements Vehicle, FourWheeler {

  @Override
  public void print() {

    Vehicle.super.print();
    FourWheeler.super.print();
    Vehicle.blowHorn();
  }

}
