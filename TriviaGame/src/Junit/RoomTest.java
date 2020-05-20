package Junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Maze.Room;

class RoomTest 
{
	
	File file;
	PrintStream console;
	Room room;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		room = new Room();
		file = new File("tests.txt");
		PrintStream print = new PrintStream(file);
		console = System.out;
		System.setOut(print);
	}

	@Test
	void testToString() throws FileNotFoundException 
	{
		String checkRoom = "*-*\n" + 
				"| |\n" + 
				"*-*\n";
		System.out.print(room.toString());
		Scanner read = new Scanner(file);
		String roomTest = "";
		while (read.hasNextLine())
		{
			roomTest += read.nextLine() + "\n";
		}
		assertEquals(checkRoom, roomTest);
		read.close();
		System.setOut(console);
	}

}
