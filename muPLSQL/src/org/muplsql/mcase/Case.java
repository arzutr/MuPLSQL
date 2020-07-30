package org.muplsql.mcase;

public class Case {

	public int ifStarted = 0;
	public int whereStarted = 0;
	public int greaterStarted = 0;
	public int lesserStarted = 0;
	public int bracketStarted = 0;
	public int quoteStarted = 0;
	public String preString="";

	public void reset() {
		ifStarted = 0;
		whereStarted = 0;
		greaterStarted = 0;
		lesserStarted = 0;
		bracketStarted = 0;
		quoteStarted = 0;
		preString="";
	}

	@Override
	public String toString() {
		return "ifStarted:" + ifStarted + "\n" + "ifStarted:" + ifStarted + "\n" + "whereStarted:" + whereStarted + "\n"
				+ "greaterStarted:" + greaterStarted + "\n" + "lesserStarted:" + lesserStarted + "\n"
				+ "bracketStarted:" + bracketStarted + "\n" + "quoteStarted:" + quoteStarted + "\n" + " preString : " + preString + "\n";
	}
}