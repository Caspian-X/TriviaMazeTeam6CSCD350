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
	ArrayList<String> answers = new ArrayList<String>();
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
		
		answers.add("AB");
		question = new Question("Test Question: ", answers);
	}

	@Test
	void testPromptQuestion()
	{
		question.PromptQuestion();
		String q = read.nextLine();
		assertEquals("Test Question: ", q);
		
		System.setOut(console);
	}
	
	@Test
	void testCheckAnswer() 
	{
		assertTrue(question.CheckAnswer("AB"));
		assertFalse(question.CheckAnswer("A"));
		
		System.setOut(console);
	}

}
