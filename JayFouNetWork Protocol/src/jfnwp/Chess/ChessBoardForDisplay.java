package jfnwp.Chess;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import jfnwp.ChessImplementation.ChessPiece;
import jfnwp.ChessImplementation.King;
import jfnwp.Games.Chess;
import jfnwp.Moves.ChessMove;

/**
 * User Interface for the Chess client
 * @see ChessClient2
 * @version 1.0
 */
public class ChessBoardForDisplay extends Chess{
	
	private JLabel cadre;
	private JLabel fond;
	private JLabel cadreEchec;
	
	public ChessBoardForDisplay() {
		this.cadreEchec = getImage("ressources/echec.png");
        this.cadre = getImage("ressources/selection.png");
        this.fond = getImage("ressources/chessboard.jpg");
	}
	
	/**
	 * Refresh the layeredPane (after a move for example)
	 * @version 1.0
	 */
	public void refresh(JLayeredPane layeredPane) {
        layeredPane.removeAll();
        this.displayPieces(layeredPane);
        this.displayBackground(layeredPane);
    }
	
	/**
	 * Get an image to inform the player which piece he selected
	 * @version 1.0
	 */
	public void displaySelection(JLayeredPane layeredPane, Position p) {
        layeredPane.add(this.cadre);
        this.cadre.setLocation(35 + p.getX() * 66, 35 + p.getY() * 66);
    }
	
	/**
	 * Insert pieces in the layeredPane
	 * @version 1.0
	 */
	public void displayPieces(JLayeredPane layeredPane) {
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
	
	/**
	 * Insert the background in the layeredPane
	 * @version 1.0
	 */
	public void displayBackground(JLayeredPane layeredPane) {
        layeredPane.add(getImage("ressources/chessboard.jpg"));
    }
	
	/**
	 * Get an image from the folder 'ressources'
	 * @version 1.0
	 */
	public final JLabel getImage(String a) {
        URL url = getClass().getClassLoader().getResource(a);
        ImageIcon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        return label;
    }
	
	/**
	 * Display an alert when the player is Mat
	 * @version 1.0
	 */
	public Boolean displayMat(Color couleur, JLayeredPane layerPane) {
        int i, j;
        Position positionRoi = new Position();
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                if ((this.board[i][j] instanceof King) && this.board[i][j].getColor().equals(couleur)) {
                    positionRoi = new Position(j, i);
                }
            }
        }
        if(this.mayBeTaken(positionRoi)){
        	layerPane.removeAll();
            this.displayPieces(layerPane);
            layerPane.add(getImage("ressources/echec.png"));
            getImage("ressources/echec.png").setLocation(35 + positionRoi.getX() * 66, 35 + positionRoi.getY() * 66);
            this.displayBackground(layerPane);
            return true;
        }
        return false;
    }
	
	/**
	 * Display the possible moves for a piece
	 * @version 1.0
	 */
	public void displayPossibleMoves(JLayeredPane layeredPane, Position p) {
        ChessPiece piece = (ChessPiece) getPiece(p);
        Position positionTmp = new Position();
        JLabel image;
        int x, y;
        for (y = 0; y < 8; y++) {
            for (x = 0; x < 8; x++) {
                positionTmp.setX(x);
                positionTmp.setY(y);
                if (piece.checkMove(new ChessMove(p, positionTmp), this)) {
                    image = getImage("ressources/coupsPossibles.png");
                    layeredPane.add(image);
                    image.setLocation(35 + positionTmp.getX() * 66, 35 + positionTmp.getY() * 66);
                }
            }
        }
    }
	
	/**
	 * Actions when a piece is selected
	 * @version 1.0
	 */
	public void clickChoixPiece(JLayeredPane layeredPane, Position p) {
        layeredPane.removeAll();        
        this.displayPieces(layeredPane);
        this.displaySelection(layeredPane, p);
        this.displayPossibleMoves(layeredPane, p);
        this.displayBackground(layeredPane);
    }
}