package game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HangFrame extends JFrame {

	private HangPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HangFrame frame = new HangFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HangFrame() {
		this.setTitle("");
		ImageIcon icon = new ImageIcon("src/files/custom_java_icon.jpg");
		getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(100, 100);
		setIconImage(icon.getImage());
		this.setResizable(false);
		this.setLayout(null);
		panel = new HangPanel();
		setContentPane(panel);
		pack();
		
	}
	}



