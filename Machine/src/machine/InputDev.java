/**
 * Machine
 * machine
 * InputDev.java
 */
package machine;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @class	InputDev
 * @author 	Chris 
 * @date	May 20, 2017
 *
 */
public class InputDev {

	private Scanner in;

	/**
	 * @param in
	 */
	public InputDev() {
		
		
	}

	/**
	 * @return
	 */
	public short input() {
		// TODO Auto-generated method stub
		short i;
		in = new Scanner( System.in );
		try
		{
			for (;;) // ever
			{
				System.out.print("Please enter a value in the range of -9999 to +9999: ");
				i = in.nextShort();
				if ( i <= +9999 && i >= -9999 )
				{
					break;
				}
				else 
				{
					System.out.print("The value: \"" + "\" is not in the range of -9999 to +9999!");
				}
			}
		}
		catch ( InputMismatchException IME  )
		{
			i = this.input();
		}
		catch ( NoSuchElementException NSEE )
		{
			i = this.input();
		}
		return i;
	}
	
	
	
	
}
