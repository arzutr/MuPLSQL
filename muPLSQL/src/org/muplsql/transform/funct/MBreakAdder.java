package org.muplsql.transform.funct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;
/***
 * 
 * For statementlar icin olacak
 * 
 * @author arzubt
 *
 */

public class MBreakAdder extends MITransformer {

	public MBreakAdder() {
		this.mtype = MutaOperatorType.Mot032.name();//FIXME
		this.mopId = 32;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();
		if (value.equalsIgnoreCase("LOOP")){
			result.add(" exit;  LOOP");
		} 

		return result;
	}
	
	@Override
	public boolean hasPreCondition() {
		return false;
	}
	
	@Override
	public String getPreConditionVal() {
		return ")";
	}
	

	public static void main(String[] a) {

	}

}
