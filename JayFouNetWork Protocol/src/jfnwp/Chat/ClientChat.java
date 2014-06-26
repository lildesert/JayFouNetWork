package jfnwp.Chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import jfnwp.Client.Interfaces.Observer;
import jfnwp.Implementation.ObservableData;

public class ClientChat {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField tbMess;
	private JEditorPane epChat;
	private InetAddress group;
	private MulticastSocket s;
	private String hostname;
	private int port;
	private Recevoir r;
	private String name;

	/**
	 * Create the frame.
	 */
	public ClientChat(String n, String host, int p) {
		hostname = host;
		port = p;
		name = n;
		joinChat();
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblChatroom = new JLabel("CHATROOM");
		lblChatroom.setHorizontalAlignment(SwingConstants.CENTER);
		lblChatroom.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChatroom.setBounds(10, 11, 424, 22);
		contentPane.add(lblChatroom);

		epChat = new JEditorPane();
		epChat.setBounds(47, 44, 357, 161);
		contentPane.add(epChat);

		JButton btSend = new JButton("Send");
		btSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = name + " > " +tbMess.getText();
				sendMessage(msg);
				tbMess.setText("");
			}
		});
		btSend.setBounds(297, 216, 89, 23);
		contentPane.add(btSend);

		tbMess = new JTextField();
		tbMess.setBounds(57, 217, 202, 20);
		contentPane.add(tbMess);
		tbMess.setColumns(10);
		
		r.addObserver(new Observer() {
			public void update(ObservableData i) {
				String s = epChat.getText();
				s += i.getInfo() +"\n";
				epChat.setText(s);
			}
		});

		
		frame.setVisible(true);
	}

	private void sendMessage(String msg) {
		DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.length(),
				group, port);
		try {
			s.send(dp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void joinChat() {
		try {
			group = InetAddress.getByName(hostname);
			s = new MulticastSocket(port);
			s.joinGroup(group);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		r = new Recevoir(hostname, port);

		SwingWorker sw = new SwingWorker() {
			protected Object doInBackground() throws Exception {
				Thread ChatListenerThread = new Thread(r);
				ChatListenerThread.setDaemon(true);
				ChatListenerThread.start();
				return null;
			}
		};
		
		sw.execute();
	}
}
