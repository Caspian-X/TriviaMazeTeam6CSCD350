package Maze;

import java.util.Scanner;

public class Player {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Maze maze = MazeBuilder.buildMaze();

		System.out.println(maze.getCurrentRoom());
    	
    	while(!maze.getCurrentRoom().isExit()) {
    		System.out.println("Move(WASD)");
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
	    	System.out.println(maze.getCurrentRoom());
    	}
    	
    	System.out.print("Congrats you have reached the exit");
		sc.close();
	}	
	
}
