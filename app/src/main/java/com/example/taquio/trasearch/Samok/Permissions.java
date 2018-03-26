package com.example.taquio.trasearch.Samok;

/**
 * Created by Taquio on 3/26/2018.
 */

import android.content.Context;
import android.content.SharedPreferences;

public class Permissions {

    private static final String FILE_NAME = "file_name";
    private static final String KEY_THEM_ALL = "my_key";

    public static void safeVerification(Context context) {
        // we shall save 1, if user is already verified. Anything else will mean that
        // user is not verified
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_THEM_ALL, "1");
        editor.apply();
    }

    public static boolean isUserVerified(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String code = sharedPreferences.getString(KEY_THEM_ALL, "");

        if (code.contentEquals("1")) {
            return true;
        }

        // in the else part we can just return false
        return false;
    }
}
