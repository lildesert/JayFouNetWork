package jfnwp.Games;

import jfnwp.ChessImplementation.Bishop;
import jfnwp.ChessImplementation.ChessMove;
import jfnwp.ChessImplementation.ChessPiece;
import jfnwp.ChessImplementation.King;
import jfnwp.ChessImplementation.Knight;
import jfnwp.ChessImplementation.Pawn;
import jfnwp.ChessImplementation.Queen;
import jfnwp.ChessImplementation.Tower;
import jfnwp.Exception.MoveException;
import jfnwp.Implementation.Game;
import jfnwp.Implementation.Player;
import jfnwp.Implementation.Position;
import jfnwp.Interfaces.Color;
import jfnwp.Interfaces.IMove;
import jfnwp.Interfaces.Piece;

/**
 * The representation of a chess's game
 * @version 2.0
 */
public class Chess extends Game {
	
	public Piece[][] board;
	protected Color winner;
	
	public Chess(){
		this.board = new ChessPiece[8][8];
		resetBoard();
	}
	
	@Override
	public boolean isOver() {
		if(isMat(Color.Black) || isMat(Color.White))
			return true;
		return false;
	}
	
	@Override
	public void applyMove(IMove m) throws MoveException {
		if(checkMove(m)) {
			ChessMove mo = (ChessMove) m;
			Position from = mo.getFrom();
			Position to = mo.getTo();
			Piece p = board[from.getX()][from.getY()];
			board[from.getX()][from.getY()] = null;
			board[to.getX()][to.getY()] = p;
		} else {
			throw new MoveException();
		}
	}

	public boolean checkMove(IMove m) {
		ChessMove mo = (ChessMove) m;
		ChessPiece p = (ChessPiece) getPiece(mo.getFrom());
		if(p == null)
			return false;
		if(! p.checkMove(mo, this))
			return false;
			
		return true;
	}
	
	@Override
	public Player getWinnerPlayer() {
		return null;
	}
	
	/**
	 * Reset the board
	 * @version 1.0
	 */
	public void resetBoard() {
        this.board[0][0] = new Tower(Color.Black);
        this.board[0][1] = new Knight(Color.Black);
        this.board[0][2] = new Bishop(Color.Black);
        this.board[0][3] = new Queen(Color.Black);
        this.board[0][4] = new King(Color.Black);
        this.board[0][5] = new Bishop(Color.Black);
        this.board[0][6] = new Knight(Color.Black);
        this.board[0][7] = new Tower(Color.Black);
        
        int i;
        for (i = 0; i < 8; i++) {
            this.board[1][i] = new Pawn(Color.Black);
        }
        for (i = 0; i < 8; i++) {
            this.board[6][i] = new Pawn(Color.White);
        }
        
        this.board[7][0] = new Tower(Color.White);
        this.board[7][1] = new Knight(Color.White);
        this.board[7][2] = new Bishop(Color.White);
        this.board[7][3] = new Queen(Color.White);
        this.board[7][4] = new King(Color.White);
        this.board[7][5] = new Bishop(Color.White);
        this.board[7][6] = new Knight(Color.White);
        this.board[7][7] = new Tower(Color.White);
	}
	
	/**
	 * Check if a piece is in the position p
	 * @return Boolean
	 * @param Position of the piece
	 * @version 1.0
	 */
	public Boolean noPiece(Position p) {
		if(board[p.getY()][p.getX()] == null)
			return true;
		return false;
	}
	
	/**
	 * Get the piece in position p
	 * @return Piece
	 * @param Position of the piece
	 * @version 1.0
	 */
	public Piece getPiece(Position p) {
        Piece piece = null;
        if(! this.noPiece(p) )
            piece = this.board[p.getY()][p.getX()];
        return piece;
    }
	
	/**
	 * Check if two pieces come from the same player
	 * @return Boolean
	 * @param Two position to compare
	 * @version 1.0
	 */
	public Boolean samePlayer(Position p1, Position p2) {
		Piece piece1 = getPiece(p1);
		Piece piece2 = getPiece(p2);
		if(piece1 == null || piece2 == null)
			return false;
		if(piece1.getColor() != piece2.getColor())
			return false;
		return true;
	}
	
	/**
	 * Check if there is no piece between two positions
	 * @return Boolean
	 * @param Move
	 * @version 1.0
	 */
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
	
	/**
	 * Check if the King will become mat with the move
	 * @return Boolean
	 * @param Move
	 * @version 1.0
	 */
	public Boolean becomeMat(ChessMove mv) {
        
		Position from = mv.getFrom();
		Position to = mv.getTo();
		
		int i, j;
        Color kingColor = this.getPiece(from).getColor();
        
        Chess chessTmp = new Chess();
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
	
	/**
	 * Move a piece on the board
	 * @param Position "from" and Position "to"
	 * @version 1.0
	 */
	public void movePiece(Position from, Position to){
		board[to.getY()][to.getX()] = board[from.getY()][from.getX()];
		board[from.getY()][from.getX()] = null;
	}
	
	/**
	 * Check if a piece can be taken by an opponent's piece
	 * @return Boolean
	 * @param Position
	 * @version 1.0
	 */
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
	
	/**
	 * Check if the game is over for the player "color"
	 * @todo When the King can be protected by another piece
	 * @return Boolean
	 * @param Color
	 * @version 1.0
	 */
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
	
	public Boolean emptySquare(Position position) {
        return this.board[position.getY()][position.getX()] == null;
    }

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getWinner() {
		// TODO Auto-generated method stub
		
	}
}