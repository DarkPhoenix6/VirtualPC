/**
 * Machine
 * compiler
 * Read.java
 */
package compiler;

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
	 * @Purpose To Close the file after Reading/Writing
	 */
	public void closeFile();
	
	public default String[] splitString(String Str, String Expression)
	{
		return Str.split(Expression);
	}
}
