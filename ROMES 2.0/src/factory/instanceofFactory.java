package factory;

public class instanceofFactory {
	
	public static boolean isStringInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static boolean isStringLong(String s) {
		try {
			Long.parseLong(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static String removeNull(String string) {
		if(string == null) {
			return "";
		}else {
			return string;
		}
	}
}
