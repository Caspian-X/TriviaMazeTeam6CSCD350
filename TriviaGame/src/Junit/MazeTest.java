package Junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Maze.Door;
import Maze.Maze;
import Maze.MazeBuilder;
import Maze.Room;

class MazeTest {

	File file;
	Maze maze;
	Scanner read;
	PrintStream console;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		maze = new Maze();
		maze = MazeBuilder.buildMaze();
		file = new File("tests.txt");
		PrintStream print = new PrintStream(file);
		console = System.out;
		System.setOut(print);
		maze.setPlayerPositionCol(3);
		maze.setPlayerPositionRow(3);
		read = new Scanner(file);
	}

	@Test
	void testMoveNorth() 
	{
		int a = maze.getPlayerPositionRow();
		int b = maze.getPlayerPositionCol();
		maze.moveNorth();
		int c = maze.getPlayerPositionRow();
		int d = maze.getPlayerPositionCol();
		assertTrue(a == c + 1 && b == d);
		
		Room[][] rooms = maze.getRooms();
		Door door = rooms[2][3].getNorth();
		door.close();
		maze.moveNorth();
		assertEquals(read.nextLine(), "North Door is Locked");
		System.setOut(console);
	}
	
	@Test
	void testMoveSouth() 
	{
		int a = maze.getPlayerPositionRow();
		int b = maze.getPlayerPositionCol();
		maze.moveSouth();
		int c = maze.getPlayerPositionRow();
		int d = maze.getPlayerPositionCol();
		assertTrue(a == c - 1 && b == d);
		
		Room[][] rooms = maze.getRooms();
		Door door = rooms[4][3].getSouth();
		door.close();
		maze.moveSouth();
		assertEquals(read.nextLine(), "South Door is Locked");
		System.setOut(console);
	}

	@Test
	void testMoveEast() 
	{
		int a = maze.getPlayerPositionRow();
		int b = maze.getPlayerPositionCol();
		maze.moveEast();
		int c = maze.getPlayerPositionRow();
		int d = maze.getPlayerPositionCol();
		assertTrue(a == c && b + 1 == d);
		
		Room[][] rooms = maze.getRooms();
		Door door = rooms[3][4].getEast();
		door.close();
		maze.moveEast();
		assertEquals(read.nextLine(), "East Door is Locked");
		System.setOut(console);
	}
	
	@Test
	void testMoveWest() 
	{
		int a = maze.getPlayerPositionRow();
		int b = maze.getPlayerPositionCol();
		maze.moveWest();
		int c = maze.getPlayerPositionRow();
		int d = maze.getPlayerPositionCol();
		assertTrue(a == c && b - 1 == d);
		
		Room[][] rooms = maze.getRooms();
		Door door = rooms[3][2].getWest();
		door.close();
		maze.moveWest();
		assertEquals(read.nextLine(), "West Door is Locked");
		System.setOut(console);
	}
}
