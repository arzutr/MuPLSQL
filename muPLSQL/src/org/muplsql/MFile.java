package org.muplsql;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.model.Mutation;
import org.muplsql.model.MutationConstants;
import org.muplsql.statics.OracleReservedWords;
import org.muplsql.util.UtilFile;

import spinat.plsqlparser.*;

public class MFile {

	//	PROCEDURE
	//	PACKAGE
	//	dosya turu ve isminden dosya adini uretir.
	public Mutation objectInfo;
	public String filename;//"C:\\mutation\\source\\objname.pkb
	public String orginalContent;
	
	public void load(){
		this.orginalContent = UtilFile.loadFile("filename");
	}
	
	public MFile () {
		super();
	}
			
	
	public MFile (  Mutation _objectInfo) {
		this.objectInfo = _objectInfo;
	}
	
	
	public String getNextToken() {
		return null;
		
	}
	
	public int applyMutation(int sequence) {
		  int index = sequence;
		  ArrayList<Token> a = Scanner.scanAll(orginalContent);
	        ArrayList<Token> r = new ArrayList<>();
	        
	        for (Token t : a) {
	            if (Scanner.isRelevant(t)) {
	                r.add(t);
	            }
	        }
	        
	        Parser p = new Parser();

	        Seq s = new Seq(r);
	        Res rs =  p.pExpr.pa(s);

	        Seq b =  rs.next;
	        int i = 0;
	        List<Integer> listDot = new ArrayList<Integer>();

	        while (b.head().ttype.name() !=MutationConstants.EOF_PARSER) 	        {
	        	if (!OracleReservedWords.contains(b.head().str ))
	        		System.out.println(b.head().str + " ++++ " + b.head().ttype.name() + "  " );
	        	b = b.tail();
	        	if (b.head().ttype.name().equals("Dot")){
	        		listDot.add(i);
	        	}
	        	i ++;
	        }

	        b =  rs.next;
	        i = 0;

	        while (b.head().ttype.name() !=MutationConstants.EOF_PARSER) 
	        {

	        	b = b.tail();
	        	Integer j = ((i+1));

	        	if (listDot.contains(j) || listDot.contains(i)){

	        		System.out.print(b.head().str);
	        	} else {
	        		System.out.print(b.head().str + System.lineSeparator());
	        	}

	        	if (b.head().ttype.name().equals("Semi")){
	        		System.out.print( System.lineSeparator());
	        	}
	        	i++;
	        }
	        return index;//son objeden sonrasina bakilir en sona geldiyse 0 return eder. hic bulamazsa da.
	}
	
	
	public boolean isLastToken(){
		//FIXME
		return false;
	}


	public Mutation getObjectInfo() {
		return objectInfo;
	}


	public void setObjectInfo(Mutation objectInfo) {
		this.objectInfo = objectInfo;
	}


	public String getFilename() {
		if (filename==null) {
			filename = objectInfo.getObjectName() + getExtention();
		}
		return filename;
	}


	private String getExtention() {
		if ( objectInfo.getObjectType().equalsIgnoreCase(MutationConstants.PACKAGE)) {
			return MutationConstants.PKB;
		}
		if ( objectInfo.getObjectType().equalsIgnoreCase(MutationConstants.FUNCTION)) {
			return MutationConstants.FNC;
		}
		if ( objectInfo.getObjectType().equalsIgnoreCase(MutationConstants.PROCEDURE)) {
			return MutationConstants.PRC;
		}
		return "";
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
		

}
