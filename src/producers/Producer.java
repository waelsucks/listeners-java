package producers;

import misc.Buffer;
import misc.Message;

public class Producer extends Thread {

    private Buffer<MessageProducer> producerBuffer;
    private Buffer<Message> messageBuffer;

    public Producer(Buffer<MessageProducer> producerBuffer, Buffer<Message> messageBuffer) {
        this.producerBuffer = producerBuffer;
        this.messageBuffer = messageBuffer;
    }

    public void run() {

        try {

            MessageProducer in;
            in = producerBuffer.get();

            for (int i = 0; i < in.times(); i++) {

                for (int j = 0; j < in.size(); j++) {

                    messageBuffer.put(in.nextMessage());
                    sleep(in.delay());

                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
