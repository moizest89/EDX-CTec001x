package moizest89.com.tipcalc;

import android.app.Application;

/**
 * Created by @moizest89 in SV on 6/15/16.
 */
public class TipCalcApp extends Application{


    private final static String ABOUT_ME = "http://www.about.me/moizest89";


    public static String getAboutMe() {
        return ABOUT_ME;
    }
}
