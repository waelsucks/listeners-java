package producers;

import misc.Buffer;

public class MessageProducerInput {
    
    private Buffer<MessageProducer> producerBuffer;

    public MessageProducerInput(Buffer<MessageProducer> thatProducerBuffer) {

        this.producerBuffer = thatProducerBuffer;

    }

    public void addMessageProducer(MessageProducer mp) {
        producerBuffer.put(mp);
    } 

}
