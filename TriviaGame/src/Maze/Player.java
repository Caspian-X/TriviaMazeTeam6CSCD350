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
    	
    	while(!maze.getCurrentRoom().isExit()) {
    		System.out.println("Move(WASD)||Save Game:R");
    		System.out.print("Enter choice:");
	    	String move = sc.next(); 
	    	
	    	System.out.println("\n\n\n\n\n\n\n\n");
	    	
	    	if(move.toLowerCase().equals("w")) 
	    		maze.moveNorth();
	    	else if(move.toLowerCase().equals("a"))
	    		maze.moveWest();
	    	else if(move.toLowerCase().equals("s"))
	    		maze.moveSouth();
	    	else if(move.toLowerCase().equals("d"))
	    		maze.moveEast();
	    	else if(move.toLowerCase().equals("p")) //here is the secret button that prints the whole map
	    		MazeBuilder.printEntireMaze(maze);
	    	else if(move.toLowerCase().equals("r")) {
	    		saveGame(maze);
	    	}
	    	System.out.println(maze.getCurrentRoom());
    	}
    	
    	System.out.print("Congrats you have reached the exit");
		sc.close();
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