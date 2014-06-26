package jfnwp.Client;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jfnwp.Chat.ClientChat;
import jfnwp.Chess.ChessBoardForDisplay;
import jfnwp.Chess.ChessMessage;
import jfnwp.Chess.UserInteractions;
import jfnwp.Interfaces.Color;
import jfnwp.Services.MessageService;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessClient extends Client {

	private static Logger logger = LogManager.getLogger(ChessClient.class
			.getName());
	
	protected static JLayeredPane layeredPane = new JLayeredPane();
	protected static JButton score = new JButton("Score");
	protected static JButton joueur;
	/****** Ask The Name ******/
	protected static JTextField username = new JTextField();
	public static JLabel text;
	/******* MENU *********/
	private JMenuBar menuBar = new JMenuBar();
	private JMenu jeuMenu = new JMenu("Jeu");
	private JMenu chat = new JMenu("Chat");
	private JMenuItem quit = new JMenuItem("Quit");
	private UserInteractions inter;

	protected static JFrame fenetre;
	protected String player = "";
	private Color tourJoueur;
	private String chatInfo;
	private ClientListener cl;

	public ChessClient(Socket s, String n) {
		super(s, n);
		start();
	}

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
				System.exit(0);
			}
		});
		this.jeuMenu.add(quit);
		this.menuBar.add(jeuMenu);
		chat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MessageService m = new MessageService(sock);
				
				while(chatInfo == null)
				{
					m.GetAdress();
					logger.info("Message 9 non reçu");
				}
				
				logger.info("Message 9 reçu");
				String[] tab = chatInfo.split(";");
				ClientChat cc = new ClientChat(name, tab[0], Integer.parseInt(tab[1]));
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
		inter = new UserInteractions();

		layeredPane.setPreferredSize(new Dimension(610, 680));
		layeredPane.addMouseListener(inter);

		ChessBoardForDisplay chessboard = new ChessBoardForDisplay();

		inter.setChessBoard(chessboard);
		inter.setLayerPane(layeredPane);
		inter.setGraph(layeredPane.getGraphics());
		inter.setModedeuxJoueurs(false);

		chessboard.refresh(layeredPane);

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
	
	private void displayMessage(String s) {
		JOptionPane.showMessageDialog(fenetre, s);
	}
}
