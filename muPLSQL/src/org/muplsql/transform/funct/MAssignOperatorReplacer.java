package org.muplsql.transform.funct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class MAssignOperatorReplacer extends MITransformer {

	public MAssignOperatorReplacer() {
		this.mtype = MutaOperatorType.Mot15.name();
		this.mopId = 15;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.equals(":=")){
			result.add(value.replace(":=", ":= null;--"));
		}
		
		return result;
	}

	public static void main(String[] a) {

	}

}
