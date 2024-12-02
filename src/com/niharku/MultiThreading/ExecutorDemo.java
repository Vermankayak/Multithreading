package com.niharku.MultiThreading;


import java.util.concurrent.Executor;

public class ExecutorDemo {
    
	public static void main(String[] args) {
		
		Runnable r = new MyTask();
		
		Executor executor = new ExecutorImpl();
		executor.execute(r);
	}

  
}

class ExecutorImpl implements Executor{

	@Override
	public void execute(Runnable runnable) {
		
		Thread t = new Thread(runnable);
		t.start();
		
	}
	
}

class MyTask implements Runnable{

	@Override
	public void run() {
		
		System.out.println("I am running");
		
	}
	
}