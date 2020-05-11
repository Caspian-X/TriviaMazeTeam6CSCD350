package Maze;

public class Maze {
	private Room [][] rooms;
	private int playerPositionRow, playerPositionCol;
	public Maze() {
		this.playerPositionRow = 0;
		this.playerPositionCol = 0;
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

	public void moveNorth() {
		if(this.rooms[playerPositionRow][playerPositionCol].getNorth().isClosed())
			System.out.println("North Door is Locked");
		else {
			this.playerPositionRow--;
		}
	}
	
	public void moveSouth() {
		if(this.rooms[playerPositionRow][playerPositionCol].getSouth().isClosed())
			System.out.println("South Door is Locked");
		else {
			this.playerPositionRow++;
		}
	}
	
	public void moveEast() {
		if(this.rooms[playerPositionRow][playerPositionCol].getEast().isClosed())
			System.out.println("East Door is Locked");
		else {
			this.playerPositionCol++;
		}
	}
	
	public void moveWest() {
		if(this.rooms[playerPositionRow][playerPositionCol].getWest().isClosed())
			System.out.println("West Door is Locked");
		else {
			this.playerPositionCol--;
		}
	}
	
}
