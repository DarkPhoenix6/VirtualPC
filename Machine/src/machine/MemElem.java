/**
 * Machine
 * machine
 * MemElem.java
 */
package machine;

/**
 * @class	MemElem
 * @author 	Chris 
 * @date	May 18, 2017
 *
 */
public class MemElem {

	private short element;
	
	/**
	 * 
	 */
	public MemElem() {
		// TODO Auto-generated constructor stub
		setElement( 0 );
	}
	
	/**
	 * 
	 * @param elem
	 */
	public MemElem( int elem ) {
		// TODO Auto-generated constructor stub
		setElement( elem );
	}
	
	/**
	 * 
	 * @param elem
	 */
	public MemElem( short elem ) {
		// TODO Auto-generated constructor stub
		setElement( elem );
	}
	
	/**
	 * @return the element
	 */
	private short getElement() {
		return element;
	}
	
	/**
	 * @param element the element to set
	 */
	private void setElement(short element) {
		this.element = element;
	}
	
	/**
	 * @param element the element to set
	 */
	private void setElement(int element) {
		this.element = (short) element;
	}

	/**
	 * @param val
	 */
	public void write(short val) {
		// TODO Auto-generated method stub
		setElement(val);
	}

	/**
	 * @return
	 */
	public short read() {
		// TODO Auto-generated method stub
		return getElement();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%+06d", element);
	}

	
}
