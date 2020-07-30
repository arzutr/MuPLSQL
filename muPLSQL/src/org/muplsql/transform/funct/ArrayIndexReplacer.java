package org.muplsql.transform.funct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class ArrayIndexReplacer extends MITransformer {

	public ArrayIndexReplacer() {
		this.mtype = MutaOperatorType.Mot012.name();
		this.mopId = 12;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();
		if (value.equals("["))
			result.add(value + "1" ); 

		return result;
	}

	public static void main(String[] a) {

	}

}
