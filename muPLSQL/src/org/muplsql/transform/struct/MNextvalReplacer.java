package org.muplsql.transform.struct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

//https://docs.oracle.com/cd/A84870_01/doc/server.816/a76989/ch26.htm#4062

public class MNextvalReplacer extends MITransformer {

	public MNextvalReplacer() {
		this.mtype = MutaOperatorType.Mot009.name();//FIXME
		this.mopId = 9;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.equalsIgnoreCase("nextval"))
			result.add("CURRVAL"  );
		if (value.equalsIgnoreCase("currval"))
			result.add("nextval"  );		

		return result;
	}

	public static void main(String[] a) {

	}

}
