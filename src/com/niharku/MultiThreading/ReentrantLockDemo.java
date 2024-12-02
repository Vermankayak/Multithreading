package com.niharku.MultiThreading;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
	
	 private final ReentrantLock lock = new ReentrantLock();
	 private int count = 0;
	
	   public static void main(String args[]) throws Exception {
		   
		   ReentrantLockDemo example = new ReentrantLockDemo();
		   
	       Thread t1 = new Thread(example::increment);
	       Thread t2 = new Thread(example::increment);

	       t1.start();
	       t2.start();

	       try {
	            t1.join();
	            t2.join();
	       } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	       }

	       System.out.println("Final count is: " + example.count);
	    }
	   
	   private void recursiveIncrement(int depth) {
	        lock.lock();  // Subsequent lock acquisition by the same thread
	        try {
	            if (depth < 5) {  // Recursion depth to illustrate multiple acquisitions
	                System.out.println(Thread.currentThread().getName() + " Depth " + depth + " - Lock hold count: " + lock.getHoldCount());
	                recursiveIncrement(depth + 1);
	            }
	            count++;
	        } finally {
	            lock.unlock();  // Corresponding unlock for each lock acquisition
	        }
	    }
	   
	   // A method that acquires the lock multiple times
	    public void increment() {
	        lock.lock();  // First lock acquisition, here the monitor is lock object. Therefore lock
	        //is applied on Reentrant Lock object "lock" by UDT. It is similar to: 
//			synchronized (lock){
	        
//			        try {
//			            recursiveIncrement(1);
//			        } finally {
//			            lock.notify();  // Unlock once
//			        }
//	    
//			}
	        try {
	            recursiveIncrement(1);
	        } finally {
	            lock.unlock();  // Unlock once
	        }
	    }
}