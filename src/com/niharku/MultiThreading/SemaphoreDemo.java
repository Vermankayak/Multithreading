package com.niharku.MultiThreading;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	
	// Below we have created semaphore with 0 initial permits. This means any thread
	// that calls acquire() on the semaphore will block until
	// a permit becomes available.
	private static Semaphore semaphore = new Semaphore(0);
	
	public static void main(String[] args) {
		
		Thread threadA = new Thread(new WorkerA());
        Thread threadB = new Thread(new WorkerB());

        threadA.start();
        threadB.start();
		
	}
	
	// Thread A tries to acquire the semaphore
    static class WorkerA implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread A is waiting to acquire the semaphore...");
            try {
                semaphore.acquire();//Any thread that calls .release() on semaphore decreases -
                // - the number of permit on semaphore by 1 if number of permits on semaphore > 0-
                // - else thread has to wait to acquire lock until number of permits on semaphore-
                // - increases to 1. The number of permits on semaphore increases to 1 if any thread-
                // - calls .release() on Semaphore.
                System.out.println("Thread A acquired the semaphore and is proceeding.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread A was interrupted.");
            }
        }
    }
    
    
 // Thread B releases the semaphore, allowing Thread A to proceed
    static class WorkerB implements Runnable {
        @Override
        public void run() {
            try {
                // Simulate some work with Thread.sleep()
                Thread.sleep(2000); // Wait for 2 seconds before releasing
                System.out.println("Thread B is releasing the semaphore...");
                semaphore.release();//Any thread that calls .release() on semaphore increases -
                // - the number of permit on semaphore by 1
                System.out.println("Thread B has released the semaphore.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread B was interrupted.");
            }
        }
    }
}