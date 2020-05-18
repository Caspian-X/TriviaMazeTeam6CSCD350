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
	
	public Room getCurrentRoom() {
		return this.rooms[playerPositionRow][playerPositionCol];
	}
	
	public void moveNorth() {
		getCurrentRoom().setVisited(true);
		if(getCurrentRoom().getNorth().isClosed())
			System.out.println("North Door is Locked");
		else {
			if(getCurrentRoom().getNorth().answerQuestion())
				this.playerPositionRow--;
			else 
				getCurrentRoom().getNorth().close();
		}
	}
	
	public void moveSouth() {
		getCurrentRoom().setVisited(true);
		if(getCurrentRoom().getSouth().isClosed())
			System.out.println("South Door is Locked");
		else {
			if(getCurrentRoom().getSouth().answerQuestion())
				this.playerPositionRow++;
			else 
				getCurrentRoom().getSouth().close();
		}
	}
	
	public void moveEast() {
		getCurrentRoom().setVisited(true);
		if(getCurrentRoom().getEast().isClosed())
			System.out.println("East Door is Locked");
		else {
			if(getCurrentRoom().getEast().answerQuestion())
				this.playerPositionCol++;
			else 
				getCurrentRoom().getEast().close();
		}
	}
	
	public void moveWest() {
		getCurrentRoom().setVisited(true);
		if(getCurrentRoom().getWest().isClosed())
			System.out.println("West Door is Locked");
		else {
			if(getCurrentRoom().getWest().answerQuestion())
				this.playerPositionCol--;
			else 
				getCurrentRoom().getWest().close();
		}
	}
}
