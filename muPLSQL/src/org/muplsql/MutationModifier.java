package org.muplsql;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.muplsql.db.DBManager;
import org.muplsql.db.DBMutation;
import org.muplsql.db.DBMutantCreator;
import org.muplsql.model.*;
import org.muplsql.statics.OracleReservedWords;
import org.muplsql.transform.MITransformer;
import org.muplsql.util.UtilFile;

import java.sql.Connection;
import java.util.*;

import spinat.plsqlparser.w.Res;
import spinat.plsqlparser.w.WScanner;
import spinat.plsqlparser.w.WSeq;
import spinat.plsqlparser.w.WToken;
import spinat.plsqlparser.w.WAst.Expression;

/*****************
 * 
 * 
 * @author Arzu BT
 *
 *      
 * 
 *         
 *
 *        
 *
 */

public class MutationModifier {

	UtilFile ufile;
	DBMutation dbm = new DBMutation();
	Connection connection = null;
	Connection connectionMutationDB = null;
	DBMutantCreator creatorinDB = new DBMutantCreator();
	String mheader;
	int index ;
	org.muplsql.mg.WinMain main;

	public MutationModifier(org.muplsql.mg.WinMain _main) { // Connection m,
		this.main = _main;
		this.mheader = main.getConfiguration().getSchemaName() + ".";
		ufile = new UtilFile(main.getConfiguration().getSourceFolder(), main.getConfiguration().getOutputFolder(),
				main.getConfiguration().getFileREadCharset());
		DBManager dbm = new DBManager(_main.getConfiguration().dbUserName, _main.getConfiguration().dbUserPassword,
				_main.getConfiguration().dbUrl);
		this.connection = dbm.getConnection();
		DBManager dbmm = new DBManager(_main.getConfiguration().dbTargetUserName, _main.getConfiguration().dbTargetUserPassword,
				_main.getConfiguration().dbTargetUrl);
		this.connectionMutationDB = dbmm.getConnection();
		DBMutation dbmutation = new DBMutation();
		index = dbmutation.getMutantId(connection, "");
	}

	public void modify(MutationOperator operator, Mutation detail) {

		String extention = getExtention(detail);
		try {
			Stream<String> lines = ufile.getLines(detail.getObjectName() + extention);

			List<MutantDetail> listOfMutants = new ArrayList<MutantDetail>();
			List<String> lineArray = lines.collect(Collectors.toList());
			int i = 0;

			for (String line : lineArray) {
				if (line.toUpperCase().contains(operator.getPattern())) {
					MutantDetail arg0 = createMutant(lineArray, i, line, detail, operator,
							getMutantId(connectionMutationDB, detail.getObjectName()), extention);
					listOfMutants.add(arg0);
				}
				i++;
			}
		} catch (Exception e) {
			System.err.println("modify" + e.getMessage());
		}
	}

	@Deprecated
	public void modifyEnhanced(MutationOperator operator, Mutation detail, MFile mFile) {

		String extention = getExtention(detail);
		try {
			Stream<String> lines = ufile.getLines(detail.getObjectName() + extention);

			List<MutantDetail> listOfMutants = new ArrayList<MutantDetail>();
			List<String> lineArray = lines.collect(Collectors.toList());
			int i = 0;

			for (String line : lineArray) {
				if (line.toUpperCase().contains(operator.getPattern())) {
					MutantDetail arg0 = createMutant(lineArray, i, line, detail, operator,
							getMutantId(connectionMutationDB, detail.getObjectName()), extention);
					listOfMutants.add(arg0);
				}
				i++;
			}
		} catch (Exception e) {
			System.err.println("modify" + e.getMessage());
		}
	}

	

