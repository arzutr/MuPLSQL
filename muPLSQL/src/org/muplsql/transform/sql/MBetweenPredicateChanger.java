package org.muplsql_full.transform.sql;

import java.util.ArrayList;
import java.util.List;

import org.muplsql_full.transform.MITransformer;
import org.muplsql_full.transform.MutaOperatorType;

public class MBetweenPredicateChanger extends MITransformer {

	public MBetweenPredicateChanger() {
		this.mtype = MutaOperatorType.Mot44.name();//BTW
		this.mopId =44;
	}


	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.equalsIgnoreCase("BETWEEN"))
			result.add("NOT BETWEEN" );

		return result;
	}

	public static void main(String[] a) {

	}

}
