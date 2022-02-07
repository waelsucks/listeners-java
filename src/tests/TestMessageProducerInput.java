package tests;

import java.io.IOException;

import javax.swing.ImageIcon;

import misc.Buffer;
import misc.Message;

import producers.ArrayProducer;
import producers.MessageProducer;
import producers.MessageProducerInput;
import producers.TextfileProducer;

public class TestMessageProducerInput {
    private static ArrayProducer getArrayProducer(int times, int delay) {
    	Message[] messages = { new Message("-- UP",new ImageIcon("images/new1.jpg")),
    			new Message("-- Going down.",new ImageIcon("images/new2.jpg")),
    			new Message("-- Going down..",new ImageIcon("images/new3.jpg")),
    			new Message("-- Going down...",new ImageIcon("images/new4.jpg")),
    			new Message("-- Going down....",new ImageIcon("images/new5.jpg")),
    			new Message("-- Almost down",new ImageIcon("images/new6.jpg")),
    			new Message("-- DOWN",new ImageIcon("images/new7.jpg")),
    			new Message("-- Going up.",new ImageIcon("images/new8.jpg")),
    			new Message("-- Going up..",new ImageIcon("images/new9.jpg")),
    			new Message("-- Almost up",new ImageIcon("images/new10.jpg")) };
        return new ArrayProducer(messages,times,delay);       
    }
 
    public static void main(String[] args) throws IOException{

        Buffer<MessageProducer> producerBuffer = new Buffer<MessageProducer>();
                
        MessageProducerInput mpInput = new MessageProducerInput(producerBuffer);

        mpInput.addMessageProducer(getArrayProducer(1,1000));
        mpInput.addMessageProducer(new TextfileProducer("files/new.txt"));
        
        MPConsumer consumer = new MPConsumer(producerBuffer);
        consumer.start();
        MPConsumer consumer2 = new MPConsumer(producerBuffer);
        consumer2.start();
    }
    
}

class MPConsumer extends Thread {
	private Buffer<MessageProducer> buffer;
	
	public MPConsumer(Buffer<MessageProducer> buffer) {
		this.buffer = buffer;
	}
	
	public void run() {
		MessageProducer mp;
		while(!Thread.interrupted()) {
			try {
				mp = buffer.get();
				populateProducerToConsole(mp);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	private void populateProducerToConsole(MessageProducer producer) throws InterruptedException {
		Message message;
	    for(int times=0; times<producer.times(); times++) {
	    	for(int index = 0; index<producer.size(); index++) {
	    		message = producer.nextMessage();
	    		System.out.println(message.getIcon()+", "+message.getText());
	    		Thread.sleep(producer.delay());
	    	}
	    }
	}
}