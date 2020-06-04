package Junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Maze.Question;
import Maze.RoomItemHint;

class RoomItemHintTest 
{
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
	void testGiveHint() 
	{
		RoomItemHint hint = new RoomItemHint(1);
		hint.giveHint(question);
		String q = read.nextLine();
		assertEquals("Not False", q);
		assertEquals(0, hint.usesLeft());
		
		System.setOut(console);
	}

}
