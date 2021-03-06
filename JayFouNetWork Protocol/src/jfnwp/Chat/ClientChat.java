package jfnwp.Chat;

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

import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import jfnwp.Client.Interfaces.Observer;
import jfnwp.Implementation.ObservableData;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;

/**
 * Class to interact with the opponent with 
 * a graphical user interface. 
 * Class is called by the clients. 
 * The protocol used is UDP. 
 * @version 1.0
 */
public class ClientChat {

	private JFrame frame;
	private JPanel contentPane;
	private JTextPane epChat;
	private JScrollPane mOutputScroll;
	private JTextField tbMess;
	
	private String name;
	private String hostname;
	
	private InetAddress group;
	private MulticastSocket s;
	
	private int port;
	private Recevoir r;
	
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
		
		mOutputScroll = new JScrollPane();
		mOutputScroll.setBounds(57, 62, 324, 135);
		mOutputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(mOutputScroll);
		
		epChat = new JTextPane();
		mOutputScroll.setViewportView(epChat);
		
		/*
		 * A an observer to update the GUI when the program
		 * is receiving a new message
		 */
		r.addObserver(new Observer() {
			public void update(ObservableData i) {
				String s = epChat.getText();
				s += i.getInfo() +"\n";
				epChat.setText(s);
				boolean scroll = isViewAtBottom();
				if (scroll)
				{
			        scrollToBottom();
				}
			}
		});

		frame.setVisible(true);
	}
	
	/**
	 * Send a message to the opponent
	 * @param msg
	 * @version 1.0
	 */
	private void sendMessage(String msg) {
		DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.length(),
				group, port);
		try {
			s.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add a player to the chat
	 * @version 1.0
	 */
	private void joinChat() {
		try {
			group = InetAddress.getByName(hostname);
			s = new MulticastSocket(port);
			s.joinGroup(group);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
	
	/**
	 * @version 1.0
	 * @return boolean
	 */
	private boolean isViewAtBottom()
	{
	    JScrollBar sb = mOutputScroll.getVerticalScrollBar();
	    int min = sb.getValue() + sb.getVisibleAmount();
	    int max = sb.getMaximum();
	    System.out.println(min + " " + max);
	    return min == max;
	}
	
	/**
	 * @version 1.0
	 */
	private void scrollToBottom()
	{
	    SwingUtilities.invokeLater(
	        new Runnable()
	        {
	            public void run()
	            {
	                mOutputScroll.getVerticalScrollBar().setValue(mOutputScroll.getVerticalScrollBar().getMaximum());
	            }
	        });
	}
}
