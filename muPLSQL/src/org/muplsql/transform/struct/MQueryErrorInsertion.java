package org.muplsql.transform.struct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class MQueryErrorInsertion extends MITransformer {

	public MQueryErrorInsertion() {
		this.mtype = MutaOperatorType.Mot8.name();//FIXME
		this.mopId =8;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.equalsIgnoreCase("immediate"))
			result.add("immediate ' and '  || " );

		return result;
	}

	public static void main(String[] a) {

	}

}
