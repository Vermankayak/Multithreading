package com.niharku.MultiThreading;



import java.util.*;

public class CriticalSectionAndRaceConditionRectified {

	public static void main(String[] args) throws InterruptedException {
		RaceCondition1.runTest();

	}

}



class RaceCondition1 {

    int randInt;
    Random random = new Random(System.currentTimeMillis());

    void printer() {

        int i = 1000000;
        while (i != 0) {
            synchronized(this) {
              if (randInt % 5 == 0) {
                  if (randInt % 5 != 0)
                    System.out.println(randInt);
              }
            }
            i--;
        }
    }

    void modifier() {

        int i = 1000000;
        while (i != 0) {
            synchronized(this) {
              randInt = random.nextInt(1000);
              i--;
            }
        }
    }

    public static void runTest() throws InterruptedException {


        final RaceCondition rc = new RaceCondition();
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                rc.printer();
            }
        });
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                rc.modifier();
            }
        });

        
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}