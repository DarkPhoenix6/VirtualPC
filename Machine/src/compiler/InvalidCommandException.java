/**
 * Machine
 * compiler
 * InvalidCommandException.java
 */
package compiler;

/**
 * @class	InvalidCommandException
 * @author 	Chris 
 * @date	Jun 17, 2017
 *
 */
public class InvalidCommandException extends Invalid {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3129873349852530438L;

	/**
	 * 
	 */
	public InvalidCommandException() {
		this( " Invalid Command!" );
		
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public InvalidCommandException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public InvalidCommandException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public InvalidCommandException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public InvalidCommandException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
