
package org.muplsql.mg;

import java.util.List;

import org.muplsql.Configuration;
import org.muplsql.MutationModifier;
import org.muplsql.OperatorLoader;
import org.muplsql.db.DBManager;
import org.muplsql.db.DBMutation;
import org.muplsql.model.Mutation;
import org.muplsql.model.MutationConstants;
import org.muplsql.model.MutationOperator;
import org.muplsql.transform.MITransformer;
/**
 * 
 * @author arzubt
 *
 *
 * future task: load operators generic and remove class dependency
 */
public class WinMain {
	DBManager dbmanager;
	Configuration configuration;
	List<MutationOperator> moperators = null;
	List<Mutation> objects = null;
	List<MITransformer> listOfMoperators;
	volatile int sequnceNumber;
	int mutantLoadType = MutationConstants.STATIC_CONFIG;

	public static void main(String[] args) {

		WinMain w = new WinMain(args);
		w.load();
		w.create();

	}

	private void load() {
		configuration = new Configuration();
		configuration.load();

		dbmanager = new DBManager(configuration.getDbUserName(), configuration.getDbUserPassword(),
				configuration.getDbUrl());

		if (configuration.isNotValidForMutantCreate()) { 
			System.err.print("Configuration parameters not valid for mutant creation!");
			System.exit(-9);
		}

		DBMutation dbm = new DBMutation();
		this.listOfMoperators = OperatorLoader
				.loadOperators(isDBConfig() ? dbm.getMutationOperators(dbmanager.getConnection()) : null);

		//version from 2017 this.moperators = dbm.getMutationOperators(dbmanager.getConnection());
		this.objects = dbm.getObjects(dbmanager.getConnection());
		dbmanager.stop();
	}

	private boolean isDBConfig() {
		return mutantLoadType == MutationConstants.DB_CONFIG;
	}

	private void create() {

		MutationModifier modifier = new MutationModifier(this);
		int totalMutant = 0;
		for (MITransformer operator : listOfMoperators) {
			for (Mutation object : objects) {
				int k = modifier.modifyInAst(operator, object);
				totalMutant += k;
			}
		}
		System.out.println("Total=  " + totalMutant + "produced");
	}

	private WinMain() {
		super();
	}

	public WinMain(String[] args) {
		if (args == null && args.length == 0)
			return;

		for (String a : args) {
			if (a.equals("-DBConfig"))
				mutantLoadType = MutationConstants.DB_CONFIG;
		}

	}

	public int getSequenceNumber() {
		return sequnceNumber++;
	}

	public Configuration getConfiguration() {
		return this.configuration;
	}
}
