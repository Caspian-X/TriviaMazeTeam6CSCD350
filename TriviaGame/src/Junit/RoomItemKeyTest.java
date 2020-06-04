package Junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Maze.Door;
import Maze.Question;
import Maze.RoomItemKey;

class RoomItemKeyTest {

	Question question;
	File file;
	Scanner read;
	PrintStream console;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		file = new File("tests.txt");
		read = new Scanner(file);
		PrintStream print = new PrintStream(file);
		console = System.out;
		System.setOut(print);
		
		question = new Question(6);
	}

	@Test
	void testUnlockDoorWithKey() 
	{
		RoomItemKey key = new RoomItemKey(2);
		Door door = new Door();
		door.close();
		assertTrue(door.isClosed());
		key.unlockDoorWithKey(door);
		assertFalse(door.isClosed());
		
		assertEquals(key.usesLeft(), 1);
		
		door.setBorder(true);
		key.unlockDoorWithKey(door);
		String q = read.nextLine();
		assertEquals(q, "Cannot open Border");
		assertEquals(key.usesLeft(), 1);
		
		System.setOut(console);
	}

}
