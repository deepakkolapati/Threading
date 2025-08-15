package com.deepak.main;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    int capacity;
    Queue <Integer> queue = new LinkedList<>();

    public synchronized void produce(int value) throws InterruptedException {
        while(queue.size() == capacity){
            wait();
        }
        queue.add(value);
        System.out.println("produced :" + value);
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while(queue.isEmpty()){
            wait();
        }
        int value = queue.poll();
        System.out.println("consumed :" + value);
        notifyAll();
    }

    public ProducerConsumer(int capacity){
        this.capacity = capacity;

    }
}
