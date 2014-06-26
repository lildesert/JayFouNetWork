package jfnwp.Client;

import java.awt.Font;
import java.net.Socket;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jfnwp.Client.Interfaces.Observer;
import jfnwp.Implementation.ObservableData;
import jfnwp.Interfaces.IMove;
import jfnwp.Moves.RpslsMove;
import jfnwp.RpslsImplementation.ListenerRpsls;
import jfnwp.Services.MessageService;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RpslsClient extends Client {

	private JFrame frame;
	private JLabel lblInfo = new JLabel("");
	private String rights = "";
	private ListenerRpsls is;
	private JList listMove;
	private JLabel lblScorePlayer;
	private JLabel lblScoreOpponent;

	private static Logger logger = LogManager.getLogger(RpslsClient.class
			.getName());

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
		btnSendMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rights.equals("12")) {
					displayMessage("You can't make a move now");
				}
				else if (rights.equals("14")) {
					displayMessage("Your game is crashed, please start a new one");
				} 
				else if (rights.equals("04")) {
					if (listMove.getSelectedValue() == null) {
						displayMessage("No move selected");
					} else {
						MessageService m = new MessageService(sock);
						IMove mv = new RpslsMove();
						mv.serialize(listMove.getSelectedValue().toString());
						m.ClientMove(mv);
					}
				}
			}
		});
		btnSendMove.setBounds(250, 219, 139, 23);

		is = new ListenerRpsls(sock);

		is.addObserver(new Observer() {
			public void update(ObservableData i) {
				lblInfo.setText(i.getInfo());
				logger.info("update lblInfo ok");
			}
		});

		is.addObserver(new Observer() {
			public void update(ObservableData i) {
				rights = i.getMsgId();
				logger.info("update rights ok");
			}
		});

		is.addObserver(new Observer() {
			public void update(ObservableData i) {
				if (i.getResult() != null) {
					logger.info("update results enter");
					if (i.getResult().equals("win")) {
						logger.info("update results win");
						int score = Integer.parseInt(lblScorePlayer.getText()) + 1;
						lblScorePlayer.setText(Integer.toString(score));
						displayMessage("You win !");
						i.setResult("");
					} else if (i.getResult().equals("loose")) {
						logger.info("update results loose");
						int score = Integer.parseInt(lblScoreOpponent.getText()) + 1;
						lblScoreOpponent.setText(Integer.toString(score));
						displayMessage("You loose !");
						i.setResult("");
					}
					else if (i.getResult().equals("tie")) {
						logger.info("update results tie");
						displayMessage("It's a tie game !");
						i.setResult("");
					}
				}
				logger.info("update results ok");
			}
		});

		listMove = new JList();
		listMove.setBounds(250, 88, 139, 102);
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

		JLabel lblScores = new JLabel("Scores");
		lblScores.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblScores.setBounds(76, 82, 75, 25);
		frame.getContentPane().add(lblScores);

		JLabel lblPseudo = new JLabel(name);
		lblPseudo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPseudo.setBounds(10, 115, 102, 14);
		frame.getContentPane().add(lblPseudo);

		JLabel lblOther = new JLabel("Opponent");
		lblOther.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOther.setBounds(132, 115, 87, 14);
		frame.getContentPane().add(lblOther);

		lblScorePlayer = new JLabel("0");
		lblScorePlayer.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblScorePlayer.setBounds(32, 140, 65, 50);
		frame.getContentPane().add(lblScorePlayer);

		lblScoreOpponent = new JLabel("0");
		lblScoreOpponent.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblScoreOpponent.setBounds(150, 139, 59, 50);
		frame.getContentPane().add(lblScoreOpponent);
		frame.setVisible(true);

		SwingWorker sw = new SwingWorker() {
			protected Object doInBackground() throws Exception {
				is.run();
				return null;
			}
		};
		sw.execute();
	}

	private void displayMessage(String s) {
		JOptionPane.showMessageDialog(frame, s);
	}
}
