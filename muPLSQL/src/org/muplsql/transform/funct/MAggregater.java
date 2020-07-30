package org.muplsql.transform.funct;

import java.util.ArrayList;
import java.util.List;

import org.muplsql.transform.MITransformer;
import org.muplsql.transform.MutaOperatorType;
/***
 * 
 * @author arzu
 *
 *
 * TODO: in future count/sum  etc could be added  these used functions  could be enough to see efficiency 
 */
public class MAggregater  extends MITransformer {

	public MAggregater() {
		this.mtype = MutaOperatorType.Mot031.name();
		this.mopId = 31;
	}

	@Override
	public List<String> mutateAsList(String value) {
		List<String> result = new ArrayList<>();
		if (value.equalsIgnoreCase("MIN")) {
			result.add("MAX");
			result.add("AVG");
		} else if (value.equalsIgnoreCase("MAX")) {
			result.add("MIN");
			result.add("AVG");
		} else if (value.equalsIgnoreCase("AVG")) {
			result.add("MAX");
			result.add("MIN");
		}
		return result;
	}

	public static void main(String[] a) {

	}

}
