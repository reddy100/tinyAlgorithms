import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;


public class Prince
{
	public enum Direction
	{
		EAST, WEST, SOUTH, NORTH, UP, DOWN 
	}

	static char grid[][][] =
		{
		{
			{ '1', '.', '.' },
			{ 'o', 'o', '.' },
			{ '.', '.', '.' }
		},
		{
			{ 'o', 'o', 'o' },
			{ '.', '.', 'o' },
			{ '.', 'o', 'o' }
		},
		{
			{ 'o', 'o', 'o' },
			{ 'o', '.', '.' },
			{ 'o', '.', '2' }
		}
		};

	//iterates through grid and finds the coordinates of the prince and princess
	public static int[] findStart(char[][][] grid)
	{
		int[] start=new int[6];
		for(int i=0;i<grid.length;i++)
		{
			for(int j=0;j<grid[0].length;j++)
			{
				for(int k=0;k<grid[0][0].length;k++)
				{
					if(grid[i][j][k]=='1')
					{
						start[0]=i;
						start[1]=j;
						start[2]=k;

					}
					else if(grid[i][j][k]=='2')
					{
						start[3]=i;
						start[4]=j;
						start[5]=k;
					}

				}
			}
		}
		return start;
	}

	//global variables regarding the grid
	static int maxX=grid[0][0].length;
	static int maxY=grid[0].length;
	static int maxZ=grid.length;

	static int[]start=findStart(grid);
	static int begX=start[2];
	static int begY=start[1];
	static int begZ=start[0];
	static int endX=start[5];
	static int endY=start[4];
	static int endZ=start[3];
	
	static Direction east=Direction.EAST;
	static Direction west=Direction.WEST;
	static Direction north=Direction.NORTH;
	static Direction south=Direction.SOUTH;
	static Direction up=Direction.UP;
	static Direction down=Direction.DOWN;
	
	
	//takes coordinates and returns true if it is a movable position else returns false
	public static boolean isSafe(int x, int y, int z)
	{
		
		return (x>=0 && x<maxX && y>=0 && y<maxY && z>=0 && z<maxZ && (grid[z][y][x]=='.' || grid[z][y][x]=='1'));
	}
	//takes 2 vectors and merges them
	public static Vector<Direction> mergeVector(Vector<Direction> Va, Vector<Direction> Vb) 
	{
		  Vector<Direction> merge = new Vector<Direction>();
		  merge.addAll(Va);
		  merge.addAll(Vb);
		  return merge;
	}
	//function takes 6 vectors and returns the shortest vector
	public static Vector<Direction> chooseMin(Vector<Direction> a, Vector<Direction> b, Vector<Direction> c, Vector<Direction> d, Vector<Direction> e, Vector<Direction> f)
	{
		ArrayList<Vector<Direction>> al = new ArrayList<Vector<Direction>>();
		if(a!=null)
			al.add(a);
		if(b!=null)
			al.add(b);
		if(c!=null)
			al.add(c);
		if(d!=null)
			al.add(d);
		if(e!=null)
			al.add(e);
		if(f!=null)
			al.add(f);
		if(a==null && b==null && c==null && d==null && e==null && f==null) 
			return null;
		Vector<Direction> min = al.get(0);
	    for (int i = 1; i < al.size(); i++) 
	    {
	    	if  (al.get(i).size() < min.size()){
	    		min = al.get(i);
	    	}
	    }
	    return min;
	}
	//calls the recursive mazesolver
	public Vector<Direction> savePrincess()
	{
		long startTime = System.nanoTime();
		Vector<Direction> dir = Solve(begX, begY, begZ, null);
		long stopTime = System.nanoTime();
	  
		System.out.println((int)(stopTime - startTime));
		return dir;
	}
	
	//function which recursively solves the maze
	public static Vector<Direction> Solve(int x, int y, int z, Direction d)
	{
		//base case
		if(x==endX && y==endY && z==endZ )
		{
			Vector<Direction> side=new Vector<Direction>(1);
			side.add(d);
			return side;
		}
		if(isSafe(x, y, z))
		{	
			//System.out.println("is safe");
			Vector<Direction> goEast=null;
			Vector<Direction> goWest=null;
			Vector<Direction> goNorth=null;
			Vector<Direction> goSouth=null;
			Vector<Direction> goUp=null;
			Vector<Direction> goDown=null;
			
			//recursively find path to goal for every direction
			if (d!=east)
			{
				goWest=Solve(x - 1, y, z, west);	
				if(goWest!=null)
					goWest.add(d);
			}
			if (d!=west)
			{
				goEast=Solve(x + 1, y, z, east);
				if(goEast!=null)
					goEast.add(d);
			}
			if (d!=south)
			{
				goNorth=Solve(x, y - 1, z, north);
				if(goNorth!=null)
					goNorth.add(d);
			}
			if (d!=north)
			{
				goSouth=Solve(x, y + 1, z, south);
				if(goSouth!=null)
					goSouth.add(d);
			}
			if (d!=down)
			{
				goUp= Solve(x, y, z - 1, up);
				if(goUp!=null)
					goUp.add(d);
			}
			if (d!=up)
			{
				goDown=Solve(x, y, z + 1, down);
				if(goDown!=null)
					goDown.add(d);
			}
			//if price cannot move in every direction return null adn break out
			else
			{
				return null;
			}
			//if not find shortest path 
			return chooseMin(goEast, goWest, goNorth, goSouth, goUp, goDown);	
		}
		//if not a safe move return null and try another move
		else 
		{
			return null;
		}
	}

	
    

	public static void main(String [] args)
	{
		Prince a=new Prince();
		Vector<Direction> fin=a.savePrincess();
		//Collections.reverse(fin);
		//fin.remove(0);
		//Direction[] vec=new Direction [fin.size()];
		//fin.toArray(vec);
		System.out.println(fin);

	}
}

