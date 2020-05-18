package Maze;


public class Room {
		private Door north, south, east, west;
		private boolean exit = false, entrance = false, isVisited = false;	
		
	public void initializeRoom() {
			//North
			this.setNorth(new Door());
			this.north.setQuestion(new Question(null, null));
			
			//South
			this.setSouth(new Door());
			this.south.setQuestion(new Question(null, null));
			
			//East
			this.setEast(new Door());
			this.east.setQuestion(new Question(null, null));
			
			//West
			this.setWest(new Door());
			this.west.setQuestion(new Question(null, null));
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
			else if(this.isVisited)
				return "V";
			else
				return " ";	
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
			roomToString += roomStatus();
			
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
