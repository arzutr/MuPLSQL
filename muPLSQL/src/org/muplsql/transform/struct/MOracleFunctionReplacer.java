package org.muplsql.transform.struct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;
/****
 * 
 * @author arzubt
 *
 *
 * 	Convertable similiar function have similar specification, they are exchanged with each other.
 * 
 * The list of functions can be extendable.  Common used functions were implemented.
 * 
 */
public class MOracleFunctionReplacer extends MITransformer {

	public MOracleFunctionReplacer() {
		this.mtype = MutaOperatorType.Mot011.name();//FIXME
		this.mopId = 11;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();
		//FIXME read from db  according to function spec could be changed
		if (value.equalsIgnoreCase("NVL"))
			result.add(  "SUBSTR"  );

		if (value.equalsIgnoreCase("NVL"))
			result.add(  "REPLACE"  );
		
		if (value.equalsIgnoreCase("SUBSTR"))
			result.add(  "REPLACE"  );

		if (value.equalsIgnoreCase("REPLACE"))
			result.add(  "SUBSTR"  );	
		
		if (value.equalsIgnoreCase("DECODE"))
			result.add(  "SUBSTR"  );		
		
		if (value.equalsIgnoreCase("SUBSTR"))
			result.add(  "DECODE"  );		
		return result;
	}

	public static void main(String[] a) {

	}

}
