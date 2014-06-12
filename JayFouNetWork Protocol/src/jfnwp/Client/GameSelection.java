package jfnwp.Client;

import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class GameSelection extends JPanel {

	/**
	 * Create the panel.
	 */
	public GameSelection(LayoutManager l) {
		setLayout(l);
		
		JLabel lblWesh = new JLabel("WESH");
		lblWesh.setBounds(174, 112, 46, 14);
		add(lblWesh);

	}

}
