package me.bayar.git;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainClass 
{
	public static void main(String[] args) throws Exception
	{	
		final String inputPath = "input.csv";

		Sudoku sudoku = readFile(inputPath);
		SudokuSolver solver = new SudokuSolver();
		
		if((sudoku = solver.solve(sudoku)) != null)
		{
			System.out.println("Sudoku result:");
			System.out.println(sudoku.toString());
		}
		else
		{
			System.out.println("Sudoku doesn't have resolution");
		}
	}
	
	public static Sudoku readFile(String filePath) throws Exception
	{
		Sudoku result = new Sudoku();

		File file = new File(filePath);
		if(!file.exists())
			throw new Exception("Sudoku input file doesn't exists.");
			
		try(FileReader fr = new FileReader(file))
		{
			try(BufferedReader br = new BufferedReader(fr)) 
			{
				for(int i = 0; i < Sudoku.N; i ++)
				{
					String line = br.readLine();
					if(line == null)
						throw new Exception("File doesn't contain enough amount of lines.");
					
					String[] splt = line.split(",");
					if(splt.length != Sudoku.N)
						throw new Exception("Line is corrupted.");
					
					for (int j = 0; j < Sudoku.N; j++)
					{
						result.set(i, j, splt[j].charAt(0));
					}
				}
				
				br.close();
			}
			
			fr.close();
		}
		
		return result;
	}
}
