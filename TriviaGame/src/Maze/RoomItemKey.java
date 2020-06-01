package Maze;

// The RoomItemKey allows the user to unlock a door that has already been locked
public class RoomItemKey implements RoomItem
{
	int numOfKeys;
	
	public RoomItemKey()
	{
		this.numOfKeys = 0;
	}
	
	public void addItem()
	{
		this.numOfKeys++;
	}
	
	public void useItem()
	{
		this.numOfKeys--;
	}
	
	public void unlockDoorWithKey(Door door)
	{
		door.open();
		useItem();
	}
	
	//this method could be called when the user tries to go through a locked door
	public String toString()
	{
		if (this.numOfKeys == 1)
			return ("You have " + this.numOfKeys + " key\n");
		else
			return ("You have " + this.numOfKeys + " keys\n");
	}
	
	public String promptUse()
	{
		return ("Would You like to use a key to unlock the door?\n");
	}
}
