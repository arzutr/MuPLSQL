package org.muplsql_full.transform.struct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql_full.transform.MITransformer;
import org.muplsql_full.transform.MutaOperatorType;
/****
 * 
 * @author arzubt
 *
 *
 * 	Convertable similiar function have similar specification, they are exchanged with each other.
 * 
 */
public class MOracleKeywordChanger extends MITransformer {

	public MOracleKeywordChanger() {
		this.mtype = MutaOperatorType.Mot45.name();//FIXME
		this.mopId = 45;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.contains("FOUND"))
			result.add(  "NOTFOUND"  );

		if (value.contains("NOTFOUND"))
			result.add(  "FOUND"  );	
		return result;
	}

	public static void main(String[] a) {

	}

}
