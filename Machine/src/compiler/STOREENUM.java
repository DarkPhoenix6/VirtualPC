/**
 * Machine
 * compiler
 * STORE.java
 */
package compiler;

import java.util.Arrays;
import java.util.List;

/**
 * @class	STORE
 * @author 	Chris 
 * @date	Jun 3, 2017
 *
 */
public enum STOREENUM implements EnumInterface {
	STORE,
	store,
	STOR;

	/* (non-Javadoc)
	 * @see compiler.EnumInterface#getNames()
	 */
	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		List<STOREENUM> L = Arrays.asList(STOREENUM.values());
		String Str = L.toString();
		String[] S = Str.substring(1, Str.length() - 1).split(",\\s");
		return S;
	}
}
