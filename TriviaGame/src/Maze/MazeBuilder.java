package Maze;

import java.util.Random;

public class MazeBuilder {
	private static Maze maze = new Maze();
	
	public static void printEntireMaze(Maze maze) {
		Room[][] rooms = maze.getRooms();
		for(int i = 0; i < 5; i++) {
			printRow(i,0,4,rooms);
			if(i == 4)
				printBottomRow(i,0,4,rooms);
		}
	}
	
	public static void printRow(int row,int columnToStartAt, int columnToEndAt, Room[][] rooms) {
		for(int i = 0; i < 2; i++) {
			for(int j = columnToStartAt; j < columnToEndAt+1; j++) {
				Room currentRoom = rooms[row][j];
				
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
					
					System.out.print(currentRoom.roomStatus());
					
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
	
	public static void printBottomRow(int row,int columnToStartAt, int columnToEndAt, Room[][] rooms) {
		for(int j = columnToStartAt; j < columnToEndAt+1; j++) {
			System.out.print("*");
			
			if(rooms[row][j].getSouth().isBorder())
				System.out.print("*");
			else if(rooms[row][j].getSouth().isClosed())
				System.out.print("+");
			else
				System.out.print("-");
			
			if(j==columnToEndAt) 
				System.out.print("*");
		}
		System.out.println();
	}
		
	public static Maze buildMaze() {
		initiliazeRooms(maze);
		return maze;
	}

	private static void initiliazeRooms(Maze maze) {
		Room[][] roomSetup = new Room[5][5];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				roomSetup[i][j] = new Room();
			}
		}
		initiliazeDoors(roomSetup);
		setExitAndEntrance(roomSetup);
		maze.setRooms(roomSetup);
	}
	
	private static void setExitAndEntrance(Room[][] roomSetup) {
		Random ran = new Random();
		
		//Setup Entrance
		int entranceColumn = ran.nextInt(5);
		int entranceRow = ran.nextInt(5);
		
		roomSetup[entranceRow][entranceColumn].setEntrance(true);
		maze.setPlayerPositionRow(entranceRow);
		maze.setPlayerPositionCol(entranceColumn);

		//Setup Exit
		int exitColumn = ran.nextInt(5);
		int exitRow = ran.nextInt(5);
		if(roomSetup[exitRow][exitColumn].isEntrance()) {
			while(roomSetup[exitRow][exitColumn].isEntrance()) {
				exitColumn = ran.nextInt(5);
				exitRow = ran.nextInt(5);
			}
			roomSetup[exitRow][exitColumn].setExit(true);
		}else {
			roomSetup[exitRow][exitColumn].setExit(true);
		}
		
	}
	
	private static void initiliazeDoors(Room[][] roomSetup) {
		lockAllBorderDoors(roomSetup);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Room currentRoom = roomSetup[i][j];
				//Check south close north
				if(i < 4)
					currentRoom.setSouth(roomSetup[i+1][j].getNorth());
				//Check east close west
				if(j < 4)
					currentRoom.setEast(roomSetup[i][j+1].getWest());
				//Check north close south
				if(i > 0)
					currentRoom.setNorth(roomSetup[i-1][j].getSouth());
				//Check west close east
				if(j > 0)
					currentRoom.setWest(roomSetup[i][j-1].getEast());
			}
		}
	}

	private static void lockAllBorderDoors(Room[][] roomSetup) {
		for(int i = 0; i < 5; i++) {
			roomSetup[0][i].getNorth().setBorder(true);//Top rooms
			roomSetup[4][i].getSouth().setBorder(true);//Bottom rooms
			roomSetup[i][0].getWest().setBorder(true);//LeftSide rooms
			roomSetup[i][4].getEast().setBorder(true);//RightSide rooms
		}
	}
	
	public static Maze getMaze() {
		return maze;
	}
	
}
