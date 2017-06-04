/**
 * Machine
 * compiler
 * BRANCH.java
 */
package compiler;

import java.util.Arrays;
import java.util.List;

/**
 * @class	BRANCH
 * @author 	Chris 
 * @date	Jun 3, 2017
 *
 */
public enum BRANCHUNCONDITIONALLY implements EnumInterface {

	BR,
	BRANCH,
	br,
	branch;

	/* (non-Javadoc)
	 * @see compiler.EnumInterface#getNames()
	 */
	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		List<BRANCHUNCONDITIONALLY> L = Arrays.asList(BRANCHUNCONDITIONALLY.values());
		String Str = L.toString();
		String[] S = Str.substring(1, Str.length() - 1).split(",\\s");
		return S;
		
	}
}