	/**
	 * 1. save to db table
	 * 2. save to disk
	 * 3.execute in the db
	 * 
	 * */
	private MutantDetail createMutant(List<String> lineArray, int i, String line, Mutation detail,
			MutationOperator operator,int mutantId, String extention) {
			//System.out.println(i + ":" + line );
			
		   String newline = lineArray.get(i);
	       lineArray.set(i, newline.replace(operator.getPattern(), operator.getExec_str()));
	       StringBuffer buffer 	= new StringBuffer();
	       String 		header	= detail.getObjectOwner() + ".";
	       int j =0;
	       MutantDetail detailM = new MutantDetail();
		       detailM.setMutantId(mutantId);
		       detailM.setNewObjectName(detail.getObjectName()+mutantId);
	       int limit = lineArray.size();
	       String v = mheader + detail.getObjectName().toUpperCase();
	       String newName= mheader + detailM.getNewObjectName();
	       for (String s:lineArray) {
	    	  s = s.toUpperCase();
	    	   String sus = header+detail.getObjectName();
	    	   
	    	   /*
	    	   if (s.indexOf(sus) >=0){
	    		  s= s.replaceAll(header, mheader);
	    	   }*/
	    	   if (s.indexOf(detail.getObjectName().toUpperCase())>=0){
	    		   s=  s.replaceAll(detail.getObjectName().toUpperCase(), detail.getObjectName().toUpperCase()+mutantId);
	    	   }
	    		/*if (j>2 && s.toUpperCase().contains(detail.getObjectName())) {
            		s  = s.toUpperCase().replace(detail.getObjectName(),   detailM.getNewObjectName());
            	}*/
	    		
	    	   if (!s.trim().equals("/"))	{
	        	   buffer.append(s );
	        	   buffer.append(System.lineSeparator());
	    	   }
	    	   
	    	   j++;
	    	   buffer.append("  ");
	    	   if (j < limit-1){
	    		  buffer.append(System.lineSeparator());
	    	   }
	       }
		      detailM.setLineId(i);		     
		      detailM.setMutationOperatorId(operator.getMutationOperatorId());
		      detailM.setPackageDetail(buffer.toString());
		      detailM.setObjectName(detail.getObjectName());
		      saveMutant(detailM);

		 ufile.write(detailM.getNewObjectName()+extention, detailM.getPackageDetail());    
		 // create mutant in database is not required remarked.	 createMutatInDB(detailM,detail);
		 if (extention.equals(".pkb")){
			 //detailM.setSpecDetail(ufile.getPackageSpec(detailM,detail));
			 ufile.write(detailM.getNewObjectName()+".pks", detailM.getSpecDetail());
		 }
		return detailM;
	}

	private void createMutatInDB(MutantDetail detailM, Mutation detail) {

		if (detail.getObjectType().equals("PACKAGE")) {
			detailM.setSpecDetail(ufile.getPackageSpec(detailM, detail, main.getConfiguration().schemaName));
			creatorinDB.createMutant(this.connection, detailM.getSpecDetail(), detailM.getNewObjectName());
		}

		creatorinDB.createMutant(this.connection, detailM.getPackageDetail(), detailM.getNewObjectName());
	}

	private void saveMutant(MutantDetail detailM) {
		dbm.saveMutant(connectionMutationDB, detailM);
	}

	private int getMutantId(Connection connection, String objectName) {
		int value = dbm.getMutantId(connection, objectName);
		return value;
	}

	private String getExtention(Mutation detail) {
		if (detail.getObjectType().equalsIgnoreCase(MutationConstants.FUNCTION)) {
			return MutationConstants.FNC;
		} else {
			if (detail.getObjectType().equalsIgnoreCase(MutationConstants.PROCEDURE)) {
				return MutationConstants.PRC;
			}
		}
		return MutationConstants.PKB;
	}

	String[] notlist = new String[] { "" };

	public boolean contains(String v) {
		for (String s : notlist) {
			if (s.equalsIgnoreCase(v)) {
				return true;
			}
		}
		return false;
	}

	public int modifyInAst (MITransformer operator,  Mutation detail){
		int mcount = 0;
		String 	extention = getExtention(detail);
		if (contains(detail.getObjectName())){
			return -1;
		}
		try {
			String 		lines = ufile.getLinesAsStr(detail.getObjectName() + extention );	
			
			List<MutantDetail> 	listOfMutants = new ArrayList<MutantDetail>();
		
			
			  ArrayList<WToken> a = WScanner.scanAll(lines);
		        ArrayList<WToken> r = new ArrayList<>();
		        for (WToken t : a) {
		            if (WScanner.isRelevant(t)) {
		                r.add(t);
		            }
		        }
		        spinat.plsqlparser.w.Parser p = new spinat.plsqlparser.w.Parser();

		        WSeq s = new WSeq(r);
		        spinat.plsqlparser.w.Res<Expression> rs =  p.pExpr.pa(s);
		        
		        WSeq b =  rs.next;
		        
		
		        
		        String v = mheader + detail.getObjectName().toUpperCase();
		        int localindex=0;
		        String prevalForCheck = operator.getPreConditionVal();
		        String preval = "";
		        while (b.head().ttype.name() !="TheEnd") {
		        	
		        	b = b.tail(); localindex++;
		        	String main = b.head().str;
		        	if (main.equalsIgnoreCase(detail.getObjectName())){
		        		continue;
		        	}
		        	/**/
		        	if (operator.hasPreCondition()){
		        		operator.eval( b.head().str, preval);
		        	//	System.out.println("line: " +  b.head().str  + "\n"+ operator.getCase().toString());
		        	}
		        	preval= b.head().str;
		        	List<String> resM =  operator.isRef()?operator.mutateAsList( b.head().str,b.head().ttype.name() ): operator.mutateAsList( b.head().str); //FIXME exception varsa ref li olani cagir 
		        	if (resM.size()>0){
		        		for (String line: resM) {	
		        			index++; //objelerin yeni ismi icin
		        			//
        					//System.out.println("line: " +  b.head().str  + "\n"+ operator.getCase().toString());
		        			b.head().str = line;
		        			MutantDetail md = getMutantDetail(s);
		        			mcount++;
		        			md.setMutationOperatorId(operator.getMopId());
		        			md.setObjectName(detail.getObjectName());
		        			
		        			
		        			try {
		        				if (operator.hasPreCondition()){

		        					if (operator.checkPreCondition(preval,prevalForCheck)) {
		        						// FIXME arzu 	
		        						createMutantExtended(md.getwSeq(), localindex, line, detail, operator, index, extention); //index yerine bunu koy db icin getMutantId(connectionMutationDB, detail.getObjectName())	
		        					} else {
		        						//future use count
		        					}
		        					
		        				} else {
		        					// FIXME arzu 
		        					createMutantExtended(md.getwSeq(), localindex, line, detail, operator, index, extention); //index yerine bunu koy db icin getMutantId(connectionMutationDB, detail.getObjectName())
		        				}
		        			} catch (Exception e) {
		        				e.printStackTrace();
		        			}
		        			//arzu iptal 0110	preval = line;
		        			//System.exit(-9); //FIXME delete 
		        		
		        			listOfMutants.add(md);
		        			b.head().str = main;
		        		
		        		}
		        		
		        		//change old
		        		
		        	}		 operator.setPreVal(b.head().str );       	
		        }
		        b =  rs.next;
			} 
		catch (Exception e) {
			System.err.println("modify  " + e.getMessage() + "---" + operator.getMopId());
e.printStackTrace();
		}
		
		return mcount;
	}
	private int getIndex() {
		return this.main.getSequenceNumber();
	}

