package com.velix.updclientserver;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        int port = 50001;
        BlockingQueue<byte[]> messageQueue = new ArrayBlockingQueue<>(2700);
        UdpUnicastServer server = new UdpUnicastServer(port);
        UdpUnicastClient client = new UdpUnicastClient(port, messageQueue);
        DataProcessor dataProcessor = new DataProcessor(messageQueue);

        // Execute the components as 3 different threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(client);
        executorService.submit(server);
        executorService.submit(dataProcessor);
    }
}
