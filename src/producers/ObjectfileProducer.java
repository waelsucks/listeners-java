package producers;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import misc.Message;

public class ObjectfileProducer implements MessageProducer {

    private int times;
    private int size;
    private int delay;
    private Message[] messages;
    private int currentIndex;

    public ObjectfileProducer(String filepath) throws IOException {

        currentIndex = 0;
        ObjectInputStream in = null;

        try {

            in = new ObjectInputStream(
                    new FileInputStream(filepath));

            times = in.readInt();
            delay = in.readInt();
            size = in.readInt();

            messages = new Message[size];

            for (int i = 0; i < size; i++) {

                messages[i] = (Message) in.readObject();

            }

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            in.close();
        }

    }

    
    /** 
     * @return int
     */
    @Override
    public int delay() {
        return delay;
    }

    
    /** 
     * @return int
     */
    @Override
    public int times() {
        return times;
    }

    
    /** 
     * @return int
     */
    @Override
    public int size() {
        return size;
    }

    
    /** 
     * @return Message
     */
    @Override
    public Message nextMessage() {

        if (size == 0) {
            return null;
        }

        currentIndex = (currentIndex + 1) % size;
        return messages[currentIndex];

    }

}
