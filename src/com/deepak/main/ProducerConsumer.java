package com.deepak.main;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

//    wait()
//    Called inside a synchronized block/method.
//    Makes the current thread release the lock on the object and go into waiting state.
//    The thread stays paused until another thread calls notify() or notifyAll() on the same object.
//    Used when a condition isn’t ready yet (e.g., queue is full or empty).

//    notifyAll()
//    Wakes up all threads that are waiting on the same object’s monitor.
//    The awakened threads still need to reacquire the lock before continuing.
//    Typically used after changing a shared resource so other threads can re-check their conditions.

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

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer(10);
        Thread producer = new Thread(()-> {
            try{
                for(int i =1; i<=10;i++){
                    pc.produce(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        Thread consumer = new Thread(()->
        {try{
            for(int i =1;i<=10;i++){
                pc.consume();
                Thread.sleep(150);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        });

        producer.start();
        consumer.start();
    }
}
