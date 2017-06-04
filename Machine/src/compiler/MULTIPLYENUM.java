/**
 * Machine
 * compiler
 * MULTIPLY.java
 */
package compiler;

import java.util.Arrays;
import java.util.List;

/**
 * @class	MULTIPLY
 * @author 	Chris 
 * @date	Jun 3, 2017
 *
 */
public enum MULTIPLYENUM implements EnumInterface {
	MULT,
	MULTI,
	MULTIPLY,
	mult,
	multi,
	multiply;

	/* (non-Javadoc)
	 * @see compiler.EnumInterface#getNames()
	 */
	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		List<MULTIPLYENUM> L = Arrays.asList(MULTIPLYENUM.values());
		String Str = L.toString();
		String[] S = Str.substring(1, Str.length() - 1).split(",\\s");
		return S;
	}

}
