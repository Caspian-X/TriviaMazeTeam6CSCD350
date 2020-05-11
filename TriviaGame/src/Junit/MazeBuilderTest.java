package Junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Maze.Maze;
import Maze.MazeBuilder;

class MazeBuilderTest {

	File file;
	Maze maze;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		maze = new Maze();
		maze = MazeBuilder.buildMaze();
		file = new File("tests.txt");
		PrintStream print = new PrintStream(file);
		System.setOut(print);
	}

	@Test
	void testBuildMaze() throws FileNotFoundException 
	{
		MazeBuilder.printEntireMaze(maze);
		Scanner read = new Scanner(file);
		String mazeTest = "";
		while (read.hasNextLine())
		{
			mazeTest += read.nextLine() + "\n";
		}
		String checkMaze = "***********\n" + 
				"* | | | | *\n" + 
				"*-*-*-*-*-*\n" + 
				"* | | | | *\n" + 
				"*-*-*-*-*-*\n" + 
				"* | | | | *\n" + 
				"*-*-*-*-*-*\n" + 
				"* | | | | *\n" + 
				"*-*-*-*-*-*\n" + 
				"* | | | | *\n" + 
				"***********\n";
		PrintStream console = System.out;
		System.setOut(console);
		assertEquals(checkMaze, mazeTest);
		read.close();
	}

}
