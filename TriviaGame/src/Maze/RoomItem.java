package Maze;

/*
 * To implement this...
 * The Room class should have a boolean that determines if the Room contains an item or not
 * The Room class should have a RoomItem object
 */
public interface RoomItem
{
	public void useItem();
	public String toString();
	public void addItem();
	public String promptUse();
	public int usesLeft();
}
