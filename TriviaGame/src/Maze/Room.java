package Maze;


public class Room {
		private Door north, south, east, west;
		private boolean exit = false, entrance = false;
			
	public void initializeRoom() {
			//North
			this.setNorth(new Door());
			
			//South
			this.setSouth(new Door());
			
			//East
			this.setEast(new Door());
			
			//West
			this.setWest(new Door());
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

		public String toString() {
			String roomToString;

			//North
			if(this.north.isClosed())
				roomToString = "***\n";
			else
				roomToString = "*-*\n";

			//West
			if(this.west.isClosed())
				roomToString += "*";
			else
				roomToString +="|";

			//Middle
			roomToString += " ";
			
			//East
			if(this.east.isClosed())
				roomToString += "*\n";
			else
				roomToString +="|\n";

			//South
			if(this.south.isClosed())
				roomToString += "***\n";
			else
				roomToString += "*-*\n";

			return roomToString;
		}
}
