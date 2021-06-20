package org.muplsql.transform.struct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

/**
 * add cursor select 1=0 
 * @author arzubt
 *
 */
public class MWhereStatementChanger extends MITransformer {

	public MWhereStatementChanger() {
		this.mtype = null;// MutaOperatorType.Mot43.name();//FIXME
		this.mopId = -1;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.equalsIgnoreCase("where"))
			result.add("where 1=0 and"  );

		return result;
	}

	public static void main(String[] a) {

	}

}
