package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import producers.MessageProducer;

public class MessageProducerClient {

    private ObjectInputStream input = null;
    private ObjectOutputStream out = null;
    private Socket socket;
    private String string;
    private int i;

    public MessageProducerClient(String string, int i) {
        
        this.string = string;
        this.i = i; 

    }

    public void send(MessageProducer showGubbe) {

        try {

            socket = new Socket(string, i);

            out = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            out.writeObject(showGubbe);
            out.flush();

            out.close();
            input.close();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void sendMessage(String string) {

        try {

            out.writeUTF(string);

            out.close();
            input.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}
