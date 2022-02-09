package misc;
import java.util.LinkedList;

public class Buffer<T> {
	private LinkedList<T> buffer = new LinkedList<T>();
	
	
	/** 
	 * @param obj
	 */
	public synchronized void put(T obj) {
		buffer.addLast(obj);
		notifyAll();
	}
	
	
	/** 
	 * @return T
	 * @throws InterruptedException
	 */
	public synchronized T get() throws InterruptedException {
		while(buffer.isEmpty()) {
			wait();
		}
		return buffer.removeFirst();
	}
	
	
	/** 
	 * @return int
	 */
	public int size() {
		return buffer.size();
	}
}
