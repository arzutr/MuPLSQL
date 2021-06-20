package org.muplsql.transform.struct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class MNumberConstantReplacer extends MITransformer {

	public MNumberConstantReplacer() {
		this.mtype = MutaOperatorType.Mot10.name();//FIXME
		this.mopId = 10;
	}

	@Override
	public List<String> mutateAsList(String value, String ref) {
		List<String> result = new ArrayList<>();

		if (ref.equals("Int"))
			result.add("100000000000000000000");
		if (ref.equals("Long"))
			result.add("-100000000000000000000");

		return result;
	}

	public static void main(String[] a) {

	}
	
	@Override
	public boolean isRef() {
		return true;
	}

}
