package com.niharku.MultiThreading;

public class ReorderingEffect {

    // shared variables
    int sharedA = 0;
    int sharedB = 0;

    // executed by thread1
    void method1() {
        int localA;
        localA = sharedA;
        sharedB = 1;
        System.out.println("localA = " + localA);
    }

    // executed by thread2
    void method2() {
        int localB;
        localB= sharedB;
        sharedA = 2;
        System.out.println("localB = " + localB);
    }

    public static void main(String[] args) throws InterruptedException {
        final ReorderingEffect reorderingEffect = new ReorderingEffect();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
            	reorderingEffect.method1();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
            	reorderingEffect.method2();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}