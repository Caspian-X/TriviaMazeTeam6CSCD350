package Maze;

import java.util.ArrayList;


public class Question {
	private String questionText = "";
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
				return true;
		}
		return false;
	}
}
