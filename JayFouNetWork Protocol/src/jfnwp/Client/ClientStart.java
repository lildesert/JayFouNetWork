package jfnwp.Client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import jfnwp.Implementation.Message;
import jfnwp.Server.RefereeServer;
import jfnwp.Services.MessageService;

public class ClientStart extends JFrame {

	private static final String ip = "192.168.0.28";
	private JPanel contentPane;
	private JTextField tbName;
	private CardLayout cl;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cl = new CardLayout();
		final GameSelection gs = new GameSelection(cl);

		JLabel lblJayfounetwork = new JLabel("JayFouNetWork");
		lblJayfounetwork.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblJayfounetwork.setBounds(143, 11, 141, 27);
		contentPane.add(lblJayfounetwork);

		JButton btConnect = new JButton("Connect");
		btConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Socket clientSocket = null;
				try {
					clientSocket = new Socket("localhost", RefereeServer.port);
					MessageService m = new MessageService(clientSocket);
					m.Connect(tbName.getText(), ip);
					Message mess = m.ReadMessage();

					if (mess.getId() == 10) {
						m.GetGames();
					}
					
					mess = m.ReadMessage();
					cl.show(gs, "lblWesh");
					
				} 
				catch (UnknownHostException e) {
					e.printStackTrace();
				} 
				catch (IOException e) {
					e.printStackTrace();
				} 
				finally {
					try {
						clientSocket.close();
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btConnect.setBounds(171, 188, 89, 23);
		contentPane.add(btConnect);

		tbName = new JTextField();
		tbName.setBounds(195, 103, 153, 20);
		contentPane.add(tbName);
		tbName.setColumns(10);

		JLabel lblEnterANickname = new JLabel("Enter a nickname :");
		lblEnterANickname.setBounds(80, 106, 117, 14);
		contentPane.add(lblEnterANickname);
	}
}
