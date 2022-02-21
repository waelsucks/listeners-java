package server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import misc.ClientHandler;
import misc.Message;
import misc.MessageManager;

/**
 * This single threaded class acts as a middleman between the MessageManager and the MessageClient classes.
 * It recieves new Message objects from the MessageManager and lets the connected clients know there is
 * a new message to display.
 * 
 * @author Adam Joseph
 * @version 2022-02-21
 */

public class MessageServer extends Thread implements PropertyChangeListener {

    ServerSocket serverSocket;
    MessageManager messageManager;
    Message currentMessage;

    ArrayList<ClientHandler> informees;

    /**
     * Creates an instance of the MessageServer class. Parameters are a MessageManager to interpret and a port to listen on.
     * @param messageManager
     * @param i
     */

	public MessageServer(MessageManager messageManager, int i) {

        currentMessage = null;
        informees = new ArrayList<ClientHandler>();

        try {

			serverSocket = new ServerSocket(i);
            this.messageManager = messageManager;
            this.messageManager.addPropertyChangeListener(this);

			start();

		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}

    /**
     * Starts the thread. This method waits for sockets from the server socket on the given port and serves each client an exclusive
     * ClientHandler object. The ClientHandler acts on an own thread and keeps the MessageClient sockets updated on when new Message objects are
     * available.
     */

	@Override
	public void run(){
		
        while (!interrupted()) {
            
            try {

                Socket socket = serverSocket.accept();

                System.out.println("A client has connected! Serving a handler.");

                informees.add(new ClientHandler(socket, this));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

	}

    
    /**
     * Executes whenever a new Message object is available in the MessageManager and notifies the connected ClientHandler
     * instances of the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        currentMessage = (Message) evt.getNewValue();

        for (ClientHandler clientHandler : informees) {
            clientHandler.newMessage(currentMessage);
        }

    }

    /**
     * Returns the current message to be displayed.
     * @return Message object to currently display.
     */

    public Message getCurrentMessage(){
        return currentMessage;
    }

}
