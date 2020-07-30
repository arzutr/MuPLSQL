package org.muplsql.mt;

import org.muplsql.Configuration;
import org.muplsql.MutantExecuter;


/****
 * 
 * 			@author arzu bt
 * 
 *         	Purpose: According to db objects declared in  tmutation_operations  table for
 *         each mutant executes test cases.
 *
 *
 */

public class WinMainExecutor {

	MutantExecuter executer;
	Configuration configuration;
	
	public static void main(String[] args) {
		WinMainExecutor w = new WinMainExecutor();
		w.load();
		w.run();
	}
	
	private void load() {
		configuration = new Configuration();
		configuration.load();
		if (!configuration.isValidForMutantExecute()) {
			System.err.print("Configuration parameters not valid for execution!");
			System.exit(-9);
		}
	}

	private void run() {
		executer = new MutantExecuter(this);
		executer.executeTestCases();
	}

	
	public Configuration getConfiguration(){
		return this.configuration;
	}
}
