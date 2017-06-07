/**
 * Machine
 * compiler
 * ReadFile.java
 */
package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @class	ReadFile
 * @author 	Chris 
 * @date	Jun 3, 2017
 *
 */
public class ReadFile implements Read {

	private Scanner input;
	
	/**
	 *  (non-Javadoc)
	 * @see compiler.Read#openFile(java.lang.String)
	 */
	@Override
	public void openFile(String file) {
		// TODO Auto-generated method stub
		try 
		{
			input = new Scanner( new File(file) );
			
		}
		catch ( FileNotFoundException f)
		{
			System.err.print("File Not Found!");
			System.exit(1);
		}
	}

	public String[] getInstructions()
	{
		String Str = null;

		try
		{
			while ( input.hasNextLine() )
			{
				if ( Str == null)
				{
					Str = new String( input.nextLine() + " \n" );
				}
				else
				{
					Str += input.nextLine();
					Str += " \n";
				}
			}
			
			
		}
		catch ( NoSuchElementException NSEE )
		{
			System.err.print("File Not Readable!");
			input.close();
			System.exit(1);
		}
		catch ( IllegalStateException ISE )
		{
			System.err.print("Error Reading The File!");
			System.exit(1);
		}
		//System.out.println(Str);
		String[] instructions = removeComments(Str);
		/*for ( String S: instructions)
		{
			System.out.println(S);
		}*/
		return instructions;
	}

	/**
	 * @param Str
	 * @param i
	 * @param j
	 * @return An Array of the Parameter String without the comments
	 */
	private String[] removeComments(String Str) 
	{
		String[] instructions = Str.split("\\s\n");
		int i = 0;
		int j = 0;
		int[] arr = new int[instructions.length];
		initArray( arr );
		for ( String s: instructions )
		{
			// Mark Locations for Removal
			if ( s.regionMatches(0, "REM ", 0, 4) )
			{
				arr[j] = i;
				j++;
				i++;
			}
			else
			{
				i++;
			}
			
		}
		String[] newStr = new String[instructions.length - j];
		i = 0;
		if ( j != 0 )
		{
			for ( int k = 0; k < instructions.length; k++ )
			{
				if ( k != arr[i] )
				{
					newStr[k - i] = instructions[k];
				}
				else 
				{
					i++;
				}
			}
			return newStr;
		}
		else
		{
			return instructions;
		}
		
	}
	
	/**
	 * @param arr The Array to Initialize to Zero
	 */
	private void initArray(int[] arr) 
	{
		// TODO Auto-generated method stub
		for ( int i = 0; i < arr.length; i++ )
		{
			arr[i] = 0;
		}
		
		return;
	}

	/**
	 *  (non-Javadoc)
	 * @see compiler.Read#closeFile()
	 */
	@Override
	public void closeFile() {
		// TODO Auto-generated method stub

		if ( input != null )
		{
			input.reset();
			input.close();
		}
	}

	/* (non-Javadoc)
	 * @see compiler.Read#openFile()
	 */
	@Override
	public void openFile() {
		// TODO Auto-generated method stub
		openFile("ToBeCompiled.txt");
	}

}
