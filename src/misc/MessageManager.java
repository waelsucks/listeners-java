package misc;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MessageManager extends Thread {

    private Buffer<Message> messageBuffer;
    private Message m;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public MessageManager(Buffer<Message> messageBuffer) {
        this.messageBuffer = messageBuffer;
        m = null;
    }

    @Override
    public void run() {

        while (!interrupted()) {
            try {

                m = messageBuffer.get();
                support.firePropertyChange("message", null, m);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    

}
