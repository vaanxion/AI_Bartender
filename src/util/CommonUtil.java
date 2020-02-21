package util;

public class CommonUtil {

	public String getParseString(String param) {
		String str;
		if (isBlank(param)) {
			str = "";
		} else {
			str = param;
		}
		return str.trim();
	}

	public int getParseInt(String param) {
		int i;
		if (isBlank(param)) {
			i = 0;
		} else {
			i = Integer.parseInt(param);
		}
		return i;
	}

	private boolean isBlank(String param) {
		if (param == null || param.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
