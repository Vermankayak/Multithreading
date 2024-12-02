package com.niharku.MultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;



public class ExecutorCompletionServiceDemo {

	static ExecutorService threadpool = Executors.newFixedThreadPool(3);
	
	static ExecutorCompletionService<Integer>ecs = new ExecutorCompletionService<Integer>(threadpool);
  
  public static void main( String args[] ) throws Exception {
	  
	 int totalSum = 0;
	 
	 defineMultipleTasks();
	 int count = 2;
	 while(count > 0) {
		 
		 Future<Integer> f =  ecs.poll();
		 
		 if(f != null) {
			 totalSum += f.get();
			 count --;
		 }
	 }
	 
	 System.out.println(totalSum);
	 threadpool.shutdown();
       
  }
  
  
  public static void defineMultipleTasks() {
	  
	  Callable<Integer> addTask = () -> {
		  int sum = 0;
		  for (int i = 0; i < 1000; i++) {
			  sum += i;
		  };
		  
		  return sum;
	  };
	  
	  Callable<Integer> addTask1 = () -> {
		  int sum = 0;
		  for (int i = 0; i < 1000; i++) {
			  sum += i;
		  };
		  
		  return sum;
	  };
	  
	  ecs.submit(addTask);
	  ecs.submit(addTask1);
  }

}

