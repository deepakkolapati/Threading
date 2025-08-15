package com.deepak.main;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerUsingBlockingQueue {


    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        Thread producerThread = new Thread(()->{
                    try {
                        for(int i=1;i<=10;i++){
                            queue.add(i);
                            System.out.println("Produced: "+ i);
                            Thread.sleep(100);
                            }
                    }catch (InterruptedException e) {
                        throw new RuntimeException(e);
                        }
                });

        Thread consumerThread = new Thread(()->{
            try{
                for(int i=1;i<=10;i++){
                    int value = queue.poll();
                    System.out.println("Consumed: "+ i);
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producerThread.start();
        consumerThread.start();

    }

}
