package org.muplsql.transform.funct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class MGotoReplacer extends MITransformer {

	public MGotoReplacer() {
		this.mtype = MutaOperatorType.Mot17.name();
		this.mopId = 17;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();
		if (value.equalsIgnoreCase("GOTO"))
			result.add(value.replace("goto", "-- goto"));

		return result;
	}

	public static void main(String[] a) {

	}

}
