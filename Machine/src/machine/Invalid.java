/**
 * Machine
 * machine
 * Invalid.java
 */
package machine;

/**
 * @class	Invalid
 * @author 	Chris 
 * @date	May 21, 2017
 *
 */
public class Invalid extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1448705789772264844L;
	private String message;
	/**
	 * 
	 */
	public Invalid() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public Invalid(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public Invalid(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public Invalid(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		this.message = new String(arg0);
	}

	/**
	 * @param arg0
	 */
	public Invalid(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
