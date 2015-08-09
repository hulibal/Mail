package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Date {
	
	public static String getDate(){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new java.util.Date());
	}

}
