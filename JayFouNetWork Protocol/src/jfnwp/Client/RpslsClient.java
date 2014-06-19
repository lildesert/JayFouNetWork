package jfnwp.Client;

import java.awt.Font;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RpslsClient extends Client {

	private JFrame frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public void start() {
		RpslsClient window = new RpslsClient();
		window.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public RpslsClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 456, 349);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRockPaperScissor = new JLabel("Rock Paper Scissor Lizard Spock");
		lblRockPaperScissor.setHorizontalAlignment(SwingConstants.CENTER);
		lblRockPaperScissor.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRockPaperScissor.setBounds(10, 11, 420, 25);
		frame.getContentPane().add(lblRockPaperScissor);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(341, 277, 89, 23);
		frame.getContentPane().add(btnQuit);
		
		JButton btnSendMove = new JButton("Send Move");
		btnSendMove.setBounds(152, 223, 139, 23);
		frame.getContentPane().add(btnSendMove);
		
		JLabel lblInfo = new JLabel("");
		lblInfo.setBounds(196, 56, 46, 14);
		frame.getContentPane().add(lblInfo);
		
		JList listMove = new JList();
		listMove.setModel(new AbstractListModel() {
			String[] values = new String[] {"Rock", "Paper", "Scissor", "Lizard", "Spock"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listMove.setBounds(152, 88, 139, 102);
		frame.getContentPane().add(listMove);
	}

}
