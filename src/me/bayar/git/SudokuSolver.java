package me.bayar.git;

import java.util.HashSet;

public class SudokuSolver 
{
	public Sudoku solve(Sudoku current) 
	{
		int minCount = 10;
		Sudoku.Cell minCell = null;

		/* get cell with minimum size */
		for (int i = 0; i < Sudoku.N; i++) 
		{
			for (int j = 0; j < Sudoku.N; j++) 
			{
				int possibilityCount = current.get(i, j).getPossibilityCount();
				if (possibilityCount > 1 && possibilityCount < minCount) 
				{
					minCell = current.get(i, j);
					minCount = possibilityCount;
				}
			}
		}

		if (minCell != null) 
		{
			HashSet<Character> contents = minCell.getContents();
			for (Character ch : contents) 
			{
				Sudoku newSudoku = (Sudoku) current.clone();

				if (newSudoku.set(minCell.getI(), minCell.getJ(), ch))
				{
					Sudoku result = null;
					if ((result = solve(newSudoku)) != null)
						return result;
				}
			}
		} 
		else if(current.isValid())
		{
			return current;
		}
		
		return null;
	}	
}
