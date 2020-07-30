package org.muplsql.transform.struct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class MCommitRemover extends MITransformer {

	public MCommitRemover() {
		this.mtype = MutaOperatorType.Mot006.name();//FIXME
		this.mopId = 6;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.equalsIgnoreCase("commit"))
			result.add("--commit"  );

		return result;
	}

	public static void main(String[] a) {

	}

}
