package producers;

import misc.Buffer;
import misc.Message;

/**
 * 
 * A single dedicated thread that takes MessageProducer instances from
 * a buffer and places the messages they
 * contain into a seperate message buffer for later consumption.
 * 
 * @author Wael Mahrous
 * @version 2022-02-07
 * 
 */

public class Producer extends Thread {

    private Buffer<MessageProducer> producerBuffer;
    private Buffer<Message> messageBuffer;

    /**
     * Creates a Producer instance that moves messages from the given
     * MessageProducer buffer and places them in the given Message buffer.
     * 
     * @param producerBuffer
     * @param messageBuffer
     */

    public Producer(Buffer<MessageProducer> producerBuffer, Buffer<Message> messageBuffer) {
        this.producerBuffer = producerBuffer;
        this.messageBuffer = messageBuffer;
    }

    /**
     * Starts the thread.
     * <p>
     * Until the thread is interrupted, this process will
     * continuously take Message objects from each MessageProducer instance
     * in the MessageProducer buffer and go through their messages using the
     * MessageProducer parameters. It places the Message objects and places them
     * in a seperate buffer for Message objects.
     */

    public void run() {

        try {

            MessageProducer in;

            while (!interrupted()) {

                in = producerBuffer.get();

                for (int i = 0; i < in.times(); i++) {

                    for (int j = 0; j < in.size(); j++) {

                        messageBuffer.put(in.nextMessage());
                        sleep(in.delay());

                    }

                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
