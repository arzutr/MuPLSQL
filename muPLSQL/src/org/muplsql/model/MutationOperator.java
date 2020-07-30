package org.muplsql.model;

/*****
 * 
 * @author arzubt
 *
 *         purpose: corresponds to TMUTATION_CONFIG table. Keeps each mutation
 *         operator
 *
 */
public class MutationOperator {

	private String pattern;
	private String exec_str;
	private String mname;
	private int mutationOperatorId;
	private String notExec;

	public MutationOperator(String pattern, String exec_str, String mname, int mutationOperatorId, String not_exec) {
		super();
		this.pattern = pattern;
		this.exec_str = exec_str;
		this.mname = mname;
		this.mutationOperatorId = mutationOperatorId;
		this.notExec = not_exec;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getExec_str() {
		return exec_str;
	}

	public void setExec_str(String exec_str) {
		this.exec_str = exec_str;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public int getMutationOperatorId() {
		return mutationOperatorId;
	}

	public void setMutationOperatorId(int mutationOperatorId) {
		this.mutationOperatorId = mutationOperatorId;
	}

	public String getNotExec() {
		return notExec;
	}

	public void setNotExec(String notExec) {
		this.notExec = notExec;
	}

	@Override
	public String toString() {
		return this.pattern + this.getMname() + this.getMutationOperatorId() + MutationConstants.NEWLINE;
	}

}
