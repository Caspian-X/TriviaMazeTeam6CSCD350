import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import Maze.Maze;
import Maze.MazeBuilder;
import Maze.Room;

public class Test 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Maze maze = new Maze();
		maze = MazeBuilder.buildMaze();
		MazeBuilder.printEntireMaze(maze);
		
		File file = new File("tests.txt");
		PrintStream print = new PrintStream(file);
		PrintStream console = System.out;
		System.setOut(print);
		
		Room room = new Room();
		room.initializeRoom();
		System.out.print(room.toString());
	}
}