	private MutantDetail getMutantDetail(WSeq b) {
		List<WToken> listofToken = new ArrayList<WToken>();

		MutantDetail md = new MutantDetail();
		for (WToken w1 : b.getTokens()) {
			WToken save = new WToken();
			save.ttype = w1.ttype;
			save.str = w1.str;
			save.pos = w1.pos;
			save.ipos = w1.ipos;
			save.line = w1.line;
			save.col = w1.col;
			listofToken.add(save);
		}
		WSeq w = new WSeq(listofToken);
		md.setwSeq(w);
		return md;
	}

	private MutantDetail createMutantExtended(WSeq lineArray, int i, String line, Mutation detail,
			MITransformer operator, int mutantId, String extention) {

		StringBuffer buffer = new StringBuffer();
		buffer.append(MutationConstants.PRE_STATEMENT);
		MutantDetail detailM = new MutantDetail();
			detailM.setMutantId(mutantId);
			detailM.setNewObjectName(detail.getObjectName() + mutantId);

			spinat.plsqlparser.w.Parser p = new spinat.plsqlparser.w.Parser();
			spinat.plsqlparser.w.Res<Expression> rs = p.pExpr.pa(lineArray);
			WSeq b = rs.next;
	
			while (b.head().ttype.name() != MutationConstants.EOF_PARSER) {
				String appender_pre = " ";
				String appender_post = " ";
	
				if (isLineSep(b)) {
					appender_post = System.lineSeparator();
					appender_pre = " ";
				} else if (b.head().str.equals(MutationConstants.DOT)) {
					appender_post = "";
					appender_pre = "";
				} else if (OracleReservedWords.contains(b.head().str.toUpperCase())) {
					appender_post = " ";
					appender_pre = " ";
				} else if (b.head().str.equals(MutationConstants.PERCENT)) {
					appender_post = "";
					appender_pre = "";
				}
	
				buffer.append(appender_pre);
				buffer.append(b.head().str);			//b.head().str.equalsIgnoreCase(detail.getObjectName()) ? detailM.getObjectName() : b.head().str);//detailM.getObjectName()  same name
				buffer.append(appender_post);
				b = b.tail();
			}
		buffer.append(System.lineSeparator());
			detailM.setLineId(i);
			detailM.setMutationOperatorId(operator.getMopId());
			detailM.setPackageDetail(buffer.toString());
			detailM.setObjectName(detail.getObjectName());
		saveMutant(detailM); 
		
		ufile.write(detailM.getNewObjectName() + extention, detailM.getPackageDetail());

	// not required	createMutatInDB(detailM, detail); 

		if (extention.equals(MutationConstants.PKB)) {
			try {
				detailM.setSpecDetail(ufile.getPackageSpec(detailM, detail, main.getConfiguration().schemaName));
				ufile.write(detailM.getNewObjectName() + MutationConstants.PKS, detailM.getSpecDetail());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return detailM;
	}

	private boolean isLineSep(WSeq b) {
		return b.head().str.contains(";") || b.head().str.equalsIgnoreCase("BEGIN")
				|| b.head().str.equalsIgnoreCase("OPEN") || b.head().str.equalsIgnoreCase("IS")
				|| b.head().str.equalsIgnoreCase("THEN") || b.head().str.equalsIgnoreCase("THEN");
	}

}
