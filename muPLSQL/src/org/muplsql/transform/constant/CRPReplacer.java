package org.muplsql.transform.constant;
import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;

public class CRPReplacer extends MITransformer {

	public CRPReplacer() {
		this.mtype = MutaOperatorType.Mot68.name();
		this.mopId = 68;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();

		if (value.equals("'")){
			result.add("xxx'");
		}
		///begin den once is den sonra olan '' constant;lar
		return result;
	}
	
//SQL dekindeyse like olmayanlar dier durumda like bozulur.
	//hic degismeyen olmasi kosulu var.
// precondition eklenebilir
	//:= ve ' icersin 
			
	public static void main(String[] a) {

	}

}
