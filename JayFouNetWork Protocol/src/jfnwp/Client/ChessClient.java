package jfnwp.Client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jfnwp.Chat.ClientChat;
import jfnwp.Chess.ChessBoardForDisplay;
import jfnwp.Chess.Color;
import jfnwp.Chess.UserInteractions;
import jfnwp.Client.Interfaces.Observer;
import jfnwp.Implementation.ObservableData;
import jfnwp.Moves.ChessMove;
import jfnwp.Services.MessageService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The GUI of chess game
 * @version 1.0
 */
public class ChessClient extends Client {
	
	//Used to record all actions 
	private static Logger logger = LogManager.getLogger(ChessClient.class.getName());

	protected static JLayeredPane layeredPane = new JLayeredPane();
	protected static JButton score = new JButton("Score");
	protected static JButton joueur;

	/******* MENU *********/
	private JMenuBar menuBar = new JMenuBar();
	private JMenu jeuMenu = new JMenu("Jeu");
	private JMenu chat = new JMenu("Chat");
	private JMenuItem quit = new JMenuItem("Quit");
	private UserInteractions inter;

	protected static JFrame fenetre;
	protected String player = "";
	private String chatInfo;
	private ClientListener cl;
	private String rights;
	private String msgInfo;
	private boolean display = true;

	public ChessClient(Socket s, String n) {
		super(s, n);
		start();
	}
	
	/**
	 * Start the game
	 * @version 1.0
	 */
	public void start() {

		player = super.name;
		cl = new ClientListener(sock);

		fenetre = new JFrame();
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);
		fenetre.pack();

		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MessageService m = new MessageService(sock);
				m.End();
				try {
					sock.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
			}
		});
		this.jeuMenu.add(quit);
		this.menuBar.add(jeuMenu);
		chat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MessageService m = new MessageService(sock);

				while (chatInfo == null) {
					m.GetAdress();
					logger.info("Message 9 non reçu");
				}

				logger.info("Message 9 reçu");
				String[] tab = chatInfo.split(";");
				ClientChat cc = new ClientChat(name, tab[0], Integer
						.parseInt(tab[1]));
				chat = null;
			}
		});
		this.menuBar.add(chat);

		fenetre.setJMenuBar(menuBar);
		fenetre.setVisible(true);

		layeredPane.removeAll();

		fenetre.setSize(615, 658);
		fenetre.setBackground(java.awt.Color.BLACK);

		fenetre.setLocationRelativeTo(null);
		layeredPane
				.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));

		fenetre.setTitle("Chess - " + player);
		inter = new UserInteractions(sock, this);

		layeredPane.setPreferredSize(new Dimension(610, 680));
		layeredPane.addMouseListener(inter);

		ChessBoardForDisplay chessboard = new ChessBoardForDisplay();

		inter.setChessBoard(chessboard);
		inter.setLayerPane(layeredPane);
		inter.setGraph(layeredPane.getGraphics());

		chessboard.refresh(layeredPane);

		cl.addObserver(new Observer() {
			public void update(ObservableData i) {
				chatInfo = i.getChatData();
				logger.info("update chat ok");
			}
		});
		
		cl.addObserver(new Observer() {
			public void update(ObservableData i) {
				if (i.getEndGame() != null) {
					if (i.getEndGame().equals("quit")) {
						displayMessage("Your opponent quit");
						try {
							sock.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.exit(0);
					}
				}
				logger.info("game not over");
			}
		});

		cl.addObserver(new Observer() {
			public void update(ObservableData i) {
				String infGame = i.getGameInfo();
				if (inter.tour == null) {
					if (infGame.equals("white")) {
						inter.tour = Color.White;
					} else if (infGame.equals("black")) {
						inter.tour = Color.Black;
					}
				}
				logger.info("update gameInfo ok");
			}
		});

		cl.addObserver(new Observer() {
			public void update(ObservableData i) {
				rights = i.getMsgId();
				logger.info("update rights ok");
			}
		});

		cl.addObserver(new Observer() {
			public void update(ObservableData i) {
				if (i.getInfo() != null) {
					if (!i.getInfo().equals(msgInfo)) {
						msgInfo = i.getInfo();
						display = true;
					}
					if (display == true) {
						displayMessage(msgInfo);
						display = false;
					}
				}
				logger.info("update info ok");
			}
		});
		
		cl.addObserver(new Observer() {
			public void update(ObservableData i) {
				if (i.getResult() != null) {
					logger.info("update results enter");
					if(i.getResult().equals("win"))
					{
						displayMessage("You win !");
						MessageService m = new MessageService(sock);
						m.End();
						try {
							sock.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						System.exit(0);
					}
					else if(i.getResult().equals("loose"))
					{
						displayMessage("You loose !");
						MessageService m = new MessageService(sock);
						m.End();
						try {
							sock.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						System.exit(0);
					}
					else if(i.getResult().length() != 0)
					{
						ChessMove mv = new ChessMove();
						mv.deserialize(i.getResult());
						inter.testDeplacementValid(mv.getFrom(), mv.getTo());
					}
				}
				logger.info("update results ok");
			}
		});

		fenetre.setContentPane(layeredPane);
		fenetre.setVisible(true);

		SwingWorker sw = new SwingWorker() {
			protected Object doInBackground() throws Exception {
				cl.run();
				return null;
			}
		};
		sw.execute();
	}

	public String getRights() {
		return rights;
	}

	public String getMsgInfo() {
		return msgInfo;
	}

	private void displayMessage(String s) {
		JOptionPane.showMessageDialog(fenetre, s);
	}
}
