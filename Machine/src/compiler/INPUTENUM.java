/**
 * Machine
 * compiler
 * INPUT.java
 */
package compiler;

import java.util.Arrays;
import java.util.List;

/**
 * @class	INPUT
 * @author 	Chris Fedun
 * @date	Jun 3, 2017
 *
 */
public enum INPUTENUM implements EnumInterface {

	IN,
	INPUT,
	in,
	input;

	/* (non-Javadoc)
	 * @see compiler.EnumInterface#getNames()
	 */
	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		List<INPUTENUM> L = Arrays.asList(INPUTENUM.values());
		String Str = L.toString();
		String[] S = Str.substring(1, Str.length() - 1).split(",\\s");
		return S;
	}
}
