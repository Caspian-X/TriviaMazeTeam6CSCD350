package Maze;

import java.io.Serializable;
import java.util.Random;

public class Room implements Serializable{
		private Door north, south, east, west;
		private boolean exit = false, entrance = false, isVisited = false;	
		private RoomItem generatedItem = null;
		
	public Room() {
		//North
		this.setNorth(new Door());
		
		//South
		this.setSouth(new Door());
		
		//East
		this.setEast(new Door());
		
		//West
		this.setWest(new Door());
		
		//See if we generate an item (0-9hint,9-19key) 10% chance each
		Random r = new Random();
		int rand = r.nextInt(100);
		if(rand < 10){
			generatedItem = new RoomItemHint(1);
		} else if(rand < 20) {
			generatedItem = new RoomItemKey(1);
		}
	}
	
	public Door getNorth() {
		return this.north;
	}
	
	public void setNorth(Door door) {
		this.north = door;
	}
	
	public Door getSouth() {
		return this.south;
	}
	
	public void setSouth(Door door) {
		this.south = door;
	}
	
	public Door getEast() {
		return this.east;
	}
	
	public void setEast(Door door) {
		this.east = door;
	}
	
	public Door getWest() {
		return this.west;
	}
	
	public void setWest(Door door) {
		this.west = door;
	}
	
	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isEntrance() {
		return entrance;
	}

	public void setEntrance(boolean entrance) {
		this.entrance = entrance;
	}
	
	public boolean isVisited() {
		return this.isVisited;
	}
	
	public void setVisited(boolean visited) {
		this.isVisited = visited;
	}
	
	public String roomStatus() {
		if(this.entrance)
			return "I";
		else if(this.exit)
			return "E";
		else
			return " ";	
	}
	public String toString() {
		String roomToString;
			//North
		if(this.north.isClosed())
			roomToString = "*+*\n";
		else if(this.north.isBorder())
			roomToString = "***\n";
		else
			roomToString = "*-*\n";
			//West
		if(this.west.isClosed())
			roomToString += "+";
		else if(this.west.isBorder())
			roomToString += "*";
		else
			roomToString +="|";
		
		//Middle
		roomToString += roomStatus();
		
		//East
		if(this.east.isClosed())
			roomToString += "+\n";
		else if(this.east.isBorder())
			roomToString += "*\n";
		else
			roomToString +="|\n";
			//South
		if(this.south.isClosed())
			roomToString += "*+*\n";
		else if(this.south.isBorder())
			roomToString += "***\n";
		else
			roomToString += "*-*\n";
			return roomToString;
	}
	public RoomItem getRoomItem()
	{
		return generatedItem;
	}
}
