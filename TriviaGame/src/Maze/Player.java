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
	private static boolean quit = false;
	private static Maze maze = null;
	
	public static void main(String[] args) {
		
		while(maze == null && !quit) {
			runMenu();
		}
		if(maze != null) {
			
	    	while(!maze.getCurrentRoom().isExit() && !maze.isPlayerStuck() && !quit) {
	    		//check to see if current room has any keys or hints
	    		checkRoomForKeys();
	    		
	    		String playerResponse = "";
	    		
	    		boolean success = true;
	    		do
	    		{
	    			if (success == false)
	    			{
	    				System.out.println(maze.getCurrentRoom());
	    				playerResponse = printPlayerOptions();
	    				System.out.print("\n");
	    			}
	    			success = playerAction(playerResponse);
	    		} while(success == false);
		    	
		    	System.out.println("\n");
		    	
	    	}
	    	if(maze.isPlayerStuck())
	    		System.out.println("No Way Out, You Lose");
	    	else if(quit)
	    		System.out.println("Winners never Quit");
	    	else
	    		System.out.print("Congrats you have reached the exit");
		}
		sc.close();
	}	
	
	
	private static void checkRoomForKeys() {
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
	
	
	private static String promptScreen(boolean printMenu) {
		System.out.println(printMenu ? "A.New Game" : "A.Multiple Choice");
		System.out.println(printMenu ? "B.Load Game" : "B.True/False");
		System.out.println(printMenu ? "C.Add Questions": "C.Free Response Question");
		System.out.print(printMenu ? "Q.Quit\n": "");
		System.out.println(printMenu ? "What would you like to do:" : "What Type of Question would you like to add:");
		return sc.nextLine();
	}
	
	
	private static void runMenu() {
		String menuOption = promptScreen(true);
		if(menuOption.toLowerCase().equals("a")) {
			maze = new Maze(5,5);
			maze.buildMaze();
		}
		else if(menuOption.toLowerCase().equals("b"))
			loadSavedGame();
		else if(menuOption.toLowerCase().equals("c")) 
			addQuestion();
		else if(menuOption.toLowerCase().equals("q"))
			quit = true;
	}
	
	private static void addQuestion() {
		String typeOfQuestion = promptScreen(false);
		
		String[] question = new String[7];
	
		System.out.println("What is the question you would like to add:");
		question[0] = sc.nextLine();
		if(typeOfQuestion.toLowerCase().equals("a")) {
			for(int i = 1; i < 5; i++) {
				String letter = i == 1 ? "A" : (i == 2 ? "B" : (i == 3 ? "C" : "D"));
				System.out.println("What would you like to add for option "+ letter + ":");
				question[i] = letter + "." + sc.nextLine();
			}
		}else
			question[1] = question[2] = question[3] = question[4] = "''";
		
		System.out.println("What is the answer" + (typeOfQuestion.toLowerCase().equals("a") ? "(Enter letter correspoding to question EX: A)" : "(For True/False enter just T or F for Answer)"));
		question[5] = sc.next().toUpperCase();
		
		System.out.println("What hint would you like to add:");
		question[6] = sc.next();
		
		SQLiteDB db = new SQLiteDB();
		db.addQuestion(question);
		db.close();
	}
	

	private static String printPlayerOptions() {
		System.out.println("Keys(K) : " + maze.roomKeys.usesLeft());
		System.out.println("Hints(H) : " + maze.roomHints.usesLeft());
		System.out.println("Move(WASD)||Save Game:R||Quit:Q");
		System.out.print("Enter choice:");
		return sc.next();
	}
	
	private static boolean playerAction(String move) {
		
		boolean success = true;
		
		if(move.toLowerCase().equals("w")) 
    		maze.moveNorth();
    	else if(move.toLowerCase().equals("a"))
    		maze.moveWest();
    	else if(move.toLowerCase().equals("s"))
    		maze.moveSouth();
    	else if(move.toLowerCase().equals("d"))
    		maze.moveEast();
    	else if(move.toLowerCase().equals("k"))
    		useKey();	
    	else if(move.toLowerCase().equals("h"))
    		useHint();	
    	else if(move.toLowerCase().equals("p")) //here is the secret button that prints the whole map
    		maze.printEntireMaze();
    	else if(move.toLowerCase().equals("r")) 
    		saveGame();
    	else if(move.toLowerCase().equals("q"))
    		quit = true;
    	else
    		success = false;
		
		return success;
	}
	
	
	private static void useKey() {
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
	
	private static void useHint() {
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
	
	
	private static void loadSavedGame() {
		try{ 
			maze = new Maze(5,5);
			if(new File(saveFile).exists()) {
				System.out.println("Loading Game...");
				
				// Reading the object from a file 
				FileInputStream file = new FileInputStream(saveFile); 
				ObjectInputStream in = new ObjectInputStream(file); 
				// Method for deserialization of object 
				maze = (Maze)in.readObject(); 
				
				in.close(); 
				file.close(); 
				
				System.out.println("Game Loaded");
				System.out.println("Row = " + (maze.getPlayerPositionRow()+1));
				System.out.println("Col = " + (maze.getPlayerPositionCol()+1));
			}else {
				System.out.println("No Save found.\nStarting New Game...");
				maze.buildMaze(); 
				System.out.println("Game Ready");
			}
		}catch(Exception e) { 
			e.printStackTrace();
		}//end catch
	}
	
	private static void saveGame() {
		try {
			//Saving of object in a file 
			FileOutputStream file = new FileOutputStream(saveFile); 
			ObjectOutputStream out = new ObjectOutputStream(file); 
			
			// Method for serialization of object 
			out.writeObject(maze); 

			out.close(); 
			file.close(); 
			
			System.out.println("Game Saved"); 
			System.out.println("Row = " + maze.getPlayerPositionRow()); 
			System.out.println("Col = " + maze.getPlayerPositionCol());
		}catch(Exception e) { 
			e.printStackTrace();
		} 
	}
	
}
