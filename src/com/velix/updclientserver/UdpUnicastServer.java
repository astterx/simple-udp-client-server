package com.velix.updclientserver;

import java.io.IOException;
import java.net.*;

public class UdpUnicastServer implements Runnable {
    // Port where the client is listening
    private final int clientPort;

    public UdpUnicastServer(int clientPort) {
        this.clientPort = clientPort;
    }

    @Override
    public void run() {
         // Create a server socket and bind it to a free port.
        try(DatagramSocket socket = new DatagramSocket(50000)) {
            // Generate 10000 messages and send them to the client at a 3ms interval
            for (int i = 0; i< 10000; i++) {
                String message = "Message number " + i;
                DatagramPacket packet = new DatagramPacket(
                    message.getBytes(),
                    message.length(),
                    InetAddress.getLocalHost(),
                    clientPort
                );

                socket.send(packet);

                // Wait 3ms before sending the next message
                Thread.sleep(3);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
