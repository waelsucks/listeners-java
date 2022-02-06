package view;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import misc.Message;
import misc.MessageManager;

public class P1Viewer extends Viewer implements PropertyChangeListener {

    private MessageManager manager;

    public P1Viewer(MessageManager manager, int width, int height) {

        super(width, height);
        this.manager = manager;

        this.manager.addPropertyChangeListener(this);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setMessage((Message) evt.getNewValue());  
    }

}
