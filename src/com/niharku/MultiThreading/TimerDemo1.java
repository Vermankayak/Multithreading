package com.niharku.MultiThreading;

import java.util.Timer;
import java.util.TimerTask;

class TimerDemo1 {
    public static void main( String args[] ) throws Exception{
        
        Timer timer = new Timer();
        TimerTask badTask = new TimerTask() {

            @Override
            public void run() {
                throw new RuntimeException("Something Bad Happened");
            }
        };

        TimerTask goodTask = new TimerTask() {

            @Override
            public void run() {
                System.out.println("Hello I am a well-behaved task");
            }
        };

        timer.schedule(badTask, 10);
        Thread.sleep(500);
        timer.schedule(goodTask, 10);
    }
}