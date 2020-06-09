//Written By: Isaiah Weaver
package Maze;

public interface RoomItem
{
	public void useItem();
	public String toString();
	public void addItem();
	public String promptUse();
	public int usesLeft();
}
