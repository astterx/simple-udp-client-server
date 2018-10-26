package com.velix.updclientserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;

public class UdpUnicastClient implements Runnable {
    private final int port;
    private final BlockingQueue<byte[]> messageQueue;

    public UdpUnicastClient(int port, BlockingQueue<byte[]> messageQueue) {
        this.port = port;
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        // Bind client socket to the port expected to receive incoming message
        try(DatagramSocket socket = new DatagramSocket(port)) {
            // Set a timeout of 3000ms for the client
            socket.setSoTimeout(3000);
            while (true) {
                /**
                 * Create a byte array buffer to store incoming data. If the message length exceeds length of buffer
                 * the message will be truncated. To avoid that buffer will be instantiated with maximum UDP packet size
                 */
                byte[] buffer = new byte[65507];
                DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);

                // Receive method will wait for 3000ms for data. If now message is received after 3000ms, client will throw a timeout exception
                socket.receive(packet);

                this.messageQueue.put(packet.getData());
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Timeout. Client is closing.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
