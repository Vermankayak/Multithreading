package com.niharku.MultiThreading;

public class InterruptedDemo {
	 public static void main(String args[]) throws InterruptedException {
	        InterruptExample.example();
	 }
}

class InterruptExample {

    static public void example() throws InterruptedException {

        final Thread sleepyThread = new Thread(new Runnable() {

            public void run() {
                try {
                	System.out.println("The interrupt flag is cleard to: " + Thread.currentThread().isInterrupted());
                    System.out.println("I am too sleepy... Let me sleep for an hour.");
                    Thread.sleep(1000 * 60 * 60);
                } catch (InterruptedException ie) {
//                    System.out.println("The interrupt flag is cleard : " + Thread.interrupted() + " " + Thread.currentThread().isInterrupted());                  
//                    Thread.currentThread().interrupt();
                    System.out.println("Oh someone woke me up ! ");
                    //System.out.println("The interrupt flag is set now : " + Thread.currentThread().isInterrupted() + " " + Thread.interrupted());                                    
                    System.out.println("The interrupt flag is set now : " + Thread.currentThread().isInterrupted());
                }
            }
        });

        sleepyThread.start();

        System.out.println("About to wake up the sleepy thread ...");
        System.out.println("Sleepy thread is interrupted :: " + sleepyThread.isInterrupted());
        sleepyThread.interrupt();
        System.out.println("Sleepy thread is interrupted :: " + sleepyThread.isInterrupted());
        System.out.println("Woke up sleepy thread ...");
        Thread.sleep(2000);
        sleepyThread.join();
    }
}