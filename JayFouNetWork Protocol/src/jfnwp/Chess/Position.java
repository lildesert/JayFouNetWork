package jfnwp.Chess;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jfnwp.Interfaces.IPosition;

public class Position implements IPosition{
	
	private static Logger logger = LogManager.getLogger(Position.class
			.getName());
	
	private int x;
	private int y;
	
	public Position(){
		this.x = 0;
		this.y = 0;
	}
	
	public Position(String sPosition){
		logger.info("constructeur Position " +sPosition);
		String[] splitString = sPosition.split(",", 20);
		this.x = Integer.valueOf(splitString[0]);
		this.y = Integer.valueOf(splitString[1]);
	}
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String toString(){		
		return  x + "," + y;
	}
}
