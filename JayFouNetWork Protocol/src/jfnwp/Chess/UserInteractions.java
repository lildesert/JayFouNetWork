package jfnwp.Chess;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLayeredPane;

import jfnwp.ChessImplementation.ChessPiece;
import jfnwp.Games.Chess;
import jfnwp.Implementation.Position;
import jfnwp.Interfaces.Color;
import jfnwp.Interfaces.Piece;
import jfnwp.Moves.ChessMove;

/**
 * To listen the mouse's events for the Chess client
 * @see ChessClient
 * @version 1.0
 */
public class UserInteractions implements java.awt.event.MouseListener {
    
    private int click;
    public Color tour;
    private Position start, end;
    //Changé à public
    public Chess chessBoard;
    //Changé à public
    public Chess chessBoardPrecedent;
    //Changé à public
    public JLayeredPane layerPane;
    private Graphics graph;
    public boolean modeDeuxJoueurs = false;
    
    public UserInteractions() {
        this.click = 0;
        this.tour = Color.White;
        this.chessBoardPrecedent = new ChessBoardForDisplay();
    }
    
    public void setModedeuxJoueurs(Boolean bool){
        modeDeuxJoueurs = bool;
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
    
    public void testDeplacementOrdi(Position start, Position end) throws IOException{   
        ChessPiece piece = (ChessPiece) chessBoard.getPiece(start);
        Boolean deplacementOrdi = false;
        if (piece.getColor() == Color.White) {
            if(piece.checkMove(new ChessMove(start, end), chessBoard)) {
                if(! chessBoard.becomeMat(new ChessMove(start, end))){
                	if (!chessBoard.isMat(Color.Black) || !chessBoard.isMat(Color.White)){
                            int i, j;
                            for (i = 0; i < 8; i++) {
                                for (j = 0; j < 8; j++) {
                                    chessBoardPrecedent.board[j][i] = this.chessBoard.board[j][i];
                                }
                            }
	                    chessBoard.movePiece(start, end);
                            deplacementOrdi = true;
	                    if (tour == Color.White) {
	                    tour = Color.Black;
	                    } else {
	                        tour = Color.White;
	                    }
                	} else 
                        System.out.println("You are in mat!");
                } else {
                    System.out.println("Vous êtes en échec !");
                }
            } else {
                System.out.println("Wrong move.");
            }
        } else {
            System.out.println("Ce n'est pas votre tour !");
        }
        ((ChessBoardForDisplay) this.chessBoard).displayMat(tour, layerPane);
        ((ChessBoardForDisplay) this.chessBoard).refresh(layerPane);
    	if (chessBoard.isMat(Color.Black) || chessBoard.isMat(Color.White))
            System.out.println("Vous êtes en échec et mat et mat et mat et mat et mat!");
        if(deplacementOrdi) {
            this.ordiDeplacement();
            ((ChessBoardForDisplay) this.chessBoard).refresh(layerPane);
        }
        tour = Color.White;
    }
    
    public void testDeplacement(Position start, Position end){   
        ChessPiece piece = (ChessPiece) chessBoard.getPiece(start);
        if (piece.getColor() == tour) {
            if (piece.checkMove(new ChessMove(start, end), chessBoard)) {
                if(! chessBoard.becomeMat(new ChessMove(start, end)) ){
                    int i, j;
                    for (i = 0; i < 8; i++) {
                        for (j = 0; j < 8; j++) {
                            chessBoardPrecedent.board[j][i] = this.chessBoard.board[j][i];
                        }
                    }
                    chessBoard.movePiece(start, end);
                    if (tour == Color.White) {
                        tour = Color.Black;
                    } else {
                        tour = Color.White;
                    }
                } else {
                    System.out.println("You are mat");
                }
            } else {
                System.out.println("Incorrect move !");
            }
        } else {
            System.out.println("Not your turn !");
        }
        if(((ChessBoardForDisplay) this.chessBoard).displayMat(tour, this.layerPane)){
        } else {
            
        }
        ((ChessBoardForDisplay) this.chessBoard).refresh(layerPane);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getX() > 36 && e.getX() < 600 - 36 && e.getY() > 36 && e.getY() < 600 - 36){
            
            Position pClick=new Position ((e.getX()-36)/66, (e.getY()-36)/66);
           
            if(this.click % 2 == 0){
                if( ! this.chessBoard.emptySquare(pClick)) {
                    if( this.chessBoard.getPiece(pClick).getColor() == tour){
                        ((ChessBoardForDisplay) this.chessBoard).clickChoixPiece(this.layerPane, pClick);
                        this.click++;
                        this.start = pClick;
                    }
                }
            } else {
                if( ! this.chessBoard.emptySquare(pClick) && this.chessBoard.getPiece(pClick).getColor().equals(tour)) {
                    ((ChessBoardForDisplay) this.chessBoard).clickChoixPiece(this.layerPane, pClick);
                    this.start = pClick;
                } else {
                    this.end = pClick;
					testDeplacement(start, end);
					this.click++;
                }
            }
        }
    }
    
    public void ordiDeplacement() throws IOException{   
    	
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
}