package p2;

import javax.swing.ImageIcon;

import p1.ArrayProducer;
import p1.Buffer;
import p1.Message;
import p1.MessageManager;
import p1.MessageProducer;
import p1.MessageProducerInput;
import p1.P1Viewer;
import p1.Producer;
import p1.Viewer;

public class TestP2Input {
	public static void main(String[] args) {
		Buffer<Message> messageBuffer = new Buffer<Message>();
		Buffer<MessageProducer> producerBuffer	= new Buffer<MessageProducer>();
		
		MessageManager messageManager = new MessageManager(messageBuffer);
		P1Viewer v1 = new P1Viewer(messageManager, 300, 200);
		P1Viewer v2 = new P1Viewer(messageManager, 320, 320);
		P1Viewer v3 = new P1Viewer(messageManager, 200, 400);
		Viewer.showPanelInFrame(v1, "Viewer 1", 100, 50);
		Viewer.showPanelInFrame(v2, "Viewer 2", 450, 50);
		Viewer.showPanelInFrame(v3, "Viewer 3", 800, 50);
		messageManager.start();
		
		Producer producer = new Producer(producerBuffer,messageBuffer);
		producer.start();
		
		MessageProducerInput ipManager = new MessageProducerInput(producerBuffer);		
		
		MessageProducerServer mps = new MessageProducerServer(ipManager,1343);
		mps.startServer();
		
		MessageProducerClient mpc = new MessageProducerClient("127.0.0.1",1343);
		mpc.send(getArrayProducer(10,100)); // kopplar upp, överför, kopplar ner
		mpc.send(new ShowGubbe(3000)); // kopplar upp, överför, kopplar ner
	}
	
    public static ArrayProducer getArrayProducer(int times, int delay) {
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
}
