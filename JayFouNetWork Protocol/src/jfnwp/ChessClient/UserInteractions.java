package jfnwp.ChessClient;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLayeredPane;

import jfnwp.Implementation.Move;
import jfnwp.Implementation.Position;
import jfnwp.Interfaces.Color;
import jfnwp.Interfaces.Piece;
import jfnwp.chessImplementation.ChessBoard;
import jfnwp.chessImplementation.ChessMove;

public class UserInteractions implements java.awt.event.MouseListener {
    
    private int click;
    public Color tour;
    private Position start, end;
    protected ChessBoardForDisplay chessBoard;
    protected ChessBoardForDisplay chessBoardPrecedent;
    protected JLayeredPane layerPane;
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
    
    public void setChessBoard(ChessBoardForDisplay board) {
        this.chessBoard = board;
    }

    public void setGraph(Graphics graph) {
        this.graph = graph;
    }

    public void setLayerPane(JLayeredPane layerPane) {
        this.layerPane = layerPane;
    }
    
    public void testDeplacementOrdi(Position start, Position end) throws IOException{   
        Piece piece = chessBoard.getPiece(start);
        Boolean deplacementOrdi = false;
        if (piece.getColor() == Color.White) {
            if (piece.mouvementValide(start, end, chessBoard)) {
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
        this.chessBoard.passeEnEchec(tour, layerPane);
        this.chessBoard.refresh(layerPane);
    	if (chessBoard.isMat(Color.Black) || chessBoard.estMat(Color.White))
            System.out.println("Vous êtes en échec et mat et mat et mat et mat et mat!");
        if(deplacementOrdi) {
            this.ordiDeplacement();
            this.chessBoard.refresh(layerPane);
        }
        tour = Color.White;
    }
    
    public void testDeplacement(Position start, Position end){   
        Piece piece = chessBoard.getPiece(start);
        if (piece.getCouleur() == tour) {
            if (piece.mouvementValide(start, end, chessBoard)) {
                if(! chessBoard.becomeMat(new ChessMove(start, end)) ){
                    int i, j;
                    for (i = 0; i < 8; i++) {
                        for (j = 0; j < 8; j++) {
                            chessBoardPrecedent.chessBoard[j][i] = this.chessBoard.chessBoard[j][i];
                        }
                    }
                    chessBoard.movePiece(start, end);
                    if (tour == Color.White) {
                        tour = Color.Black;
                    } else {
                        tour = Color.White;
                    }
                } else {
                    System.out.println("Vous êtes en échec !");
                }
            } else {
                System.out.println("Déplacement non autorisé !");
            }
        } else {
            System.out.println("Ce n'est pas votre tour !");
        }
        if(this.chessBoard.passeEnEchec(tour, this.layerPane)){
        } else {
            this.chessBoard.refresh(layerPane);
        }
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
                    if( this.chessBoard.getPiece(pClick).getColor() == tour ){
                        this.chessBoard.clickChoixPiece(this.layerPane, pClick);
                        this.click++;
                        this.start = pClick;
                    }
                }
            } else {
                if( ! this.chessBoard.emptySquare(pClick) && this.chessBoard.getPiece(pClick).getColor().equals(tour)) {
                    this.chessBoard.clickChoixPiece(this.layerPane, pClick);
                    this.start = pClick;
                } else {
                    try {
                        this.end = pClick;
                        if( this.modeDeuxJoueurs ){
                            testDeplacement(start, end);
                        } else {
                            testDeplacementOrdi(start, end);
                        }
                        this.click++;
                    } catch (IOException ex) {
                        Logger.getLogger(UserInteractions.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
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