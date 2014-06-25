package jfnwp.Client;

import java.awt.Font;
import java.net.Socket;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import jfnwp.Client.Interfaces.Observer;
import java.awt.Color;

public class RpslsClient extends Client {

	private JFrame frame;
	private JLabel lblInfo = new JLabel("");
	private InfoServer is;

	public RpslsClient(Socket s, String n) {
		super(s, n);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 456, 349);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblRockPaperScissor = new JLabel(
				"Rock Paper Scissor Lizard Spock");
		lblRockPaperScissor.setBounds(10, 11, 420, 25);
		lblRockPaperScissor.setHorizontalAlignment(SwingConstants.CENTER);
		lblRockPaperScissor.setFont(new Font("Tahoma", Font.BOLD, 20));

		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(341, 277, 89, 23);

		JButton btnSendMove = new JButton("Send Move");
		btnSendMove.setBounds(152, 223, 139, 23);

		is = new InfoServer(sock);

		is.addObserver(new Observer() {
			public void update(String info) {
				lblInfo.setText(info);
			}
		});

		JList listMove = new JList();
		listMove.setBounds(152, 88, 139, 102);
		listMove.setModel(new AbstractListModel() {
			String[] values = new String[] { "Rock", "Paper", "Scissor",
					"Lizard", "Spock" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblRockPaperScissor);
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setForeground(Color.RED);
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInfo.setBounds(10, 47, 420, 30);
		frame.getContentPane().add(lblInfo);
		frame.getContentPane().add(listMove);
		frame.getContentPane().add(btnSendMove);
		frame.getContentPane().add(btnQuit);
		frame.setVisible(true);

		SwingWorker sw = new SwingWorker() {
			protected Object doInBackground() throws Exception {
				is.run();
				return null;
			}
		};
		sw.execute();
	}

}
