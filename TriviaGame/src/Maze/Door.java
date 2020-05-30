package Maze;

import java.io.Serializable;
import java.util.Random;

public class Door implements Serializable{
	private boolean closed = false;
	private Question question;
	
	public Door()
	{
		Random ran = new Random();
		int r = ran.nextInt(20) + 1;
		this.question = new Question(r);
	}
	
	public boolean isClosed() {
		return this.closed;
	}
	
	public void open() {
		this.closed = false;
	}
	public void close() {
		this.closed = true;
	}
	
	public Question getQuestion() {
		return this.question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public boolean answerQuestion() {
		this.question.PromptQuestion();
		String answer = Player.sc.next();
		return this.question.CheckAnswer(answer);
	}
}