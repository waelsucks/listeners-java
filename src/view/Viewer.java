package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import misc.Message;

public class Viewer extends JPanel {
	private JLabel lblText;
	private JLabel lblIcon;
	
	public Viewer(int width, int height) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setLayout(new BorderLayout());
				lblText = new JLabel(" ",JLabel.CENTER);
				lblIcon = new JLabel(" ",JLabel.CENTER);
				lblText.setFont(new Font("SansSerif",Font.PLAIN,20));
				lblIcon.setOpaque(true);
				add(lblText,BorderLayout.NORTH);
				add(lblIcon);
				lblIcon.setPreferredSize(new Dimension(width,height));
			}
		});
	}
	
	public void setMessage(Message message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				lblText.setText(message.getText());
				lblIcon.setIcon(message.getIcon());
			}
		});
	}	
    
    public static void showPanelInFrame(final JPanel panel, String title, int x, int y) {
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    	    	JFrame frame = new JFrame(title);
    	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	    	frame.add(panel);
    	    	frame.pack();
    	    	frame.setLocation(x, y);
    	    	frame.setVisible(true);
    		}
    	});
    }
}
