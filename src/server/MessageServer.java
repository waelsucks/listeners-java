package server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import misc.Buffer;
import misc.Message;
import misc.MessageManager;

public class MessageServer extends Thread implements PropertyChangeListener {

    ServerSocket serverSocket;
    MessageManager messageManager;

    Buffer<Message> messageList;

	public MessageServer(MessageManager messageManager, int i) {

        messageList = new Buffer<Message>();


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
            
            

        }

	}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        messageList.put((Message) evt.getNewValue());
    }


    
}
