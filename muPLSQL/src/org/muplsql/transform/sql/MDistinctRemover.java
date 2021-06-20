package org.muplsql.transform.sql;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class MDistinctRemover extends MITransformer {

	public MDistinctRemover() {
		this.mtype = MutaOperatorType.Mot14.name();//FIXME
		this.mopId =14;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.equalsIgnoreCase("distinct"))
			result.add("" );

		return result;
	}

	public static void main(String[] a) {

	}

}
