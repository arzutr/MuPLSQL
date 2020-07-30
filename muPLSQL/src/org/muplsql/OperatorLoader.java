package org.muplsql;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.model.MutationOperator;
import org.muplsql.transform.MBomber;
import org.muplsql.transform.MITransformer;
import org.muplsql.transform.ar.MArithmeticOperatorChanger;
import org.muplsql.transform.funct.ArrayIndexReplacer;
import org.muplsql.transform.funct.MIFRelationalOpChanger;
import org.muplsql.transform.funct.MAbsInserter;
import org.muplsql.transform.funct.MAggregater;
import org.muplsql.transform.funct.MAssignOperatorReplacer;
import org.muplsql.transform.funct.MBreakAdder;
import org.muplsql.transform.funct.MGotoReplacer;
//import org.muplsql.transform.funct.MReturnAdder;
//import org.muplsql.transform.funct.MStepOmitter;
import org.muplsql.transform.rel.MLogicalConnectorChanger;

import org.muplsql.transform.sql.MDistinctRemover;
import org.muplsql.transform.sql.MHavingEditer;
import org.muplsql.transform.sql.MJoinReplacer;
import org.muplsql.transform.sql.MLikeHelperChanger;
import org.muplsql.transform.sql.MNullPredicateReplacer;
import org.muplsql.transform.sql.MUnionReplacer;
import org.muplsql.transform.sql.MWhereOrderReplacer;
import org.muplsql.transform.sql.MSQLAbsConverter;
import org.muplsql.transform.sql.MSQLRelationalOpChanger;
import org.muplsql.transform.sql.MSQLLogicalConnectorChanger;
import org.muplsql.transform.struct.MCommitRemover;


import org.muplsql.transform.struct.MExceptionInsertion;
import org.muplsql.transform.struct.MQueryErrorInsertion;
import org.muplsql.transform.struct.MNextvalReplacer;
import org.muplsql.transform.struct.MNumberConstantReplacer;
import org.muplsql.transform.struct.MOracleFunctionReplacer;
import org.muplsql.transform.struct.MTypeChanger;

/**
 * 
 * 	FUTURETASK: Available mutation operators are loading 
 *  They will be get from db in future
 *  
 * */
public class OperatorLoader {
	
	public static List<MITransformer> loadOperators(List<MutationOperator> listOfValidOperators) {

			List<MITransformer> listOfMoperators = new ArrayList<MITransformer>();

			if (listOfValidOperators != null) // FIXME read from properties file and dynamic loading in FUTURE
			{
				return loadFromDB(listOfValidOperators);
			}

		    listOfMoperators.add(new MBomber());
			listOfMoperators.add(new MArithmeticOperatorChanger());
			listOfMoperators.add(new ArrayIndexReplacer());
			listOfMoperators.add(new MIFRelationalOpChanger());
			listOfMoperators.add(new MAbsInserter());
			listOfMoperators.add(new MAggregater());
			listOfMoperators.add(new MAssignOperatorReplacer());		
			listOfMoperators.add(new MBreakAdder());
			listOfMoperators.add(new MGotoReplacer());
			
			//releational
			listOfMoperators.add(new MLogicalConnectorChanger());
			
			
			//sql
			listOfMoperators.add(new MDistinctRemover());
			listOfMoperators.add(new MHavingEditer());
			listOfMoperators.add(new	MNullPredicateReplacer());
			listOfMoperators.add(new MJoinReplacer());
			listOfMoperators.add(new MAggregater());			
			listOfMoperators.add(new MLikeHelperChanger());			
			listOfMoperators.add(new MUnionReplacer());
			listOfMoperators.add(new MWhereOrderReplacer());
			listOfMoperators.add(new MSQLAbsConverter());
			listOfMoperators.add(new MSQLRelationalOpChanger());
			listOfMoperators.add(new MSQLLogicalConnectorChanger());
			
			//plsql

			listOfMoperators.add(new MCommitRemover());			
			listOfMoperators.add(new MExceptionInsertion());						
			listOfMoperators.add(new MQueryErrorInsertion());
			listOfMoperators.add(new MNextvalReplacer());			
			listOfMoperators.add(new MNumberConstantReplacer());			
			listOfMoperators.add(new MOracleFunctionReplacer());
			listOfMoperators.add(new MTypeChanger());
	
		return listOfMoperators;
	}

	private static List<MITransformer> loadFromDB(
			List<MutationOperator> listOfValidOperators) {
		// TODO Auto-generated method stub
		return null;
	}


}
