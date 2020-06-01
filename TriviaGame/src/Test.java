import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import Maze.Maze;
import Maze.MazeBuilder;
import Maze.Room;

public class Test 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner input = new Scanner(System.in);
		Maze maze = new Maze();
		maze = MazeBuilder.buildMaze();
		MazeBuilder.printEntireMaze(maze);
		
		File file = new File("tests.txt");
		PrintStream print = new PrintStream(file);
		PrintStream console = System.out;
		//System.setOut(print);
		
		Room room = new Room();
		System.out.println("Room question test");
		room.getEast().getQuestion().PromptQuestion();
		room.getEast().getQuestion().CheckAnswer(input.nextLine());
	}
}
