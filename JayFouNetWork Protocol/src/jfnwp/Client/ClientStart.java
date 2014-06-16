package jfnwp.Client;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import jfnwp.Implementation.Message;
import jfnwp.Server.RefereeServer;
import jfnwp.Services.MessageService;

public class ClientStart extends JFrame {

	private static final String ip = "192.168.0.28";
	private JPanel cardPanel;
	private Home h;
	private GameSelection gs;
	private CardLayout cl = new CardLayout();
	private MessageService m;
	private Socket clientSocket;
	private String name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
					clientSocket = new Socket("localhost", RefereeServer.port);
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
					
				} 
				catch (UnknownHostException e) {
					e.printStackTrace();
				} 
				catch (IOException e) {
					e.printStackTrace();
				} 
			}
		});
		gs.getBtOk().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String game = gs.getGameList().getSelectedValue().toString();
				m.Start(game);
				Class c;
				try {
					c = Class.forName("jfnwp.Client."+game +"Client");
					setVisible(false);
					Client cli = (Client) c.newInstance();
					cli.setSock(clientSocket);
					cli.setName(name);
					cli.start();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			});
		getContentPane().add(cardPanel);
		cl.show(cardPanel, "home");
	}
}
