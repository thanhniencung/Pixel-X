package thenextapp.mvpdemo.uitil;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;

/**
 * Created by Sandy on 12/25/15.
 */
public class DialogUtils {

    public static Dialog getInstanceDialoLoading(Activity activity) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Loading ...");
        return progressDialog;
    }

}
