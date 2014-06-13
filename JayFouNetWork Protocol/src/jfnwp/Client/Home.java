package jfnwp.Client;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Home extends JPanel {

	private JTextField tbName;
	private JButton btConnect;

	/**
	 * Create the panel.
	 */
	public Home() {
		setLayout(null);

		JLabel lblJayfounetwork = new JLabel("JayFouNetWork");
		lblJayfounetwork.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblJayfounetwork.setBounds(152, 34, 136, 21);
		add(lblJayfounetwork);

		btConnect = new JButton("Connect");
		btConnect.setBounds(177, 186, 91, 23);
		add(btConnect);

		tbName = new JTextField();
		tbName.setBounds(218, 108, 128, 20);
		add(tbName);
		tbName.setColumns(10);

		JLabel lblEnterANickname = new JLabel("Enter a nickname :");
		lblEnterANickname.setBounds(88, 111, 120, 14);
		add(lblEnterANickname);
	}

	public JTextField getTbName() {
		return tbName;
	}

	public JButton getBtConnect() {
		return btConnect;
	}
}
