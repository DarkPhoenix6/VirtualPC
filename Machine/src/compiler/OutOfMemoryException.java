/**
 * Machine
 * compiler
 * OutOfMemoryException.java
 */
package compiler;

/**
 * @class	OutOfMemoryException
 * @author 	Chris Fedun
 * @date	Jun 17, 2017
 *
 */
public class OutOfMemoryException extends Invalid {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3544276265387967831L;

	/**
	 * 
	 */
	public OutOfMemoryException() {
		// TODO Auto-generated constructor stub
		this("Out Of Memory!");
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public OutOfMemoryException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public OutOfMemoryException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public OutOfMemoryException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public OutOfMemoryException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
