/**
 * Machine
 * machine
 * MathError.java
 */
package machine;

/**
 * @class	MathError
 * @author 	Chris 
 * @date	May 28, 2017
 *
 */
public class MathError extends Invalid {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2433951913007686502L;

	/**
	 * 
	 */
	public MathError() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public MathError(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public MathError(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public MathError(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public MathError(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
