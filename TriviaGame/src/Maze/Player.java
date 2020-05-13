package Maze;

import java.util.Scanner;

public class Player {
	public static void main(String[] args) {
		
		Maze maze = MazeBuilder.buildMaze();

		System.out.println(getCurrentRoom(maze));
		
    	Scanner sc = new Scanner(System.in);
    	
    	while(!getCurrentRoom(maze).isExit()) {
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
	    	System.out.println(getCurrentRoom(maze));
    	}
    	
    	System.out.print("Congrats you have reached the exit");
		sc.close();
	}
	
	private static Room getCurrentRoom(Maze maze) {
		return maze.getRooms()[maze.getPlayerPositionRow()][maze.getPlayerPositionCol()];
	}
	
	
}
