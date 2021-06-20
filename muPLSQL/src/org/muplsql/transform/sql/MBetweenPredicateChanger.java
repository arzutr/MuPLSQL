package org.muplsql.transform.sql;

import org.muplsql.transform.*;

import java.util.ArrayList;
import java.util.List;

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
