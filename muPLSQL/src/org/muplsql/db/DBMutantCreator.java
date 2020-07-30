package org.muplsql.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class DBMutantCreator {

	/*************
	 * Steps:
	 * 
	 * 1. Get object list
	 * 2. Get mutation operator list
	 * 3. Read each object
	 * 4. Apply each operator for each line if available
	 * 5. Save mutant into db 
	 * 6. Create record for mutant 
	 * 7. Go to 4 until end of the file 
	 * 8. Go to 2 read next operator 
	 * Sample content:
	 * "CREATE OR REPLACE PACKAGE  PKG_MUTATION AS  PROCEDURE RUN_TESTCASES_PRC (    P_TCASE_ID      IN       NUMBER);  END PKG_MUTATION;"
	 * 
	 */
	public void createMutant(Connection connection, String content, String objectName) {
	      try {
	    	  CallableStatement funcnone = connection.prepareCall (content);
	    	  funcnone.execute ();
			funcnone.close();
	    } catch (java.sql.SQLSyntaxErrorException ee){
			System.err.println(objectName +  ee.getMessage());
		} catch (SQLException e) {
			System.err.println(objectName +  e.getMessage());
		} catch (Exception e) {
			System.err.println(objectName +  e.getMessage());
		}
	}

	
	
}
