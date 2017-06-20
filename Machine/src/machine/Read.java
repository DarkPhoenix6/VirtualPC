/**
 * Machine
 * compiler
 * Read.java
 */
package machine;

import java.io.IOException;

/**
 * @class	Read
 * @author 	Chris Fedun
 * @date	Jun 3, 2017
 *
 */
public interface Read {

	/**
	 * @Purpose Open the file for Reading/Writing
	 * @param file
	 */
	public void openFile( String file );
	public void openFile( );
	/**
	 * @throws IOException 
	 * @Purpose To Close the file after Reading/Writing
	 */
	public void closeFile() throws IOException;
	
	public default String[] splitString(String Str, String Expression)
	{
		return Str.split(Expression);
	}
	
	public void openBinaryFile();
	public void openBinaryFile( String file );
}
