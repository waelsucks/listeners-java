package producers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;

import misc.Message;

/**
 * 
 * An implementation of the class MessageProducer, this class reads an
 * UTF-8 file and acts as a message carrier for
 * the different messages and icons in the text file. Text file must be
 * formatted in the following manner:
 * 
 * @author Wael Mahrous
 * @version 2022-02-07
 * 
 */
public class TextfileProducer implements MessageProducer {

    private int delay;
    private int times;
    private int size;
    private int currentIndex;
    private Message[] messages;

    /**
     * Creates an instance of TextfileProducer which takes a path to a text file
     * and creates an implementation of MessageProducer.
     * The text file must have the following format:
     * <p>
     * Line 0: Number of times messages will be displayed.
     * <p>
     * Line 1: The amount of time each message is displayed.
     * <p>
     * Line [ 2 + 3 ]: The amount of [ messages + icons ] the file contains.
     * 
     * @param filepath
     */

    public TextfileProducer(String filepath) {

        BufferedReader in = null;

        try {
            in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filepath),
                            "UTF-8"));

            times = Integer.parseInt(in.readLine());
            delay = Integer.parseInt(in.readLine());
            size = Integer.parseInt(in.readLine());

            currentIndex = -1;

            messages = new Message[size];

            for (int i = 0; i < size; i++) {
                messages[i] = new Message(in.readLine(), new ImageIcon(in.readLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * The amount of time each message is displayed.
     * 
     * @return int
     */
    @Override
    public int delay() {
        return delay;
    }

    /**
     * The amount of times messages are to be displayed.
     * 
     * @return int
     */
    @Override
    public int times() {
        return times;
    }

    /**
     * The amount of messages included in the Message array.
     * 
     * @return int
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the next message according to current index. Reverts back to index 0
     * after reaching end of array.
     * 
     * @return Message
     */
    @Override
    public Message nextMessage() {

        if (size == 0) {
            return null;
        }

        currentIndex = (currentIndex + 1) % size;
        return messages[currentIndex];

    }

}
