package jfnwp.Chess;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jfnwp.ChessImplementation.ChessPiece;
import jfnwp.Client.ChessClient;
import jfnwp.Games.Chess;
import jfnwp.Moves.ChessMove;
import jfnwp.Services.MessageService;

/**
 * To listen the mouse's events for the Chess client
 * 
 * @see Chess
 * @version 1.0
 */
public class UserInteractions implements java.awt.event.MouseListener {

	private static Logger logger = LogManager.getLogger(UserInteractions.class
			.getName());

	private MessageService m;

	private int click;
	public Color tour;
	private Position start, end;
	public Chess chessBoard;
	public Chess chessBoardPrecedent;
	public JLayeredPane layerPane;
	private Graphics graph;
	private ChessClient chessCli;

	public UserInteractions(Socket s, ChessClient cc) {
		this.click = 0;
		this.chessBoardPrecedent = new ChessBoardForDisplay();
		m = new MessageService(s);
		chessCli = cc;
	}

	public void setChessBoard(Chess chessBoardPrecedent2) {
		this.chessBoard = chessBoardPrecedent2;
	}

	public void setGraph(Graphics graph) {
		this.graph = graph;
	}

	public void setLayerPane(JLayeredPane layerPane) {
		this.layerPane = layerPane;
	}
	
	/**
	 * Test if a movement is valid
	 * @param start
	 * @param end
	 */
	public void testDeplacementValid(Position start, Position end) {
		logger.info("testDeplacement call "+tour);
		ChessPiece piece = (ChessPiece) chessBoard.getPiece(start);	
					int i, j;
					for (i = 0; i < 8; i++) {
						for (j = 0; j < 8; j++) {
							chessBoardPrecedent.board[j][i] = this.chessBoard.board[j][i];
						}
					}
					chessBoard.movePiece(start, end);
		if (((ChessBoardForDisplay) this.chessBoard).displayMat(tour,
				this.layerPane)) {
		} else {

		}
		((ChessBoardForDisplay) this.chessBoard).refresh(layerPane);
	}

	public void testDeplacement(Position start, Position end) {
		logger.info("testDeplacement call");
		
		m.ClientMove(new ChessMove(start, end).toString());
		
		/*if (((ChessBoardForDisplay) this.chessBoard).displayMat(tour,
				this.layerPane)) {
		} else {

		}
		((ChessBoardForDisplay) this.chessBoard).refresh(layerPane);*/
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	/**
	 * When the mouse is pressed, displays the possible movements
	 * or perform the movement
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		logger.info("mousePressed call "+tour);
		if (chessCli.getRights().equals("12")) {
			displayMessage(chessCli.getMsgInfo());
		} 
		else if (chessCli.getRights().equals("14")) {
			displayMessage("Your game is crashed, please start a new one");
		} 
		else if (chessCli.getRights().equals("04")) {
			if (e.getX() > 36 && e.getX() < 600 - 36 && e.getY() > 36
					&& e.getY() < 600 - 36) {
				Position pClick = new Position((e.getX() - 36) / 66,
						(e.getY() - 36) / 66);

				if (this.click % 2 == 0) {
					logger.info("mousePressed call : selection piece");
					if (!this.chessBoard.emptySquare(pClick)) {
						if (this.chessBoard.getPiece(pClick).getColor() == tour) {
							((ChessBoardForDisplay) this.chessBoard)
									.clickChoixPiece(this.layerPane, pClick);
							this.click++;
							this.start = pClick;
						}
						else
						{
							displayMessage("This is not your color !");
						}
					}
				} else {
					logger.info("mousePressed call : selection déplacement piece");
					if (!this.chessBoard.emptySquare(pClick)
							&& this.chessBoard.getPiece(pClick).getColor()
									.equals(tour)) {
						((ChessBoardForDisplay) this.chessBoard)
								.clickChoixPiece(this.layerPane, pClick);
						this.start = pClick;
					} else {
						this.end = pClick;
						testDeplacement(start, end);
						this.click++;
					}
				}
			}
		}
	}

	public void ordiDeplacement() throws IOException {

	}

	@Override
	public void mouseReleased(MouseEvent me) {
	}

	@Override
	public void mouseEntered(MouseEvent me) {
	}

	@Override
	public void mouseExited(MouseEvent me) {
	}

	private void displayMessage(String s) {
		JOptionPane.showMessageDialog(layerPane, s);
	}
}