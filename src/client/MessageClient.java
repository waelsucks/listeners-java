package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import client.CallbackInterface;

public class MessageClient extends CallbackInterface {

    private ObjectInputStream input = null;
    private ObjectOutputStream out = null;
    private Socket socket;
    private String string;
    private int i;

    public MessageClient(String string, int i) {

        this.string = string;
        this.i = i;

        try {
            
            socket = new Socket(string, i);

            out = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            // CALLBACK

            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
