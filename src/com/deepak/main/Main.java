package com.deepak.main;

public class Main {
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
