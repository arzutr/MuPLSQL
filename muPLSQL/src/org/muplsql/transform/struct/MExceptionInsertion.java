package org.muplsql.transform.struct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class MExceptionInsertion  extends MITransformer {

	public MExceptionInsertion() {
		this.mtype = MutaOperatorType.Mot003.name();//FIXME
		this.mopId = 3;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();
		//NoteL once digeri icin calistirildi
	
		if (value.equalsIgnoreCase("OTHERS"))
			result.add("TOO_MANY_ROWS"  );
		if (value.equalsIgnoreCase("TOO_MANY_ROWS"))
			result.add("OTHERS"  );
		
		if (value.equalsIgnoreCase("NO_DATA_FOUND"))
			result.add("OTHERS"  );
		
		if (value.equalsIgnoreCase("OTHERS"))
			result.add("NO_DATA_FOUND"  );

		return result;
	}

	public static void main(String[] a) {

	}	
	
}
