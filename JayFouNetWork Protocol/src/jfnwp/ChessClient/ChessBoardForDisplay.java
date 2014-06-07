package jfnwp.ChessClient;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import jfnwp.ChessImplementation.ChessBoard;
import jfnwp.ChessImplementation.ChessMove;
import jfnwp.ChessImplementation.ChessPiece;
import jfnwp.ChessImplementation.King;
import jfnwp.Implementation.Position;
import jfnwp.Interfaces.Color;

public class ChessBoardForDisplay extends ChessBoard{
	
	private JLabel cadre, fond, cadreEchec;
	
	public ChessBoardForDisplay() {
		this.cadreEchec = getImage("ressources/echec.png");
        this.cadre = getImage("ressources/selection.png");
        this.fond = getImage("ressources/chessboard.jpg");
	}

	public void refresh(JLayeredPane layeredPane) {
        layeredPane.removeAll();
        this.displayPieces(layeredPane);
        this.displayBackground(layeredPane);
    }
	
	public void displaySelection(JLayeredPane layeredPane, Position p) {
        layeredPane.add(this.cadre);
        this.cadre.setLocation(35 + p.getX() * 66, 35 + p.getY() * 66);
    }
	
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
            layerPane.add(this.cadreEchec);
            this.cadreEchec.setLocation(35 + positionRoi.getX() * 66, 35 + positionRoi.getY() * 66);
            this.displayBackground(layerPane);
            return true;
        }
        return false;
    }
	
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
	
	public void clickChoixPiece(JLayeredPane layeredPane, Position p) {
        layeredPane.removeAll();        
        this.displayPieces(layeredPane);
        this.displaySelection(layeredPane, p);
        this.displayPossibleMoves(layeredPane, p);
        this.displayBackground(layeredPane);
    }
}