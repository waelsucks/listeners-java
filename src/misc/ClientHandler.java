package misc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.CallbackInterface;
import server.MessageServer;

public class ClientHandler extends Thread implements CallbackInterface{

    private Socket socket;
    private MessageServer messageServer;
    private ObjectInputStream input;
    private ObjectOutputStream out;

    public ClientHandler(Socket socket, MessageServer messageServer) {

        this.socket = socket;
        this.messageServer = messageServer;

        start();
    }

    @Override
    public void run() {

        try {
            out = new ObjectOutputStream(
                    socket.getOutputStream());

            input = new ObjectInputStream(
                    socket.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void newMessage(Message message) {
        
        try {
            out.writeObject(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
