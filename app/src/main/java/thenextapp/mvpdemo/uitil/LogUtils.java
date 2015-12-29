package thenextapp.mvpdemo.uitil;

import android.util.Log;

import thenextapp.mvpdemo.BuildConfig;

/**
 * Created by Sandy on 12/26/15.
 */
public class LogUtils {

    private static final String TAG = "LogUtils";

    private static final String LOG_PREFIX = "Groovy_";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    private LogUtils() {
    }

    public static void debug(final String tag, String message) {
        if (BuildConfig.DEBUG || Log.isLoggable(tag, Log.DEBUG)) {
            Log.d(tag, message);
        }
    }

    public static void debug(final String tag, String message, Throwable cause) {
        if (BuildConfig.DEBUG || Log.isLoggable(tag, Log.DEBUG)) {
            Log.d(tag, message, cause);
        }
    }

    public static void verbose(final String tag, String message) {
        if (BuildConfig.DEBUG && Log.isLoggable(tag, Log.VERBOSE)) {
            Log.v(tag, message);
        }
    }

    public static void verbose(final String tag, String message, Throwable cause) {
        if (BuildConfig.DEBUG && Log.isLoggable(tag, Log.VERBOSE)) {
            Log.v(tag, message, cause);
        }
    }

    public static void info(final String tag, String message) {
        Log.i(tag, message);
    }

    public static void info(final String tag, String message, Throwable cause) {
        Log.i(tag, message, cause);
    }

    public static void error(final String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message, new Throwable());
        } else {
            Log.e(tag, message);
        }
    }

    public static void error(final String tag, String message, Throwable cause) {
        Log.e(tag, message, cause);
    }
}
