package jfnwp.chessImplementation;

import jfnwp.Implementation.Move;
import jfnwp.Implementation.Player;
import jfnwp.Implementation.Position;
import jfnwp.Interfaces.Color;
import jfnwp.Interfaces.IBoard;
import jfnwp.Interfaces.IMove;
import jfnwp.Interfaces.Piece;

public class ChessBoard implements IBoard {
	
	private Piece[][] board;
	
	public ChessBoard(){
		this.board = new Piece[8][8];
        this.board[0][0] = new Tower(Color.Black);
        this.board[0][1] = new Knight(Color.Black);
        this.board[0][2] = new Bishop(Color.Black);
        this.board[0][3] = new Queen(Color.Black);
        this.board[0][4] = new King(Color.Black);
        this.board[0][5] = new Bishop(Color.Black);
        this.board[0][6] = new Knight(Color.Black);
        this.board[0][7] = new Tower(Color.Black);
	}
	
	@Override
	public Boolean isFinish(Color turn) {
		return isMat(turn);
	}
	
	//@TODO : When the King can be protected by another piece 
	public Boolean isMat(Color color) {
		int i, j;
        Piece pieceTmp =null;
        Position kingPosition = new Position();
        Position positionTest = new Position();
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                if ((board[i][j] instanceof King) && board[i][j].getColor().equals(color)) {
                    kingPosition = new Position(j, i);
                	pieceTmp = board[i][j];
                }
            }
        }
        
        ChessPiece piece = (ChessPiece) board[kingPosition.getX()][kingPosition.getY()];
        Position positionTmp = new Position();
        
        int x, y;
        if (mayBeTaken(kingPosition)){
	        for (y = 0; y < 8; y++) {
	            for (x = 0; x < 8; x++) {
	                positionTmp.setX(y);
	                positionTmp.setY(x);
	                ChessMove mv = new ChessMove(kingPosition, positionTmp);
	                if (piece.checkMove(mv, this))
	                	if(!mayBeTaken(positionTmp))
	                		return false;
	            }
	        }
	        return true;
        }
        return false;
	}
	
	@Override
	public void applyMove(Player j, IMove m) {
		ChessMove mo = (ChessMove) m;
		Position from = mo.getFrom();
		Position to = mo.getTo();
		Piece p = board[from.getX()][from.getY()];
		board[from.getX()][from.getY()] = null;
		board[to.getX()][to.getY()] = p;
	}

	@Override
	public boolean checkMove(Player j, IMove m) {
		ChessMove mo = (ChessMove) m;
		ChessPiece p = (ChessPiece) getPiece(mo.getFrom());
		if(p == null)
			return false;
		if(! p.checkMove(mo, this))
			return false;
			
		return true;
	}
	
	public Boolean noPiece(Position p) {
		if(board[p.getX()][p.getY()] == null)
			return true;
		return false;
	}
	
	private Piece getPiece(Position p) {
        Piece piece = null;
        if(! this.noPiece(p) )
            piece = this.board[p.getY()][p.getX()];
        return piece;
    }
	
	public Boolean samePlayer(Position p1, Position p2) {
		Piece piece1 = getPiece(p1);
		Piece piece2 = getPiece(p2);
		if(piece1 == null || piece2 == null)
			return false;
		if(piece1.getColor() != piece2.getColor())
			return false;
		return true;
	}

	public boolean freeRoad(ChessMove mv) {
		Position from = mv.getFrom();
		Position to = mv.getTo();
		
		Position posTmp = new Position(from.getX(), from.getY());
		
        int x = 1, y = 1;
        if (to.getX() < posTmp.getX()) {
            x = -1;
        }
        if (to.getY() < posTmp.getY()) {
            y = -1;
        }
        while (x != 0 || y != 0) {
            if (posTmp.getX() == to.getX()) {
                x = 0;
            }
            if (posTmp.getY() == to.getY()) {
                y = 0;
            }
            if (!noPiece(posTmp)
                    && !((posTmp.getX() == from.getX()) && (posTmp.getY() == from.getY()))
                    && !((x == 0) && (y == 0))) {
                return false;
            }
            posTmp.setX(posTmp.getX() + x);
            posTmp.setY(posTmp.getY() + y);
        }
        return true;
	}
	
	public Boolean becomeMat(ChessMove mv) {
        
		Position from = mv.getFrom();
		Position to = mv.getTo();
		
		int i, j;
        Color kingColor = this.getPiece(from).getColor();
        
        ChessBoard chessTmp = new ChessBoard();
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                chessTmp.board[i][j] = board[i][j];
            }
        }
        chessTmp.movePiece(from, to);
        Position KingPosition = null;
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                if ((chessTmp.board[i][j] instanceof King) && chessTmp.board[i][j].getColor().equals(kingColor)) {
                	KingPosition = new Position(j, i);
                	break;
                }
            }
        }
        if(chessTmp.mayBeTaken(KingPosition))
            return true;
        
        return false;
    }
	
	public void movePiece(Position from, Position to){
		board[to.getX()][to.getY()] = board[from.getX()][from.getY()];
		board[from.getX()][from.getY()] = null;
	}
	
	public Boolean mayBeTaken(Position p) {
		Piece piece = this.getPiece(p);
        int i, j;
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    ChessPiece pTmp = (ChessPiece) board[i][j];
                    ChessMove mv = new ChessMove(new Position(j, i), p);
                    if (!pTmp.getColor().equals(piece.getColor()) && pTmp.checkMove(mv, this))
                        return true;
                }
            }
        }
        return false;
	}
}