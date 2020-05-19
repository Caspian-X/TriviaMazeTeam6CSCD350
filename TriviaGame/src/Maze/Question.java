package Maze;

import java.util.ArrayList;
import java.util.Random;

public class Question {
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
	
	// This will be what allows each door to access a question
	public class QuestionGenerator
	{
		private ArrayList<Question> allQuestions = new ArrayList<Question>();
		
		public QuestionGenerator()
		{
			//Start with all true false
			ArrayList<String> trueMc = new ArrayList<String>();
			ArrayList<String> falseMc = new ArrayList<String>();
			trueMc.add("T");
			falseMc.add("F");
			trueMc.add("True");
			falseMc.add("False");
			allQuestions.add(new Question("The word “can’t” is an example of a contraction. (T/F)", trueMc));
			allQuestions.add(new Question("The boiling point of water is 221 degrees F. (T/F)", falseMc));
			allQuestions.add(new Question("Morocco is in Europe. (T/F)", falseMc));
			allQuestions.add(new Question("Kanye West and Kim Kardashian’s children are named North, Saint and Chicago. (T/F)", trueMc));
			allQuestions.add(new Question("The largest planet in our solar system is Neptune.. (T/F)", falseMc));
			allQuestions.add(new Question("Nike is the Greek goddess of victory. (T/F)", trueMc));
			allQuestions.add(new Question("Hot water freezes faster than cold water. (T/F)", trueMc));
			allQuestions.add(new Question("There are 88 keys in a piano. (T/F)", trueMc));
			allQuestions.add(new Question("Adolf Hitler was born in Germany. (T/F)", trueMc));
			allQuestions.add(new Question("Dolphins are fish. (T/F)", falseMc));
			allQuestions.add(new Question("Albert Einstein was awarded the Nobel Prize in Physics. (T/F)", trueMc));
			allQuestions.add(new Question("Freeware is software that is available for use at no monetary cost. (T/F)", trueMc));
			allQuestions.add(new Question("IPv6 Internet Protocol address is represented as eight groups of four Octal digits. (T/F)", falseMc));
			allQuestions.add(new Question("Octal number system contains digits from 0 - 7. (T/F)", trueMc));
			allQuestions.add(new Question("CPU stands for Central Performance Unit. (T/F)", falseMc));
			//end t/f
			//start multiple choice
			ArrayList<String> a = new ArrayList<String>();
			a.add("A");
			ArrayList<String> b = new ArrayList<String>();
			b.add("B");
			ArrayList<String> c = new ArrayList<String>();
			c.add("C");
			ArrayList<String> d = new ArrayList<String>();
			d.add("D");
			allQuestions.add(new Question("The Operating System is a:\r\n" + 
					"A. System Software\r\n" + 
					"B. Application Software\r\n" + 
					"C. Utility Software\r\n" + 
					"D. Malware", a));
			allQuestions.add(new Question("Files are organized in:\r\n" + 
					"A. RAM\r\n" + 
					"B. Cache\r\n" + 
					"C. Directories\r\n" + 
					"D. None of the above", c));
			allQuestions.add(new Question("Computer Hardware is:\r\n" + 
					"A. A medium for Data Communications\r\n" + 
					"B. A Physical device that is driven by data\r\n" + 
					"C. A Physical device driven by software\r\n" + 
					"D. All the above", d));
			allQuestions.add(new Question("Two types of Networks are:\r\n" + 
					"A. WAN and LAN\r\n" + 
					"B. LAN and SAT\r\n" + 
					"C. SAT and WAN\r\n" + 
					"D. LAN and LAT", a));
			allQuestions.add(new Question("Backing up of computer data is the process of:\r\n" + 
					"A. Reversing the files stored on the hard drive\r\n" + 
					"B. Putting your computer in the back of the room\r\n" + 
					"C. Storing an extra copy of data on the hard disk\r\n" + 
					"D. Storing an extra copy on an external storage device", d));		
			
		}
		
		public Question pickRandomQuestion()
		{
			Random r = new Random();
			int i = r.nextInt(allQuestions.size());
			return allQuestions.get(i);
		}
		
	}
}


