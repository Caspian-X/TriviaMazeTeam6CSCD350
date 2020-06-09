//written by: Isaiah Weaver
package Junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Maze.Question;

class QuestionTest {

	Question question;
	File file;
	PrintStream console;
	Scanner read;
	
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
	void testPromptQuestion()
	{
		question.PromptQuestion();
		String q = read.nextLine();
		assertEquals("The word “can’t” is an example of a contraction.(T/F)", q);
		
		System.setOut(console);
	}
	
	@Test
	void testCheckAnswer() 
	{
		assertTrue(question.CheckAnswer("T"));
		assertTrue(question.CheckAnswer("t"));
		assertFalse(question.CheckAnswer("f"));
		
		System.setOut(console);
	}

}
