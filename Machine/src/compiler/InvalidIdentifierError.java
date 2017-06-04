/**
 * Machine
 * compiler
 * InvalidIdentifierError.java
 */
package compiler;

/**
 * @class	InvalidIdentifierError
 * @author 	Chris 
 * @date	Jun 3, 2017
 *
 */
public class InvalidIdentifierError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6269905006050536728L;

	
	public InvalidIdentifierError( String name )
	{
		super( "The Identifier: " + name + " is INVALID!");
	}
}
