package jfnwp.Chess;

import jfnwp.Interfaces.IPosition;

public class Position implements IPosition{
	
	private int x;
	private int y;
	
	public Position(){
		this.x = 0;
		this.y = 0;
	}
	
	public Position(String sPosition){
		String[] splitString = sPosition.split(",", 20);
		this.x = Integer.valueOf(splitString[0].substring(1));
		this.y = Integer.valueOf(splitString[1].substring(1));
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
		return "(" + x + "," + y + ")";
	}
}
