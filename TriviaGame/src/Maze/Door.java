package Maze;

public class Door {
	private boolean closed = false;
	private Question question;
	
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
	
}