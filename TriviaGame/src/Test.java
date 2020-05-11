import Maze.Maze;
import Maze.MazeBuilder;

public class Test 
{
	public static void main(String[] args)
	{
		Maze maze = new Maze();
		maze = MazeBuilder.buildMaze();
		MazeBuilder.printEntireMaze(maze);
	}
}
