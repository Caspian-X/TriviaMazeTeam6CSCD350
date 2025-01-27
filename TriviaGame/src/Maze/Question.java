//Written By:Igor Svirgun, Griffin Foster
package Maze;

import java.io.Serializable;

public class Question implements Serializable{
	private String questionText = "";
	//All question answers will be forced into lower case
	private String questionAnswer = "";
	private String hint = "";
	private boolean alreadyAnswered = false;
	
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

	public boolean isAlreadyAnswered() 
	{
		return alreadyAnswered;
	}

	public void setAlreadyAnswered(boolean alreadyAnswered) 
	{
		this.alreadyAnswered = alreadyAnswered;
	}
}


