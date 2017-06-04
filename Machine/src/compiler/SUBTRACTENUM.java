/**
 * Machine
 * compiler
 * SUBTRACT.java
 */
package compiler;

import java.util.Arrays;
import java.util.List;

/**
 * @class	SUBTRACT
 * @author 	Chris 
 * @date	Jun 3, 2017
 *
 */
public enum SUBTRACTENUM implements EnumInterface {

	SUBTRACT,
	SUB,
	sub,
	subtract;

	/* (non-Javadoc)
	 * @see compiler.EnumInterface#getNames()
	 */
	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		List<SUBTRACTENUM> L = Arrays.asList(SUBTRACTENUM.values());
		String Str = L.toString();
		String[] S = Str.substring(1, Str.length() - 1).split(",\\s");
		return S;
	}
	
	
	
}
