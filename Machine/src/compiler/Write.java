/**
 * Machine
 * compiler
 * Write.java
 */
package compiler;

import java.io.IOException;

/**
 * @class	Write
 * @author 	Chris Fedun
 * @date	Jun 3, 2017
 *
 */
public interface Write extends Read {

	final String newline = System.getProperty("line.separator");
	public void writeFile( String[] Str );
	
	public default void openFile(  )
	{
		openFile("InstructionsWritten.txt");
	}
	
	public void writeBinaryFile( short[] executable) throws IOException;

}
