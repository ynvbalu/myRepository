package com.mytutorial;

import akka.actor.*;

public class Producer {

  public static void main(String[] args) {
    ActorSystem system = ActorSystem.create("generate-numbers-one-to-ten");

    ActorRef printNumbersConsumer = system.actorOf(Props.create(Consumer.class));

    for (int i = 1; i <= 10; i++) {
      System.out.println(">>> Producing & sending a number " + i);
      printNumbersConsumer.tell(i, ActorRef.noSender());
    }

    system.shutdown();
    System.out.println("===== Finished producing & sending numbers 1 to 10");

  }
}
