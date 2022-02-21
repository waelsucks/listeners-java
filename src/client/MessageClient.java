package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import client.CallbackInterface;
import misc.Message;

/**
 * Creates a socket which connects to the port and IP address of the MessageServer.
 * @author Adam Joseph
 * @version 2022-02-21
 */
public class MessageClient extends Thread{

    private ArrayList<CallbackInterface> informees;

    private ObjectInputStream input = null;
    private ObjectOutputStream out = null;
    private Socket socket;
    private String string;
    private int i;

    /**
     * Creates a MessageClient instance and attempts to connect to the server socket provided by the parameters.
     * @param string
     * @param i
     */

    public MessageClient(String string, int i) {

        this.string = string;
        this.i = i;
        informees = new ArrayList<CallbackInterface>();

        start();

    }

    /**
     * Adds an CallbackInterface object informee through callback to notify of new Message objects to display.
     * @param informee
     */

    public void registerInformee(CallbackInterface informee) {
        if (!informees.contains(informee)) {
            informees.add(informee);
        }
    }

    /**
     * Informs the connected informees of a change. This change would be the availability of a new Message object.
     * @param message
     */

    private void inform(Message message) {

        if (!informees.isEmpty()) {
            
            for (CallbackInterface informee : informees) {
                informee.newMessage(message);
            }

        }

    }

    /**
     * Starts the thread. Creates a socket to attempt to connect to tge server socket and waits for new Message objects through the input stream.
     */

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
