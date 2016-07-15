package com.generics;
 
import java.util.ArrayList;
import java.util.List;
 
public class GenericsByExample {
 
    public static void main(String[] args) {
        new GenericsByExample().create();
    }
 
    public void create() {
 
        // dogs only
        List<Dog> dogs = new ArrayList<Dog>();
        dogs.add(new Dog());
        dogs.add(new SpanielDog()); //polymorphism
       // dogs.add(new Pet()); // compile error
       // dogs.add(new Cat()); // compile error
 
        // cats only
        List<Cat> cats = new ArrayList<Cat>();
        cats.add(new Cat());
        // cats.add(new Dog()); //No can't add dogs to list of cats
 
        // any pets can be added
        List<Pet> pets = new ArrayList<Pet>();
        pets.add(new Dog()); //polymorphism
        pets.add(new Cat()); //polymorphism
        pets.add(new Pet());
 
        // so, wrong to say List<Pet> is a super type of List<Dog>
        // defeats the purpose of having Generics (i.e type safety)
 
        // RHS allows Pet, Dog, Cat, SpanielDog, but read only
        List<? extends Pet> petsOnlyForReading = new ArrayList<Dog>(); 
 
        // RHS allows Dog and SpaniellDog, but read only
        List<? extends Dog> dogsOnlyForReadingy = new ArrayList<SpanielDog>();
 
       // petsOnlyForReading.add(new Dog()); // compile error
       // dogsOnlyForReadingy.add(new Cat()); // compile error
 
       // List<Dog> dogsOnly = new ArrayList<Pet>(); // compile error, see dogsOnly 1, 2 , and 3
 
        List<? super Dog> dogsOnly1 = new ArrayList<Dog>();
        dogsOnly1.add(new Dog());
        dogsOnly1.add(new SpanielDog()); // polymorphism
        //dogsOnly1.add(new Pet()); // compile error
 
        List<? super Dog> dogsOnly2 = new ArrayList<Pet>();
        dogsOnly2.add(new Dog());
        dogsOnly2.add(new SpanielDog()); // polymorphism
       // dogsOnly2.add(new Pet()); // compile error
 
        List<? super Dog> dogsOnly3 = new ArrayList<Object>();
        dogsOnly3.add(new Dog());
        dogsOnly3.add(new SpanielDog()); // polymorphism
      //  dogsOnly3.add(new Pet()); // compile error
      //  dogsOnly3.add(new Object()); // compile error
 
        List<? super SpanielDog> spanielsOrSubclassesOnly = new ArrayList<SpanielDog>();
     //   spanielsOrSubclassesOnly.add(new Dog()); // compile error
        spanielsOrSubclassesOnly.add(new SpanielDog());
     //   spanielsOrSubclassesOnly.add(new Pet()); // compile error
    }
}