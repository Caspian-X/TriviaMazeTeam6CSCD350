package Maze;

// The RoomItemHint gives the user a hint on the question they are trying to answer
public class RoomItemHint 
{
	int numOfHints;
	
	public RoomItemHint()
	{
		this.numOfHints = 0;
	}
	
	public void addItem()
	{
		this.numOfHints++;
	}
	
	public void useItem()
	{
		this.numOfHints--;
	}
	
	public void giveHint(Question question)
	{
		//take away one wrong answer or something
		//could do something like tell the user that the answer is either A, or B or starts with a certain letter.
		useItem();
	}
	
	public String toString()
	{
		if (this.numOfHints == 1)
			return ("You have " + this.numOfHints + " hint\n");
		else
			return ("You have " + this.numOfHints + " hints\n");
	}
	
	public String promptUse()
	{
		return ("Would You like to use a hint?\n");
	}
}
