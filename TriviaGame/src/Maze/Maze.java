package Maze;

import java.io.Serializable;

public class Maze implements Serializable{
	private Room [][] rooms;
	private int playerPositionRow, playerPositionCol;
	private boolean isPlayerStuck = false;
	public RoomItemKey roomKeys;
	public RoomItemHint roomHints;

	public Maze() {
		this.playerPositionRow = 0;
		this.playerPositionCol = 0;
		roomKeys = new RoomItemKey();
		roomHints = new RoomItemHint();
	}
	
	public Room[][] getRooms(){
		return rooms;
	}
	
	public void setRooms(Room[][] rooms) {
		this.rooms = rooms;
	}
	
	public int getPlayerPositionRow() {
		return playerPositionRow;
	}

	public void setPlayerPositionRow(int playerPositionRow) {
		this.playerPositionRow = playerPositionRow;
	}

	public int getPlayerPositionCol() {
		return playerPositionCol;
	}

	public void setPlayerPositionCol(int playerPositionCol) {
		this.playerPositionCol = playerPositionCol;
	}
	
	public Room getCurrentRoom() {
		return this.rooms[playerPositionRow][playerPositionCol];
	}
	
	private boolean allDoorsLocked() {
		if(getCurrentRoom().getNorth().isClosed() && 
		   getCurrentRoom().getSouth().isClosed() && 
		   getCurrentRoom().getEast().isClosed()  && 
		   getCurrentRoom().getWest().isClosed()) {
				return true;
		}
		return false;
	}

	public boolean isPlayerStuck() {
		return isPlayerStuck;
	}

	public void setPlayerStuck(boolean isPlayerStuck) {
		this.isPlayerStuck = isPlayerStuck;
	}

	public void moveNorth() {
		if(move("n"))
			this.playerPositionRow--;
	}
	
	public void moveSouth() {
		if(move("s"))
			this.playerPositionRow++;
	}
	
	public void moveEast() {
		if(move("e"))
			this.playerPositionCol++;
	}
	
	public void moveWest() {
		if(move("w"))
			this.playerPositionCol--;
	}
	
	
	private boolean move(String direction) {
		getCurrentRoom().setVisited(true);
		Door currentDoor = null;
		if(direction.toLowerCase().equals("n"))
			currentDoor = getCurrentRoom().getNorth();
		else if(direction.toLowerCase().equals("s"))
			currentDoor = getCurrentRoom().getSouth();
		else if(direction.toLowerCase().equals("e"))
			currentDoor = getCurrentRoom().getEast();
		else if(direction.toLowerCase().equals("w"))
			currentDoor = getCurrentRoom().getWest();
		
		if(currentDoor.isClosed())
			System.out.println("This Door is Locked");
		else {
			if(currentDoor.getQuestion().isAlreadyAnswered())
				return true;
			else {
				if(currentDoor.answerQuestion()) {
					currentDoor.getQuestion().setAlreadyAnswered(true);
					return true;	
				}
				else {
					currentDoor.close();
					if(allDoorsLocked()) 
						setPlayerStuck(true);
				}
			}
		}
		return false;
	}
}
