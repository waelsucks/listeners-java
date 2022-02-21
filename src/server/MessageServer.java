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

public class MessageServer extends Thread implements PropertyChangeListener {

    ServerSocket serverSocket;
    MessageManager messageManager;
    Message currentMessage;

    ArrayList<ClientHandler> informees;

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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        currentMessage = (Message) evt.getNewValue();

        for (ClientHandler clientHandler : informees) {
            clientHandler.newMessage(currentMessage);
        }

    }

    public Message getCurrentMessage(){
        return currentMessage;
    }

}
