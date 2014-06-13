package jfnwp.Client;

import java.awt.LayoutManager;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;

public class GameSelection extends JPanel {

	private JList gameList;
	private DefaultListModel dlmGameList;
	private JButton btOk;
	
	/**
	 * Create the panel.
	 */
	public GameSelection() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(137, 31, 154, 177);
		add(scrollPane);
		
		dlmGameList = new DefaultListModel();
		gameList = new JList(dlmGameList);
		scrollPane.setViewportView(gameList);
		
		btOk = new JButton("OK");
		btOk.setBounds(173, 219, 89, 23);
		add(btOk);
		
		JLabel lblSelectGame = new JLabel("Select game : ");
		lblSelectGame.setBounds(173, 11, 129, 14);
		add(lblSelectGame);

	}

	public DefaultListModel getDlmGameList() {
		return dlmGameList;
	}

	public JButton getBtOk() {
		return btOk;
	}
}
