package org.muplsql;

/***
 * 
 * @author arzubt
 *
 *
 *	Mutasyona ugramis kod hangi satir icin mutation operator uygulandiysa onu belirtir.
 *
 */

public class MutationContent {
	//FIXME public olanlari private yap.
	
	public String fileDetail;
	public int index;
	public String  operatorInfo;
	
	public String getFileDetail() {
		return fileDetail;
	}
	
	public void setFileDetail(String fileDetail) {
		this.fileDetail = fileDetail;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public String getOperatorInfo() {
		return operatorInfo;
	}
	
	public void setOperatorInfo(String operatorInfo) {
		this.operatorInfo = operatorInfo;
	}
	
	
	

}
