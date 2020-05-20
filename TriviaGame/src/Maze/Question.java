package Maze;

import java.io.Serializable;
import java.util.ArrayList;


public class Question implements Serializable{
	private String questionText = "Sample Question, Type your answer:";
	//All question answers will be forced into lower case
	private ArrayList<String> questionAnswers = new ArrayList<String>();
	
	public Question(String question, ArrayList<String> answers)
	{
		questionText = question;
		questionAnswers = answers;
	}
	
	public void PromptQuestion()
	{
		System.out.print(questionText);
	}
	
	public boolean CheckAnswer(String givenAnswer)
	{
		for(String answer : questionAnswers)
		{
			if(answer.toLowerCase().equals(givenAnswer.toLowerCase())) 
			{
				System.out.print("You answered correctly, you may pass.");
				return true;
			}
		}
		System.out.print("Sorry that is Incorrect. The door is now Locked");
		return false;
	}
}
