package Maze;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Question implements Serializable{
	private String questionText = "";
	//All question answers will be forced into lower case
	private String questionAnswer = "";
	public Question(int num)
	{
		SQLiteDB db = new SQLiteDB();
		this.questionText = db.getQuestion(num);
		this.questionAnswer = db.getAnswer(num);
	}
	
	public void PromptQuestion()
	{
		System.out.println(questionText);
	}
	
	public boolean CheckAnswer(String givenAnswer)
	{
		if(givenAnswer.toLowerCase().equals(this.questionAnswer.toLowerCase())) 
		{
			System.out.println("You answered correctly, you may pass.");
			return true;
		}
		System.out.println("Sorry that is Incorrect. The door is now Locked");
		return false;
	}
	
}


