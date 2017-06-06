/**
 * Machine
 * compiler
 * WriteFile.java
 */
package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;

/**
 * @class	WriteFile
 * @author 	Chris Fedun
 * @date	Jun 3, 2017
 *
 */
public class WriteFile implements Write {

	private Formatter output;
	
	/* (non-Javadoc)
	 * @see compiler.Read#openFile(java.lang.String)
	 */
	@Override
	public void openFile(String file) {
		// TODO Auto-generated method stub
		try
		{
			output = new Formatter( new File(file));
		}
		catch ( SecurityException sE )
	      {
	         System.err.println("You do not have write access permissions for this file." );
	         System.exit( 1 ); // terminate the program
	      }
		catch ( FileNotFoundException f)
		{
			System.err.print("File Not Found, Unable to Create file, or Problems Opening the File!");
			System.exit(1); // terminate the program
		}
	}

	
	/* (non-Javadoc)
	 * @see compiler.Read#closeFile()
	 */
	@Override
	public void closeFile() {
		// TODO Auto-generated method stub
		if ( output != null )
		{
			output.close();
		}
	}



	/* (non-Javadoc)
	 * @see compiler.Write#writeFile(java.lang.String)
	 */
	@Override
	public void writeFile(String[] Str) {
		// TODO Auto-generated method stub
		try
		{
			int a = 0;
			
			
			while ( a < Str.length )
			{

				if ( a < Str.length )
				{
					
					output.format("%s%s", Str[a++], newline);

				}
				else
				{
					output.format("%s", Str[a++]);
				}
				
			}
		}
		catch ( FormatterClosedException formatterClosedException )
        {
           System.err.println( "Error writing to file." );
           return;
        } // end catch
        catch ( NoSuchElementException elementException )
        {
           System.err.println( "Invalid input. Please try again." );
           //input.nextLine(); // discard input so user can try again
        } // end catch
	}


	/**
	 * @param string
	 */
	public void writeFile(String string) {
		// TODO Auto-generated method stub
		try
		{
			int a = 0;
			String[] splitString = string.split("\n");
			while ( a < splitString.length )
			{
				output.format("%s%s", splitString[a++], newline);
			}
		}
		catch ( FormatterClosedException formatterClosedException )
        {
           System.err.println( "Error writing to file." );
           return;
        } // end catch
        catch ( NoSuchElementException elementException )
        {
           System.err.println( "Invalid input. Please try again." );
           //input.nextLine(); // discard input so user can try again
        } // end catch
	}

}
