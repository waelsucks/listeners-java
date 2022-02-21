package misc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import server.MessageServer;

public class ClientHandler extends Thread {

    private Socket socket;
    private MessageServer messageServer;

    public ClientHandler(Socket socket, MessageServer messageServer) {

        this.socket = socket;
        this.messageServer = messageServer;

        start();
    }

    @Override
    public void run() {

        try {
            ObjectInputStream input = new ObjectInputStream(
                    socket.getInputStream());

            ObjectOutputStream out = new ObjectOutputStream(
                    socket.getOutputStream());
            
            out.writeObject(messageServer.getCurrentMessage());
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
