package Maze;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Question implements Serializable{
	private String questionText = "";
	//All question answers will be forced into lower case
	private String questionAnswer = "";
	private String hint = "";
	private boolean answeredCorrectly = false;
	
	public Question(int num)
	{
		SQLiteDB db = new SQLiteDB();
		this.questionText = db.getQuestion(num);
		this.questionAnswer = db.getAnswer(num);
		this.hint = db.getHint(num);
		db.close();
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
	
	public String GetAnswer()
	{
		return this.questionAnswer;
	}
	
	public String GetHint()
	{
		return this.hint;
	}

	public boolean isAnsweredCorrectly() {
		return answeredCorrectly;
	}

	public void setAnsweredCorrectly(boolean answeredCorrectly) {
		this.answeredCorrectly = answeredCorrectly;
	}
}


