package tests;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;

import misc.Message;
import producers.ArrayProducer;
import producers.MessageProducer;
import producers.ObjectfileProducer;
import view.Viewer;

public class TestMessageProducer {
    
	/** 
	 * @param times
	 * @param delay
	 * @return ArrayProducer
	 */
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
    
    
	/** 
	 * @param filename
	 * @param mp
	 */
	private static void writeToObjectStream(String filename, MessageProducer mp) {
    	try( FileOutputStream fos = new FileOutputStream(filename);
    			BufferedOutputStream bos = new BufferedOutputStream(fos);
    			ObjectOutputStream oos = new ObjectOutputStream(bos)) {
    		oos.writeInt(mp.times());
    		oos.writeInt(mp.delay());
    		oos.writeInt(mp.size());
    		for(int i=0; i<mp.size(); i++) {
    			oos.writeObject(mp.nextMessage());
    		}
    		oos.flush();
    	}catch(IOException e) {
    		System.err.println(e.toString());
    	}
    }
    
	
	/** 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{	
		Viewer viewer = new Viewer(300,200);
		Viewer.showPanelInFrame(viewer,"From MessageProducer",100,50);
		// MessageProducer mp = getArrayProducer(4,500);
		// MessageProducer mp = new TextfileProducer("files/new.txt");

		writeToObjectStream("files/new.dat", getArrayProducer(4, 500));
		MessageProducer mp = new ObjectfileProducer("files/new.dat");

//		writeToObjectStream("files/new.dat",getArrayProducer(4,500));
//		MessageProducer mp = new ObjectfileProducer("files/new.dat");
		Message message;
		int times = mp.times();
		int delay = mp.delay();
		int size = mp.size();
		for(int i=0; i<times; i++) {
			for(int j=0; j<size; j++) {
				message = mp.nextMessage();
				try {
					viewer.setMessage(message);
					Thread.sleep(delay);
				}catch(InterruptedException e) {}
			}
		}
	}
}
