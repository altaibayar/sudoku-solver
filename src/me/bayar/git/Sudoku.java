package me.bayar.git;

import java.util.Arrays;
import java.util.HashSet;

public class Sudoku 
{
	public static final int N = 9;

	private Cell[][] values;
	
	public Sudoku()
	{
		initializeArray();
	}

	private void initializeArray()
	{
		values = new Cell[N][];
		for(int i = 0; i < N; i++)
		{
			values[i] = new Cell[N];
			for(int j = 0; j < N; j++)		
			{
				values[i][j] = new Cell(i, j);
			}
		}
	}

	public Cell get(int i, int j) 
	{ 
		return values[i][j];
	}
		
	public boolean set(int i, int j, char ch)
	{
		/*self*/
		HashSet<Character> contents = get(i, j).contents;
		if(contents.contains(ch))
		{
			contents.clear();
			contents.add(ch);
		}
		else { return false; }
		
		/*3x3*/
		for (int _i = minSubSquare(i); _i < maxSubSquare(i); _i++)
		{
			for(int _j = minSubSquare(j); _j < maxSubSquare(j); _j++)
			{
				if(_i == i && _j == j) continue;
				
				contents = get(_i, _j).contents;
				if(contents.contains(ch))
					contents.remove(ch);

				if(contents.size() == 0)
					return false;
			}
		}
		
		/*vertical*/
		for (int _j = 0; _j < N; _j ++)
		{
			if(_j == j) continue;
			
			contents = get(i, _j).contents;
			if(contents.contains(ch))
				contents.remove(ch);
			
			if(contents.size() == 0)
				return false;
		}
		
		/*horizontal*/
		for (int _i = 0; _i < N; _i ++)
		{
			if(_i == i) continue;
			
			contents = get(_i, j).contents;
			if(contents.contains(ch))
				contents.remove(ch);
			
			if(contents.size() == 0)
				return false;
		}
		
		return true;
	}
	
	public boolean isValid()
	{
		/* 3x3 */
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				HashSet<Character> unique = new HashSet<Character>();
				for (int _i = (i * 3); _i < (i * 3) + 3; _i ++)
				{
					for (int _j = (j * 3); _j < (j * 3) + 3; _j++)
					{
						unique.addAll(get(_i, _j).contents);						
					}
				}

				if(unique.size() < N) return false;
			}
		}
		
		/* vertical */
		for (int i = 0; i < N; i++)
		{
			HashSet<Character> unique = new HashSet<Character>();
			for(int j = 0; j < N; j++)
				unique.addAll(get(i, j).contents);
			
			if(unique.size() < N) return false;
		}
		
		/* horizontal */
		for (int j = 0; j < N; j++)
		{
			HashSet<Character> unique = new HashSet<Character>();
			for(int i = 0; i < N; i++)
				unique.addAll(get(i, j).contents);
			
			if(unique.size() < N) return false;
		}
		
		return true;
	}
	
	/* helper */
	int minSubSquare(int i)
	{
		return 3 * (i / 3);
	}
	
	int maxSubSquare(int i)
	{
		return (3 * (i / 3)) + 3;
	}
	
	/* overrides */
	@Override
	protected Object clone()
	{
		Sudoku result = new Sudoku();
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				HashSet<Character> resultContents = result.get(i, j).contents;
				resultContents.clear();
				for(Character ch : values[i][j].contents)
					resultContents.add(ch);
			}
		}
		
		return result;
	}
	
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				sb.append(get(i, j));
				if(j < N - 1)
					sb.append(',');
			}
			
			sb.append(System.lineSeparator());
		}
		
		return sb.toString();
	}

	public class Cell
	{
		private HashSet<Character> contents;
		private int i, j;
		
		/* ctor */
		public Cell (int i, int j)
		{
			this.i = i;
			this.j = j;			
			this.contents = new HashSet<Character>(Arrays.asList(new Character[] { '1', '2', '3', '4', '5', '6', '7', '8', '9' }));;
		}
		
		/* getter/setter */
		public int getI() { return i; }
		public int getJ() { return j; }
		public HashSet<Character> getContents() { return contents; }
		
		/*  */
		public int getPossibilityCount() { return contents.size(); }
		
		/* debug */
		@Override
		public String toString() 
		{
			return contents.size() > 1 ? contents.toString() : contents.iterator().next().toString();
		}		
	}
}
