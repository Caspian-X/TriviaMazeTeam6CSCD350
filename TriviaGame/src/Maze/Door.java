package Maze;

import java.util.Scanner;

public class Door {
	private boolean closed = false;
	private Question question = generateQuestion();
	
	public boolean isClosed() {
		return this.closed;
	}
	
	public void open() {
		this.closed = false;
	}
	public void close() {
		this.closed = true;
	}
	
	private Question generateQuestion() {
		return question;
	}
	
	public Question getQuestion() {
		return this.question;
	}
	
	public boolean answerQuestion() {
		Scanner sc = new Scanner(System.in);
		this.question.PromptQuestion();
		String answer = sc.next();
		sc.close();
		return this.question.CheckAnswer(answer);
	}
}