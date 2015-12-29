package thenextapp.mvpdemo.uitil;

/**
 * Created by Sandy on 12/27/15.
 */
public class StringUtils {
    public static boolean isEmpty(String str) {
        boolean isEmpty = false;
        if (str == null || str.trim().length() == 0 || ("null").equalsIgnoreCase(str)) {
            isEmpty = true;
        }
        return isEmpty;
    }
}
