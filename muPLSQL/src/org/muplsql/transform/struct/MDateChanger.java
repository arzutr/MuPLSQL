package org.muplsql_full.transform.struct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql_full.transform.MITransformer;
import org.muplsql_full.transform.MutaOperatorType;

public class MDateChanger extends MITransformer {

	public MDateChanger() {
		this.mtype = MutaOperatorType.Mot53.name();//FIXME
		this.mopId = 53;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.contains("date"))
			result.add(" sysdate as "  +value );

		return result;
	}

	public static void main(String[] a) {

	}

}
