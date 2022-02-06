package producers;
import misc.Message;

public interface MessageProducer {
	public int delay();
	public int times();
	public int size();
	public Message nextMessage();
	
	default void info() {
		System.out.println("times="+times()+", delay="+delay()+", size="+size()+"]");
	}
}
