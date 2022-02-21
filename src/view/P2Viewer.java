package view;

import client.CallbackInterface;
import client.MessageClient;
import misc.Message;

public class P2Viewer extends Viewer implements CallbackInterface{


    public P2Viewer(MessageClient messageClient1, int width, int height) {
        super(width, height);

        messageClient1.registerInformee(this);
    }

    @Override
    public void newMessage(Message message) {
        setMessage(message);
    }
    
}
