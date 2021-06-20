package org.muplsql.transform.struct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class MRollbackChanger extends MITransformer {

	public MRollbackChanger() {
		this.mtype = MutaOperatorType.Mot40.name();//FIXME
		this.mopId = 40;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.equalsIgnoreCase("rollback"))
			result.add("--rollback"  );

		return result;
	}

	public static void main(String[] a) {

	}

}
