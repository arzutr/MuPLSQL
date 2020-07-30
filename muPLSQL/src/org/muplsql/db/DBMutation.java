package org.muplsql.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.muplsql.model.*;

/***
 * 
 * @author Arzu BT
 *
 */
public class DBMutation {

	public List<MutationOperator> getMutationOperators(Connection connection) {
		List<MutationOperator> oLst = new ArrayList<MutationOperator>();
		try {
			Statement stm = connection.createStatement();

			ResultSet rs = stm.executeQuery(
					"select  PATTERN, EXEC_STR, MNAME, MUTATION_OPERATOR_ID,NOT_EXEC from TMUTATION_CONFIG");
			while (rs.next()) {
				MutationOperator op = new MutationOperator(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getInt(4), rs.getString(5));
				oLst.add(op);
			}

			rs.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return oLst;
	}

	public List<Mutation> getObjects(Connection connection) {
		List<Mutation> oLst = new ArrayList<Mutation>();
		try {
			Statement stm = connection.createStatement();

			ResultSet rs = stm
					.executeQuery("select  OBJECT_NAME, OBJECT_TYPE, OBJECT_OWNER from TMUTATION where enabled=0");
			while (rs.next()) {
				Mutation op = new Mutation(rs.getString(1), rs.getString(2), rs.getString(3));
				oLst.add(op);
			}

			rs.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return oLst;
	}

	public int getMutantId(Connection connection, String objectName) {
		int mutantid = 0;
		try {
			PreparedStatement stm = connection
					.prepareStatement("select nvl(max(mutant_id),0)+1 from tmutation_operations");
			// select mutant_seq.nextval from dual -->less effective version , removed.
			// stm.setString(1, objectName);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				mutantid = rs.getInt(1);
			}

			rs.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mutantid;
	}

	public void saveMutant(Connection connection, MutantDetail detailM) {
		try {
			PreparedStatement stm = connection.prepareStatement(
					"insert into TMUTATION_OPERATIONS (OBJECT_NAME, NEW_OBJECT_NAME, MUTATION_OPERATOR_ID, MNAME, CHANGED_LINE, MUTANT_ID) values (?,?,?,?,?,?)");
			stm.setString(1, detailM.getObjectName());
			stm.setString(2, detailM.getNewObjectName());
			stm.setInt(3, detailM.getMutationOperatorId());
			stm.setInt(4, detailM.getMutationOperatorId());
			stm.setInt(5, detailM.getLineId());
			stm.setInt(6, detailM.getMutantId());
			int result = stm.executeUpdate();

			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/***
	 * retrieving available mutants will be tested
	 * 
	 * @param connection
	 * @return
	 */
	public List<Mutant> getMutants(Connection connection) {
		List<Mutant> list = new ArrayList<Mutant>();
		try {

			PreparedStatement stm = connection.prepareStatement(
					"select MO.OBJECT_NAME, MO.NEW_OBJECT_NAME, MO.MUTATION_OPERATOR_ID, MO.MNAME, MO.CHANGED_LINE, MO.MUTANT_ID, M.OBJECT_TYPE from "
							+ " TMUTATION_OPERATIONS MO , TMUTATION M  WHERE MO.OBJECT_NAME = M.OBJECT_NAME AND  MO.is_run = 0 ");

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Mutant m = new Mutant(rs.getString(1), rs.getString(2), rs.getInt(6), rs.getString(7));
				list.add(m);
			}

			stm.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	public void updateMutant(Connection connection, Mutant detailM) {
		try {
			PreparedStatement stm = connection.prepareStatement(
					"update TMUTATION_OPERATIONS  set is_run = 1 where OBJECT_NAME = ? and  NEW_OBJECT_NAME = ?");
			stm.setString(1, detailM.getObjectName());
			stm.setString(2, detailM.getNewObjectName());
			int result = stm.executeUpdate();

			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/***
	 * connection user should be granted execute this package
	 * 
	 * @param connection
	 * @param detailM
	 */
	public void runTestCaseForMutant(Connection connection, Mutant detailM) {
		CallableStatement callStmt = null;
		try {
			callStmt = connection.prepareCall("{ call PKG_MUTATION.RUN_TESTCASE_PRC(?,?,?,?) }");
			callStmt.setString(1, detailM.getObjectName());
			callStmt.setString(2, detailM.getNewObjectName());
			callStmt.setInt(3, detailM.getMutantId());
			callStmt.registerOutParameter(4, Types.VARCHAR);

			boolean result = callStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			if (callStmt != null) {
				try {
					callStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
