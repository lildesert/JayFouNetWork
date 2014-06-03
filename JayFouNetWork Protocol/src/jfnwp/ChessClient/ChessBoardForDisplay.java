package jfnwp.ChessClient;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import jfnwp.chessImplementation.ChessBoard;

public class ChessBoardForDisplay extends ChessBoard{
	
	private JLabel cadre, fond, cadreEchec;
	
	public ChessBoardForDisplay() {
		this.cadreEchec = getImage("ressources/echec.png");
        this.cadre = getImage("ressources/selection.png");
        this.fond = getImage("ressources/chessboard.jpg");
	}

	public void refresh(JLayeredPane layeredPane) {
        layeredPane.removeAll();
        this.affichierPieces(layeredPane);
        this.displayBackground(layeredPane);
    }
	
	public void affichierPieces(JLayeredPane layeredPane) {
        JLabel image;
        int y, x;
        for (y = 0; y < 8; y++) {
            for (x = 0; x < 8; x++) {
                if (board[y][x] != null) {
                    image = getImage("ressources/" + board[y][x].toString() + ".png");
                    layeredPane.add(image);
                    image.setLocation(35 + x * 66, 35 + y * 66);
                }
            }
        }
    }
	
	public void displayBackground(JLayeredPane layeredPane) {
        layeredPane.add(this.fond);
    }
	
	public final JLabel getImage(String a) {
        URL url = getClass().getClassLoader().getResource(a);
        ImageIcon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        return label;
    }
}