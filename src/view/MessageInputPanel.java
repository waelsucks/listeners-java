package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.MainP1;
import misc.Message;
import misc.MessageManager;
import producers.ArrayProducer;
import producers.MessageProducerInput;

public class MessageInputPanel extends JOptionPane implements Runnable {

    JFrame f;
    Thread thread;

    MessageProducerInput ipManager;
    MessageManager manager;

    public MessageInputPanel(MessageProducerInput ipManager, MessageManager manager) {

        f = new JFrame();
        thread = new Thread(this);

        this.ipManager = ipManager;
        this.manager = manager;

        thread.start();

    }

    private void command(String[] commands) {
        for (String string : commands) {

            String[] temp = string.split(" ");

            switch (temp[0]) {

                case "spawn":

                    for (int i = 0; i < Integer.parseInt(temp[1]); i++) {
                        MainP1.spawnViewer(manager);
                    }

                    break;

                default:
                    break;
            }

        }
    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {

            String message = JOptionPane.showInputDialog(f, "Enter Message.");
            int times = 1;

            String[] options = message.split("/");
            message = options[0];

            if (options.length == 2) {
                times = Integer.parseInt(options[1]);
            }

            // if (message.contains("-spawn")) {

            // String[] amt = message.split(" ");

            // for (int i = 0; i < Integer.parseInt(amt[1]); i++) {
            // MainP1.spawnViewer(manager);
            // }

            // continue;
            // }

            if (message.contains("-")) {
                String[] cmd = message.split("-");
                command(cmd);
            } else {
                ipManager.addMessageProducer(
                        new ArrayProducer(new Message[] {
                                new Message(message, null)
                        }, times, 1000));
            }

        }

    }

}
