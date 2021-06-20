package org.muplsql.transform.sql;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

// not any mutant is generated maybe where condition could be active in future.

public class MLikeHelperChanger extends MITransformer {

	public MLikeHelperChanger() {
		this.mtype = MutaOperatorType.Mot25.name();//FIXME
		this.mopId =25;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.equalsIgnoreCase("%"))
			result.add("" );

		return result;
	}

	public static void main(String[] a) {

	}

}
