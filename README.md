# udp-client-server
### UDP client-server using Producer Consumer pattern

Server will send a fixed number of message to client at an interval of 3 milliseconds.<br/>
Client will run in a separate thread and will read received messages. <br/>
Each message will be placed in a blocking queue. The client will not waste time with the message processing and can go back to listening for another messages.
A Data Processor will also run on it’s own thread and it will take messages from the blocking queue and execute the handling logic.
