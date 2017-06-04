/**
 * Machine
 * compiler
 * ADD.java
 */
package compiler;

import java.util.Arrays;
import java.util.List;

/**
 * @class	ADD
 * @author 	Chris Fedun
 * @date	Jun 3, 2017
 *
 */
public enum ADDITION implements EnumInterface{
	ADD,
	add;

	/* (non-Javadoc)
	 * @see compiler.EnumInterface#getNames()
	 */
	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		List<ADDITION> L = Arrays.asList(ADDITION.values());
		String Str = L.toString();
		String[] S = Str.substring(1, Str.length() - 1).split(",\\s");
		return S;
	}

}
