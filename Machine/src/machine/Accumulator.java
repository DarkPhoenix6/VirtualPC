/**
 * Machine
 * machine
 * Accumulator.java
 */
package machine;

/**
 * @class	Accumulator
 * @author 	Chris 
 * @date	May 19, 2017
 *
 */
public class Accumulator extends MemElem {

	
	/**
	 * 
	 */
	public Accumulator() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @param elem
	 */
	public Accumulator(int elem) {
		super(elem);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param elem
	 */
	public Accumulator(short elem) {
		super(elem);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param read
	 * @throws MathError 
	 */
	public void add(short read) throws MathError {
		// TODO Auto-generated method stub
		if ( (super.read() + read) < -9999 || (super.read() + read) > 9999 )
		{
			MathError AdditionError = new MathError("The Difference of the numbers are: Out of Bounds");
			throw AdditionError;
		}
		else
		{
			super.write((short) (super.read() + read));
		}
	}

	/**
	 * @param val
	 * @throws Invalid 
	 */
	public void subtract(short val) throws Invalid {
		// TODO Auto-generated method stub
		if ( (super.read() - val) < -9999 || (super.read() - val) > 9999 )
		{
			Invalid SubtractionError = new Invalid("The Difference of the numbers are: Out of Bounds");
			throw SubtractionError;
		}
		else
		{
			super.write((short) (super.read() - val));
		}
	}

	/**
	 * 
	 * @param val
	 * @throws Invalid
	 */
	public void multiply(short val) throws Invalid{
		// TODO Auto-generated method stub
		if ( (super.read() * val) < -9999 || (super.read() * val) > 9999 )
		{
			Invalid MultiplicationError = new Invalid("The Product of the numbers are: Out of Bounds");
			throw MultiplicationError;
		}
		else
		{
			super.write((short) (super.read() * val));
		}
	}

	/**
	 * 
	 * @param val
	 * @throws Invalid 
	 * @throws ArithmeticException
	 */
	public void divide(short val) throws Invalid {
		// TODO Auto-generated method stub
		try
		{
			if ( (super.read() / val) < -9999 || (super.read() / val) > 9999 )
			{
				Invalid DivisionError = new Invalid("The Quotient of the numbers are: Out of Bounds");
				throw DivisionError;
			}
			else
			{
				super.write((short) (super.read() / val));
			}
		}
		catch ( ArithmeticException AE )
		{
			throw AE;
		}
	}

	/**
	 * @return
	 */
	public boolean isZero() {
		// TODO Auto-generated method stub
		if  ( super.read() == (short) 0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * @return
	 */
	public boolean isGreaterThanZero() {
		// TODO Auto-generated method stub
		if  ( super.read() > (short) 0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	

}
