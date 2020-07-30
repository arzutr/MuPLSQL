package org.muplsql.transform.funct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.mcase.Case;
import org.muplsql.model.MutationConstants;
import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

/***
 * int or long icin
 * 
 * @author ArzuBT
 *
 */
public class MAbsInserter extends MITransformer {

	public MAbsInserter() {
		this.mtype = MutaOperatorType.Mot013.name();
		this.mopId = 13;
		oCcase = new Case();
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();
//System.out.println("preString" + oCcase.preString);
		if (isNumber(value)) {
			double valueTemp = getNumber(value);
			if (valueTemp == 0) {
				result.add("-abs(1)");
			} else if  (valueTemp >0 && hasNotMinus()  ) {
				result.add("-1*abs(" + value + ")");
			} else  {
				result.add("1*-1*abs(" + value + ")");
			}
			
		}

		return result;
	}
	
	private boolean hasNotMinus() {
		return oCcase.preString != null && !oCcase.preString.equals(MutationConstants.MINUS);
	}
	

	@Override
	public  boolean hasPreCondition(){
		return true;
	}
	
	@Override
	public boolean checkPreCondition(String value, String valueNext) {
		return oCcase.whereStarted == 0;
		// return value.equalsIgnoreCase(valueNext);
	}
	
	public static void main(String[] a) {

	}

}
