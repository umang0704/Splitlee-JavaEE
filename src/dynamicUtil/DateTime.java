package dynamicUtil;

import java.util.*;

public class DateTime {
	/**
	 * Gets the date hashmap from string date from
	 * 
	 * @param date
	 * @return
	 */
	public static HashMap<Character, Integer> getIntDate(String date) {
		HashMap<Character, Integer> ret = null;
		StringTokenizer str = new StringTokenizer(date, "-");
		if (str.hasMoreElements()) {
			ret = new HashMap<Character, Integer>();
			ret.put('y', Integer.parseInt(str.nextToken()));
			ret.put('m', Integer.parseInt(str.nextToken()));
			ret.put('d', Integer.parseInt(str.nextToken()));
		}
		return ret;
	}
}
