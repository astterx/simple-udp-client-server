package com.velix.updclientserver;

import java.util.concurrent.BlockingQueue;

public class DataProcessor implements Runnable {
    private final BlockingQueue<byte[]> messageQueue;

    public DataProcessor(BlockingQueue<byte[]> messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            try {
                // Take message from the queue. Will block if the queue is empty until an element becomes available
                byte[] rawData = this.messageQueue.take();
                counter++;
                System.out.println("Data processor handled " + counter + " messages");
                // Simulate a 5ms delay
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
