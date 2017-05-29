/**
 * Machine
 * machine
 * BadIndex.java
 */
package machine;

/**
 * @class	BadIndex
 * @author 	Chris Fedun
 * @date	May 28, 2017
 *
 * @category Invalid
 * @Purpose Thrown when a Invalid index is given
 */
public class BadIndex extends Invalid {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9031190908154429417L;

	/**
	 * 
	 */
	public BadIndex() {
		// TODO Auto-generated constructor stub
		super(new BadIndex("Bad Index Error:"
				+ " Out of Bounds"));
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public BadIndex(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public BadIndex(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public BadIndex(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public BadIndex(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
