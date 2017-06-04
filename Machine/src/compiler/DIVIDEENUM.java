/**
 * Machine
 * compiler
 * DIVIDE.java
 */
package compiler;

import java.util.Arrays;
import java.util.List;

/**
 * @class	DIVIDE
 * @author 	Chris 
 * @date	Jun 3, 2017
 *
 */
public enum DIVIDEENUM implements EnumInterface {

	DIV,
	DIVIDE,
	divide,
	div;

	/* (non-Javadoc)
	 * @see compiler.EnumInterface#getNames()
	 */
	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		List<DIVIDEENUM> L = Arrays.asList(DIVIDEENUM.values());
		String Str = L.toString();
		String[] S = Str.substring(1, Str.length() - 1).split(",\\s");
		return S;
	}
}
