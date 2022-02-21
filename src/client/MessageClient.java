package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import client.CallbackInterface;
import misc.Message;

public class MessageClient extends Thread{

    private ArrayList<CallbackInterface> informees;

    private ObjectInputStream input = null;
    private ObjectOutputStream out = null;
    private Socket socket;
    private String string;
    private int i;

    public MessageClient(String string, int i) {

        this.string = string;
        this.i = i;
        informees = new ArrayList<CallbackInterface>();

        start();

    }

    public void registerInformee(CallbackInterface informee) {
        if (!informees.contains(informee)) {
            informees.add(informee);
        }
    }

    private void inform(Message message) {

        if (!informees.isEmpty()) {
            
            for (CallbackInterface informee : informees) {
                informee.newMessage(message);
            }

        }

    }

    @Override
    public void run() {
        try {
            
            socket = new Socket(string, i);

            out = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            // We want to inform only when there's a new message.

            while (true) {
                Message newMessage = (Message) input.readObject();
                inform(newMessage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
