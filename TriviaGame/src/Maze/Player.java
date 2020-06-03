package Maze;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Player {
	public static Scanner sc = new Scanner(System.in);
	public static String saveFile = "GameSaves.ser";
	
	public static void main(String[] args) {
		
		Maze maze = checkForSaves();	

		System.out.println(maze.getCurrentRoom());
    	
    	while(!maze.getCurrentRoom().isExit() && !maze.isPlayerStuck()) {
    		//check to see if current room has any keys or hints
    		checkRoomForKeys(maze);
    		
    		String playerResponse = printPlayerOptions(maze);
    		
    		playerAction(maze,playerResponse);
	    	
	    	System.out.println("\n\n\n\n\n\n");
	    	
	    	System.out.println(maze.getCurrentRoom());
    	}
    	if(maze.isPlayerStuck())
    		System.out.println("No Way Out, You Lose");
    	else
    		System.out.print("Congrats you have reached the exit");
		sc.close();
	}	
	
	
	private static void checkRoomForKeys(Maze maze) {
		if(maze.getCurrentRoom().getRoomItem() != null){
			if(maze.getCurrentRoom().getRoomItem() instanceof RoomItemKey && maze.getCurrentRoom().getRoomItem().usesLeft() > 0)
			{   				
				RoomItemKey roomKey = (RoomItemKey)maze.getCurrentRoom().getRoomItem();
				System.out.println(roomKey.toString());
				roomKey.useItem();
				maze.roomKeys.addItem();
			} else if(maze.getCurrentRoom().getRoomItem() instanceof RoomItemHint && maze.getCurrentRoom().getRoomItem().usesLeft() > 0) {
				RoomItemHint roomHint = (RoomItemHint)maze.getCurrentRoom().getRoomItem();
				System.out.println(roomHint.toString());
				roomHint.useItem();
				maze.roomHints.addItem();
			}
		}
	}
	
	
	private static String printPlayerOptions(Maze maze) {
		System.out.println("Keys(K) : " + maze.roomKeys.usesLeft());
		System.out.println("Hints(H) : " + maze.roomHints.usesLeft());
		System.out.println("Move(WASD)||Save Game:R");
		System.out.print("Enter choice:");
		return sc.next();
	}
	
	private static void playerAction(Maze maze,String move) {
		
		if(move.toLowerCase().equals("w")) 
    		maze.moveNorth();
    	else if(move.toLowerCase().equals("a"))
    		maze.moveWest();
    	else if(move.toLowerCase().equals("s"))
    		maze.moveSouth();
    	else if(move.toLowerCase().equals("d"))
    		maze.moveEast();
    	else if(move.toLowerCase().equals("k"))
    		useKey(maze);	
    	else if(move.toLowerCase().equals("h"))
    		useHint(maze);	
    	else if(move.toLowerCase().equals("p")) //here is the secret button that prints the whole map
    		MazeBuilder.printEntireMaze(maze);
    	else if(move.toLowerCase().equals("r")) {
    		saveGame(maze);
    	}
	}
	
	
	private static void useKey(Maze maze) {
		if(maze.roomKeys.usesLeft() > 0)
		{
			System.out.println(maze.getCurrentRoom());
			System.out.print("Which door would you like to unlock?(WASD):");
    		String doorToUnlock = sc.next(); 
    		if(doorToUnlock.toLowerCase().equals("w")) 
	    		maze.roomKeys.unlockDoorWithKey(maze.getCurrentRoom().getNorth());
	    	else if(doorToUnlock.toLowerCase().equals("a"))
	    		maze.roomKeys.unlockDoorWithKey(maze.getCurrentRoom().getWest());
	    	else if(doorToUnlock.toLowerCase().equals("s"))
	    		maze.roomKeys.unlockDoorWithKey(maze.getCurrentRoom().getSouth());
	    	else if(doorToUnlock.toLowerCase().equals("d"))
	    		maze.roomKeys.unlockDoorWithKey(maze.getCurrentRoom().getEast());
		}
		else
			System.out.println("You have no keys!");
	}
	
	private static void useHint(Maze maze) {
		if(maze.roomHints.usesLeft() > 0)
		{
			System.out.println(maze.getCurrentRoom());
			System.out.print("Which door would you like to use a hint on?(WASD):");
    		String doorToUnlock = sc.next(); 
    		if(doorToUnlock.toLowerCase().equals("w")) 
	    		maze.roomHints.giveHint(maze.getCurrentRoom().getNorth().getQuestion());
	    	else if(doorToUnlock.toLowerCase().equals("a"))
	    		maze.roomHints.giveHint(maze.getCurrentRoom().getWest().getQuestion());
	    	else if(doorToUnlock.toLowerCase().equals("s"))
	    		maze.roomHints.giveHint(maze.getCurrentRoom().getSouth().getQuestion());
	    	else if(doorToUnlock.toLowerCase().equals("d"))
	    		maze.roomHints.giveHint(maze.getCurrentRoom().getEast().getQuestion());
		}
		else
			System.out.println("You have no hints!");
	}
	
	
	private static Maze checkForSaves() {
		
		Maze maze = MazeBuilder.buildMaze();
		
		if(new File(saveFile).exists()) {
			if(loadGame()) {
				try{ 
					// Reading the object from a file 
					FileInputStream file = new FileInputStream(saveFile); 
					ObjectInputStream in = new ObjectInputStream(file); 
					// Method for deserialization of object 
					maze = (Maze)in.readObject(); 
					
					in.close(); 
					file.close(); 
					
					System.out.println("Game Loaded"); 
					System.out.println("Col = " + maze.getPlayerPositionCol()); 
					System.out.println("Row = " + maze.getPlayerPositionRow()); 
				}catch(Exception e) { 
					e.printStackTrace();
				}//end catch
			}//end if
		}//end if
		
		return maze;
	}
	
	private static boolean loadGame() {
		System.out.print("Game save found, would you like to load save: Y/N");
		if(sc.next().toLowerCase().equals("y"))
			return true;
		return false;
	}
	
	private static void saveGame(Maze maze) {
		try {
			//Saving of object in a file 
			FileOutputStream file = new FileOutputStream(saveFile); 
			ObjectOutputStream out = new ObjectOutputStream(file); 
			
			// Method for serialization of object 
			out.writeObject(maze); 

			out.close(); 
			file.close(); 
			
			System.out.println("Game Saved"); 
			System.out.println("Col = " + maze.getPlayerPositionCol()); 
			System.out.println("Row = " + maze.getPlayerPositionRow()); 
		}catch(Exception e) { 
			e.printStackTrace();
		} 
	}
	
}
