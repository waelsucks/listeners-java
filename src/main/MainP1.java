package main;

import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;

import misc.Buffer;
import misc.Message;
import misc.MessageManager;
import producers.ArrayProducer;
import producers.MessageProducer;
import producers.MessageProducerInput;
import producers.Producer;
import producers.TextfileProducer;
import view.MessageInputPanel;
import view.P1Viewer;
import view.Viewer;

public class MainP1 {
	public static void main(String[] args) throws IOException {
		Buffer<Message> messageBuffer = new Buffer<Message>();
		Buffer<MessageProducer> producerBuffer	= new Buffer<MessageProducer>();
		
		MessageManager messageManager = new MessageManager(messageBuffer);
		P1Viewer v1 = new P1Viewer(messageManager, 300, 200);
		P1Viewer v2 = new P1Viewer(messageManager, 320, 320);
		P1Viewer v3 = new P1Viewer(messageManager, 200, 400);
		P1Viewer v4 = new P1Viewer(messageManager, 200, 400);
		
		Viewer.showPanelInFrame(v1, "Viewer 1", 100, 50);
		Viewer.showPanelInFrame(v2, "Viewer 2", 450, 50);
		Viewer.showPanelInFrame(v3, "Viewer 3", 800, 200);
		Viewer.showPanelInFrame(v4, "Viewer 4", 200, 200);
		messageManager.start();
		
		Producer producer = new Producer(producerBuffer,messageBuffer);
		producer.start();
		
		MessageProducerInput ipManager = new MessageProducerInput(producerBuffer);
		// ipManager.addMessageProducer(getArrayProducer(10,100));
		ipManager.addMessageProducer(new ShowGubbe(3000));
		// ipManager.addMessageProducer(new TextfileProducer("files/new.txt"));

		MessageInputPanel inputPanel = new MessageInputPanel(ipManager, messageManager);

	}
	
    private static ArrayProducer getArrayProducer(int times, int delay) {
    	Message[] messages = { new Message("UP",new ImageIcon("images/new1.jpg")),
    			new Message("Going down.",new ImageIcon("images/new2.jpg")),
    			new Message("Going down..",new ImageIcon("images/new3.jpg")),
    			new Message("Going down...",new ImageIcon("images/new4.jpg")),
    			new Message("Going down....",new ImageIcon("images/new5.jpg")),
    			new Message("Almost down",new ImageIcon("images/new6.jpg")),
    			new Message("DOWN",new ImageIcon("images/new7.jpg")),
    			new Message("Going up.",new ImageIcon("images/new8.jpg")),
    			new Message("Going up..",new ImageIcon("images/new9.jpg")),
    			new Message("Almost up",new ImageIcon("images/new10.jpg")) };
        return new ArrayProducer(messages,times,delay);       
    }

	public static void spawnViewer(MessageManager manager) {

		Random random = new Random();

		P1Viewer viewer = new P1Viewer(manager, 300, 200);
		Viewer.showPanelInFrame(viewer, "Viewer", random.nextInt(800), random.nextInt(800));
	}

}

class ShowGubbe implements MessageProducer {
	private int delay;
	
	public ShowGubbe(int delay) {
		this.delay = delay;
	}

	@Override
	public int delay() {
		return delay;
	}

	@Override
	public int times() {
		return 1;
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public Message nextMessage() {
		return new Message("Hi folks...",new ImageIcon("images/gubbe.jpg"));
	}
	
}

