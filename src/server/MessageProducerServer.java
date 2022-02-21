package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import producers.MessageProducer;
import producers.MessageProducerInput;

public class MessageProducerServer extends Thread{

    private ServerSocket serverSocket;
    private MessageProducerInput ipManager;

    public MessageProducerServer(MessageProducerInput ipManager, int i) {

        try {

            serverSocket = new ServerSocket(i);
            this.ipManager = ipManager;
            System.out.println("Server running...");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        MessageProducer messageProducer;
        
        while (true) {
                
            try {
                
                Socket socket = serverSocket.accept();

                System.out.println("A client has connected!");

                ObjectInputStream input = new ObjectInputStream(
                    socket.getInputStream()
                );

                ObjectOutputStream output = new ObjectOutputStream(
                    socket.getOutputStream()
                );

                messageProducer = (MessageProducer) input.readObject();
                ipManager.addMessageProducer(messageProducer);
                

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

	public void startServer() {
        start();
	}
    
}
