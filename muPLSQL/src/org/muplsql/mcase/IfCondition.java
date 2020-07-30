package org.muplsql.mcase;

public class IfCondition  implements ICondition {

	public boolean available (Case oCase){
		return oCase.ifStarted>0;
	}
	
}