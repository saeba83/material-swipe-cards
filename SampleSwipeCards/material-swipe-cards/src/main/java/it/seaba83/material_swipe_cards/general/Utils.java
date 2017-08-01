package it.seaba83.material_swipe_cards.general;

import android.util.Log;

import it.seaba83.material_swipe_cards.BuildConfig;

/**
 * Created by Marco on 25/07/2017.
 */

public class Utils {

    private final static String LOG_PREFIX = "MAT-SWIPE-CARDS";

    public static void log (String tag, String value){
        if (BuildConfig.IS_LOG_ENABLED) {
            Log.i("[" + LOG_PREFIX + " - " + tag + "]", value);
        }
    }
}
