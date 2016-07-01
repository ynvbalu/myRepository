package com.naga.java.beginning;

class Frog1 {
     
    private int id;
    private String name;
     
    public Frog1(int id, String name) {
        this.id = id;
        this.name = name;
    }
     
    public String toString() {
         
        return String.format("%-4d: %s", id, name);
         
        /*
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(": ").append(name);
         
        return sb.toString();
        */
    }
}
 
public class ToStringMethod {
 
    public static void main(String[] args) {
        Frog1 frog1 = new Frog1(7, "Freddy");
        Frog1 frog2 = new Frog1(5, "Roger");
         
        System.out.println(frog1);
        System.out.println(frog2);
    }
}