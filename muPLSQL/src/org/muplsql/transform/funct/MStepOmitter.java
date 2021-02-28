package org.muplsql.transform.funct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.statics.OracleReservedWords;
import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class MStepOmitter extends MITransformer {

	public MStepOmitter() {
		this.mtype = null;//MutaOperatorType.Mot19.name();
		this.mopId = -19;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();
		if (!OracleReservedWords.contains(value) && !cantStop(value) )
			result.add("-- " + value);

		return result;
	}

	private boolean cantStop(String value) {
		return value.equals(":=") || value.equals(",") || value.equals(":") || value.equals(";") || value.equals("||") || value.equalsIgnoreCase("NUMBER")
				 || value.equalsIgnoreCase("VARCHAR2") 
				|| value.equals("+");
	}

	public static void main(String[] a) {

	}

}
