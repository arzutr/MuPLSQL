package org.muplsql.model;

import spinat.plsqlparser.w.WSeq;

/***
 * 
 * @author arzubt
 *
 *
 * purpose: TMUTATION_OPERATIONS table will be populated according to generated each mutant
 *
 */
public class MutantDetail {
	
	private String objectName;
	private String newObjectName;
	private int mutationOperatorId;
	private int mutatorName;
	private int lineId;
	private int mutantId;
	private String packageDetail;
	private String specDetail;
	private WSeq wSeq;
	
	public MutantDetail(){
		
	}
	
	public MutantDetail(String objectName, String newObjectName, int mutationOperatorId, int mutatorName, int lineId,
			int mutantId, String packageDetail) {
		super();
		this.objectName = objectName;
		this.newObjectName = newObjectName;
		this.mutationOperatorId = mutationOperatorId;
		this.mutatorName = mutatorName;
		this.lineId = lineId;
		this.mutantId = mutantId;
		this.packageDetail = packageDetail;
	}

	public String getObjectName() {
		return objectName;
	}
	
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
	public String getNewObjectName() {
		return newObjectName;
	}
	
	public void setNewObjectName(String newObjectName) {
		this.newObjectName = newObjectName;
	}
	
	public int getMutationOperatorId() {
		return mutationOperatorId;
	}
	
	public void setMutationOperatorId(int mutationOperatorId) {
		this.mutationOperatorId = mutationOperatorId;
	}
	
	public int getMutatorName() {
		return mutatorName;
	}
	
	public void setMutatorName(int mutatorName) {
		this.mutatorName = mutatorName;
	}
	
	public int getLineId() {
		return lineId;
	}
	
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	
	public int getMutantId() {
		return mutantId;
	}
	
	public void setMutantId(int mutantId) {
		this.mutantId = mutantId;
	}
	
	public String getPackageDetail() {
		return packageDetail;
	}
	
	public void setPackageDetail(String packageDetail) {
		this.packageDetail = packageDetail;
	}

	public String getSpecDetail() {
		return specDetail;
	}

	public void setSpecDetail(String specDetail) {
		this.specDetail = specDetail;
	}

	public WSeq getwSeq() {
		return wSeq;
	}

	public void setwSeq(WSeq wSeq) {
		this.wSeq = wSeq;
	}
	
	
}
