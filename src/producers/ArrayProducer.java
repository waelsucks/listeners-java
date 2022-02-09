package producers;

import misc.Message;

public class ArrayProducer implements MessageProducer {
	private Message[] messages;
	private int delay = 0;
	private int times = 0;
	private int currentIndex = -1;

	public ArrayProducer(Message[] messages, int times, int delay) {
		this.messages = messages;
		this.times = times;
		this.delay = delay;
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
		return (messages == null) ? 0 : messages.length;
	}

	
	/** 
	 * @return Message
	 */
	@Override
	public Message nextMessage() {
		if (size() == 0)
			return null;
		currentIndex = (currentIndex + 1) % messages.length;
		return messages[currentIndex];
	}
}
