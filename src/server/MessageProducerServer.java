package server;

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
            System.out.println("MessageProducer server running...");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        
        while (true) {
                
            try {
                
                Socket socket = serverSocket.accept();

                ObjectInputStream input = new ObjectInputStream(
                    socket.getInputStream()
                );

                ObjectOutputStream out = new ObjectOutputStream(
                    socket.getOutputStream()
                );
                
                ipManager.addMessageProducer((MessageProducer) input.readObject());
                

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

	public void startServer() {
        start();
	}
    
}
