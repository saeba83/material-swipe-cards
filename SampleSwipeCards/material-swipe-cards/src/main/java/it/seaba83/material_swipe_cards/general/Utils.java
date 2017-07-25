package it.seaba83.material_swipe_cards.general;

import android.util.Log;

/**
 * Created by Marco on 25/07/2017.
 */

public class Utils {

    private final static String LOG_PREFIX = "MAT-SWIPE-CARDS";

    public static void log (String tag, String value){
            Log.i("[" + LOG_PREFIX + " - " + tag + "]", value);
    }
}
