package me.bayar.git;

public class Sudoku 
{
	public static final int N = 9;
	
	private byte[][] values;
	
	public Sudoku()
	{
		initializeArray();
	}

	public byte get(int i, int j)
	{
		return values[i][j];
	}

	public void set(int i, int j, byte value)
	{
		values[i][j] = value;
	}
	
	private void initializeArray()
	{
		values = new byte[N][N];
		
		for(int i = 0; i < N; i++)
			values[i] = new byte[N];		
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
