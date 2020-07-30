package org.muplsql.transform.sql;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class MHavingEditer extends MITransformer {

	public MHavingEditer() {
		this.mtype = MutaOperatorType.Mot036.name();//FIXME
		this.mopId =35;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.equalsIgnoreCase("HAVING"))
			result.add("" );

		return result;
	}

	public static void main(String[] a) {

	}

}
