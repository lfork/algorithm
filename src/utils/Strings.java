package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 98620
 * @date 2018/10/16
 */
public class Strings {
    public static boolean isNullOrEmpty(String target) {
        return target == null || target.isEmpty();
    }

    private final static Pattern NUMERIC_PATTERN = Pattern.compile("-?[0-9]+.?[0-9]*");

    public static boolean isNumeric(String str) {
        Matcher isNum = NUMERIC_PATTERN.matcher(str);
        return isNum.matches();
    }
}
