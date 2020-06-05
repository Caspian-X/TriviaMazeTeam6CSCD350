package Maze;

import java.io.Serializable;
import java.util.Random;

public class Maze implements Serializable{
	private Room [][] rooms;
	private int playerPositionRow, playerPositionCol;
	private boolean isPlayerStuck = false;
	public RoomItemKey roomKeys;
	public RoomItemHint roomHints;

	public Maze(int rows, int columns) {
		this.rooms = new Room[rows][columns];
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
		Door currentDoor = null;
		if(direction.toLowerCase().equals("n"))
			currentDoor = getCurrentRoom().getNorth();
		else if(direction.toLowerCase().equals("s"))
			currentDoor = getCurrentRoom().getSouth();
		else if(direction.toLowerCase().equals("e"))
			currentDoor = getCurrentRoom().getEast();
		else if(direction.toLowerCase().equals("w"))
			currentDoor = getCurrentRoom().getWest();
		
		if(currentDoor.isClosed()|| currentDoor.isBorder())
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
					if(!mazeCanBeTraversed(this.rooms)) 
						setPlayerStuck(true);
				}
			}
		}
		return false;
	}
	
	
	private boolean mazeCanBeTraversed(Room[][] rooms) {
		boolean result = mazeCanBeTraversed(this.playerPositionRow,this.playerPositionCol);
		resetAllRoomsToNotVisited();
		return result;
	}
	
	private void resetAllRoomsToNotVisited() {
		for(int i = 0; i < this.rooms.length; i++) {
			for(int j = 0; j < this.rooms[0].length; j++) {
				this.rooms[i][j].setVisited(false);
			}
		}
		
	}

	private boolean mazeCanBeTraversed(int row, int col) {
		if(isValidRoom(row,col)) {
			if(this.rooms[row][col].isExit())
				return true;
			else {
				this.rooms[row][col].setVisited(true);
				if(canEnter(row,col,"S")) {
					if(mazeCanBeTraversed(row+1,col))
						return true;
					}
				if(canEnter(row,col,"E")) { 
					if(mazeCanBeTraversed(row,col+1))
						return true;
					}
				if(canEnter(row,col,"N")) {
					if(mazeCanBeTraversed(row-1,col))
						return true;
					}
				if(canEnter(row,col,"W")) {
					if(mazeCanBeTraversed(row,col-1))
						return true;
					}
			}
		}	
		return false;
	}
	
	private boolean isValidRoom(int row, int col) {
		if(row > this.rooms.length-1 || row < 0 || col > this.rooms[row].length-1 || col < 0)
			return false;
		return true;
	}
	
	private boolean canEnter(int row,int col, String door) {

		Room room = this.rooms[row][col];
		if(door.equals("S"))
			if(!room.getSouth().isBorder() && !room.getSouth().isClosed())
				if(!rooms[row+1][col].isVisited())
					return true;
		
		if(door.equals("N"))
			if(!room.getNorth().isBorder() && !room.getNorth().isClosed())
				if(!rooms[row-1][col].isVisited())
					return true;
		
		if(door.equals("E"))
			if(!room.getEast().isBorder() && !room.getEast().isClosed())
				if(!rooms[row][col+1].isVisited())
					return true;
		
		if(door.equals("W"))
			if(!room.getWest().isBorder() && !room.getWest().isClosed())
				if(!rooms[row][col-1].isVisited())
					return true;
		
		return false;
	}
	
	
	public void printEntireMaze() {
		for(int i = 0; i < 5; i++) {
			printRow(i,0,4);
			if(i == 4)
				printBottomRow(i,0,4);
		}
	}
	
	public void printRow(int row,int columnToStartAt, int columnToEndAt) {
		for(int i = 0; i < 2; i++) {
			for(int j = columnToStartAt; j < columnToEndAt+1; j++) {
				Room currentRoom = this.rooms[row][j];
				
				//Print Top
				if(i == 0) {
					System.out.print("*");
					
					if(currentRoom.getNorth().isBorder())
						System.out.print("*");
					else if(currentRoom.getNorth().isClosed())
						System.out.print("+");
					else
						System.out.print("-");
					
					if(j==columnToEndAt) 
						System.out.print("*");
				}
				//Print Middle
				else {
					if(j==0)
						System.out.print("*");
					else {
						if(currentRoom.getWest().isBorder())
							System.out.print("*");
						else if(currentRoom.getWest().isClosed())
							System.out.print("+");
						else
							System.out.print("|");
					}
					
					String middle = currentRoom.roomStatus();
					if(middle.equals(" "))
						if(this.playerPositionRow == row && this.playerPositionCol == j)
							middle = "O";
					System.out.print(middle);
					
					if(j==columnToEndAt) {
						if(currentRoom.getEast().isBorder())
							System.out.print("*");
						else if(currentRoom.getEast().isClosed())
							System.out.print("+");
						else
							System.out.print("|");
					}
				}
			}
			System.out.println();
		}
	}
	
	public void printBottomRow(int row,int columnToStartAt, int columnToEndAt) {
		for(int j = columnToStartAt; j < columnToEndAt+1; j++) {
			System.out.print("*");
			
			if(this.rooms[row][j].getSouth().isBorder())
				System.out.print("*");
			else if(this.rooms[row][j].getSouth().isClosed())
				System.out.print("+");
			else
				System.out.print("-");
			
			if(j==columnToEndAt) 
				System.out.print("*");
		}
		System.out.println();
	}
		
	public void buildMaze() {
		initiliazeRooms();
	}

	private void initiliazeRooms() {
		for (int i = 0; i < this.rooms.length; i++) {
			for (int j = 0; j < this.rooms[0].length; j++) {
				this.rooms[i][j] = new Room();
			}
		}
		initiliazeDoors();
		setExitAndEntrance();
	}
	
	private void setExitAndEntrance() {
		Random ran = new Random();
		
		//Setup Entrance
		int entranceColumn = ran.nextInt(5);
		int entranceRow = ran.nextInt(5);
		
		this.rooms[entranceRow][entranceColumn].setEntrance(true);
		this.playerPositionRow = entranceRow;
		this.playerPositionCol = entranceColumn;

		//Setup Exit
		int exitColumn = ran.nextInt(5);
		int exitRow = ran.nextInt(5);
		if(this.rooms[exitRow][exitColumn].isEntrance()) {
			while(this.rooms[exitRow][exitColumn].isEntrance()) {
				exitColumn = ran.nextInt(5);
				exitRow = ran.nextInt(5);
			}
			this.rooms[exitRow][exitColumn].setExit(true);
		}else {
			this.rooms[exitRow][exitColumn].setExit(true);
		}
		
	}
	
	private void initiliazeDoors() {
		lockAllBorderDoors();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Room currentRoom = this.rooms[i][j];
				//Check south close north
				if(i < 4)
					currentRoom.setSouth(this.rooms[i+1][j].getNorth());
				//Check east close west
				if(j < 4)
					currentRoom.setEast(this.rooms[i][j+1].getWest());
				//Check north close south
				if(i > 0)
					currentRoom.setNorth(this.rooms[i-1][j].getSouth());
				//Check west close east
				if(j > 0)
					currentRoom.setWest(this.rooms[i][j-1].getEast());
			}
		}
	}

	private void lockAllBorderDoors() {
		for(int i = 0; i < 5; i++) {
			this.rooms[0][i].getNorth().setBorder(true);//Top rooms
			this.rooms[4][i].getSouth().setBorder(true);//Bottom rooms
			this.rooms[i][0].getWest().setBorder(true);//LeftSide rooms
			this.rooms[i][4].getEast().setBorder(true);//RightSide rooms
		}
	}
	
	
}
