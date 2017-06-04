/**
 * Machine
 * compiler
 * OUTPUT.java
 */
package compiler;

import java.util.Arrays;
import java.util.List;

/**
 * @class	OUTPUT
 * @author 	Chris Fedun
 * @date	Jun 3, 2017
 *
 */
public enum OUTPUTENUM implements EnumInterface {

	OUT,
	out,
	OUTPUT;

	/* (non-Javadoc)
	 * @see compiler.EnumInterface#getNames()
	 */
	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		List<OUTPUTENUM> L = Arrays.asList(OUTPUTENUM.values());
		String Str = L.toString();
		String[] S = Str.substring(1, Str.length() - 1).split(",\\s");
		return S;
	}
}
