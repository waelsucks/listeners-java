package producers;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;

import misc.Message;

public class TextfileProducer extends Serialize implements MessageProducer {

    private int delay;
    private int times;
    private int size;
    private int currentIndex;
    private Message[] messages;

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
            currentIndex = 0;

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

    @Override
    public int delay() {
        return delay;
    }

    @Override
    public int times() {
        return times;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Message nextMessage() {

        if (size == 0) {
            return null;
        }

        currentIndex = (currentIndex + 1) % size;
        return messages[currentIndex];

    }

}
