//written by: Isaiah Weaver, Griffin Foster

package Maze;

import java.io.Serializable;

// The RoomItemKey allows the user to unlock a door that has already been locked
public class RoomItemKey implements RoomItem, Serializable
{
	int numOfKeys;
	
	public RoomItemKey()
	{
		this.numOfKeys = 0;
	}
	public RoomItemKey(int numOfKeys)
	{
		this.numOfKeys = numOfKeys;
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
		if(!door.isBorder()) {
			door.open();
			useItem();
		}else
			System.out.println("Cannot open Border");
	}
	
	//this method could be called when the user tries to go through a locked door
	public String toString()
	{
		if (this.numOfKeys == 1)
			return ("This room had " + this.numOfKeys + " key\n");
		else
			return ("This room had " + this.numOfKeys + " keys\n");
	}
	
	public String promptUse()
	{
		return ("Would You like to use a key to unlock the door?\n");
	}
	@Override
	public int usesLeft() 
	{
		return numOfKeys;
	}
}
