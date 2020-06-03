package Maze;


import java.io.Serializable;

// The RoomItemHint gives the user a hint on the question they are trying to answer
public class RoomItemHint implements RoomItem, Serializable{
	int numOfHints;
	
	public RoomItemHint()
	{
		this.numOfHints = 0;
	}
	

	public RoomItemHint(int numOfHints)
	{
		this.numOfHints = numOfHints;
	}
	
	public void addItem()
	{
		this.numOfHints++;
	}
	
	public void useItem()
	{
		this.numOfHints--;
	}
	
	public String toString()
	{
		if (this.numOfHints == 1)
			return ("This room had " + this.numOfHints + " hint\n");
		else
			return ("This room had " + this.numOfHints + " hints\n");
	}
	
	public String promptUse()
	{
		return ("Would You like to use a hint?\n");
	}

	@Override
	public int usesLeft() {
		return numOfHints;
	}


	public void giveHint(Question question) {
		useItem();
		System.out.println(question.GetHint());
	}
}
