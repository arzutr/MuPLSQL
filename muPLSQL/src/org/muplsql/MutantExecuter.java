package org.muplsql;

import java.util.List;

import org.muplsql.db.DBManager;
import org.muplsql.db.DBMutation;
import org.muplsql.db.DBMutantCreator;
import org.muplsql.model.Mutant;
import org.muplsql.model.Mutation;
import org.muplsql.model.MutationConstants;
import org.muplsql.model.MutationOperator;
import org.muplsql.mt.WinMainExecutor;
import org.muplsql.transform.MITransformer;
import org.muplsql.util.UtilFile;

/***
 * 
 * @author arzubt
 *
 *
 *         purpose: deploying mutants into database and trigger test cases
 * 
 */
public class MutantExecuter {
	WinMainExecutor winMain;
	DBManager dbmanager;
	DBManager dbmanagerTarget;
	List<MutationOperator> moperators = null;
	List<Mutation> objects = null;
	List<MITransformer> listOfMoperators;
	UtilFile utilFile;

	public MutantExecuter(WinMainExecutor _winMain) {
		this.winMain = _winMain;
		load();
	}

	private void load() {

		dbmanager = new DBManager(getConfig().getDbUserName(), getConfig().getDbUserPassword(), getConfig().getDbUrl());
		dbmanagerTarget = new DBManager(getConfig().getDbTargetUserName(), getConfig().getDbTargetUserPassword(),
				getConfig().getDbTargetUrl());

	}

	/****
	 * reads defined test cases and objects then Executes defined test cases
	 */
	public void executeTestCases() {

		DBMutation dbm = new DBMutation();
		DBMutantCreator mt = new DBMutantCreator();

		UtilFile utf = new UtilFile(getSourceFolder(), getOutputFolder(), getFileREadCharset());
		List<Mutant> listMutant = dbm.getMutants(dbmanager.getConnection());
		// step1 read mutant
		for (Mutant m : listMutant) {
			// step2 create on db
			if (m.hasPks()) {
				mt.createMutant(dbmanagerTarget.getConnection(), utf.read(m.getNewObjectName(), MutationConstants.PKS),
						m.getObjectName());
			}
			mt.createMutant(dbmanagerTarget.getConnection(), utf.read(m.getNewObjectName(), m.getExt()), m.getObjectName());
			dbm.runTestCaseForMutant(dbmanagerTarget.getConnection(), m);
			// step3 call proc testcases
			// step4 drop mutant

			dbm.updateMutant(dbmanager.getConnection(), m);
		}
		dbmanager.stop();
		dbmanagerTarget.stop();
	}

	public Configuration getConfig() {
		return winMain.getConfiguration();
	}

	private String getFileREadCharset() {
		return winMain.getConfiguration().getFileREadCharset();
	}

	private String getOutputFolder() {
		return winMain.getConfiguration().getOutputFolder();
	}

	private String getSourceFolder() {
		return winMain.getConfiguration().getSourceFolder();
	}

}
