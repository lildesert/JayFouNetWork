package jfnwp.Client;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import jfnwp.Implementation.Message;
import jfnwp.Server.RefereeServer;
import jfnwp.Services.MessageService;

/**
 * Main class of the client program. 
 * @version 1.0
 */
public class ClientStart extends JFrame {

	private static String ip;
	private static String serverIp;
	private static int serverPort;
	
	private static Logger logger = LogManager.getLogger(ClientStart.class
			.getName());

	private JPanel cardPanel;
	private Home h;
	private GameSelection gs;
	private CardLayout cl = new CardLayout();
	private MessageService m;
	private Socket clientSocket;
	private String name;

	/**
	 * Launch the application.
	 * @param args or default configuration
	 * @version 1.0
	 */
	public static void main(String[] args) {
		
		try {
			ip = InetAddress.getLocalHost().toString();
			serverIp = args[0];
			serverPort = Integer.parseInt(args[1]);
		} catch (Exception e) {
			logger.info("Argument Error " + e.getMessage());
			ip = "192.168.0.10";
			serverIp = "localhost";
			serverPort = 9890;
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientStart frame = new ClientStart();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @version 1.0
	 */
	public ClientStart() {
		cardPanel = new JPanel();
		cardPanel.setLayout(cl);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		h = new Home();
		gs = new GameSelection();
		cardPanel.add(h, "home");
		cardPanel.add(gs, "gameSelect");

		h.getBtConnect().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					clientSocket = new Socket(serverIp, serverPort);
					m = new MessageService(clientSocket);
					name = h.getTbName().getText();
					m.Connect(name, ip);
					Message mess = m.ReadMessage();

					if (mess.getId() == 10) {
						m.GetGames();
					}

					mess = m.ReadMessage();

					String[] tabGame = mess.getData().split(";");
					for (String s : tabGame) {
						gs.getDlmGameList().addElement(s);
					}
					cl.show(cardPanel, "gameSelect");

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		gs.getBtOk().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (gs.getGameList().getSelectedValue() == null) {
					displayMessage("Error, no game selected");
				} else {
					String game = gs.getGameList().getSelectedValue()
							.toString();

					m.Start(game);
					Class c;
					try {
						c = Class.forName("jfnwp.Client." + game + "Client");
						setVisible(false);
						Client cli = (Client) c.getDeclaredConstructor(
								Socket.class, String.class).newInstance(
								clientSocket, name);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
				}
			}
		});
		getContentPane().add(cardPanel);
		cl.show(cardPanel, "home");
	}

	private void displayMessage(String s) {
		JOptionPane.showMessageDialog(this, s);
	}
}
