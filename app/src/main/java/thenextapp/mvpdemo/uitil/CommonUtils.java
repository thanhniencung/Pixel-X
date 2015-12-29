package thenextapp.mvpdemo.uitil;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Sandy on 12/29/15.
 */
public class CommonUtils {
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
