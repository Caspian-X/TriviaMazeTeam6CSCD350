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
		if(isPlayerStuck)
			System.out.println("No Way Out, You Lose");
		this.isPlayerStuck = isPlayerStuck;
	}

	public void moveNorth() {
		getCurrentRoom().setVisited(true);
		if(getCurrentRoom().getNorth().isClosed())
			System.out.println("North Door is Locked");
		else {
			if(getCurrentRoom().getNorth().answerQuestion())
				this.playerPositionRow--;
			else {
				getCurrentRoom().getNorth().close();
				if(allDoorsLocked()) 
					setPlayerStuck(true);
			}
		}
	}
	
	public void moveSouth() {
		getCurrentRoom().setVisited(true);
		if(getCurrentRoom().getSouth().isClosed())
			System.out.println("South Door is Locked");
		else {
			if(getCurrentRoom().getSouth().answerQuestion())
				this.playerPositionRow++;
			else {
				getCurrentRoom().getSouth().close();
				if(allDoorsLocked()) 
					setPlayerStuck(true);
			}
		}
	}
	
	public void moveEast() {
		getCurrentRoom().setVisited(true);
		if(getCurrentRoom().getEast().isClosed())
			System.out.println("East Door is Locked");
		else {
			if(getCurrentRoom().getEast().answerQuestion())
				this.playerPositionCol++;
			else {
				getCurrentRoom().getEast().close();
				if(allDoorsLocked()) 
					setPlayerStuck(true);
			}
		}
	}
	
	public void moveWest() {
		getCurrentRoom().setVisited(true);
		if(getCurrentRoom().getWest().isClosed())
			System.out.println("West Door is Locked");
		else {
			if(getCurrentRoom().getWest().answerQuestion())
				this.playerPositionCol--;
			else {
				getCurrentRoom().getWest().close();
				if(allDoorsLocked()) 
					setPlayerStuck(true);
			}
		}
	}

}
