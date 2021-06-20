package org.muplsql.mcase;

public class Case {

	public int ifStarted = 0;
	public int tStarted = 0;
	public int whereStarted = 0;
	public int selectStarted = 0;
	public int greaterStarted = 0;
	public int lesserStarted = 0;
	public int bracketStarted = 0;
	public int quoteStarted = 0;
	public int groupByStarted = 0;
	public int beginStarted = 0;
	public int equalsStarted = 0;
	public int assignStarted = 0;
	public String preString = "";
	public String preClauseString = "";
	
	public void reset() {
		ifStarted = 0;
		whereStarted = 0;
		greaterStarted = 0;
		lesserStarted = 0;
		bracketStarted = 0;
		quoteStarted = 0;
		groupByStarted = 0;
		selectStarted = 0;
		tStarted=0;
		beginStarted=0;
		equalsStarted=0;
		assignStarted=0;
		preString = "";
		preClauseString = "";
	}

	@Override
	public String toString() {
		return "ifStarted:" + ifStarted + "\n" + "ifStarted:" + ifStarted + "\n" + "whereStarted:" + whereStarted + "\n"
				+ "greaterStarted:" + greaterStarted + "\n" + "lesserStarted:" + lesserStarted + "\n"
				+ "bracketStarted:" + bracketStarted + "\n" + "quoteStarted:" + quoteStarted + "\n" + " preString : "
				+ preString + "\n" + " selectStarted : " + selectStarted + "\n"+ " tStarted : " + tStarted + "\n";
	}
}
