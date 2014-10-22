package me.bayar.git;

import java.util.HashMap;
import java.util.HashSet;

public class Sudoku 
{
	public static final int N = 9;
	
	private HashMap<Integer, HashSet<Character>> values;
	
	public Sudoku()
	{
		initializeArray();
	}

	private void initializeArray()
	{
		final Character[] allowedChars = new Character[] { '1', '2', '3', '4', '5', '6', '7', '8', '9' };		
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				HashSet<Character> cell = new HashSet<Character>();
				for(Character ch : allowedChars)
					cell.add(ch);
				
				values.put(key(i, j), cell);
			}
		}
	}

	private HashSet<Character> get(int i, int j) { return values.get(key(i, j)); }
	
	private int key(int i, int j) { return i * N + j; }
	
	@Override
	protected Object clone()
	{
		Sudoku result = new Sudoku();
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				result.put(key(i, j), get(i, j).clone());
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
}
