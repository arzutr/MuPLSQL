package org.muplsql.transform.funct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.mcase.Case;
import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;
/**
 * 
 * @author arzubt
 *
 */
public class MReturnAdder extends MITransformer {

	public MReturnAdder() {
		this.mtype = MutaOperatorType.Mot39.name();
		this.mopId = 39;
		oCcase = new  Case();
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();


		if (value.equals("return") &&  oCcase.whereStarted > 0 ) {
			result.add(value.replace("return", " return 1  +  "));
			result.add(value.replace("return", " return 'nulll' || "));
		}
		
		if ( oCcase.whereStarted > 0 &&  value.equalsIgnoreCase("FUNCTION")) {
			oCcase.whereStarted = 0;
		}

		if ( oCcase.whereStarted == 0 && value.equalsIgnoreCase("FUNCTION")) {
			oCcase.whereStarted++;
		}


		return result;
	}


}
