package com.vimalcvs.materialrating.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
/**
 * Created by VimalCvs on 02/11/2020.
 */


public class RateSPManager{

    private static final String NEVER_ASK_KEY = "never_ask_key";
    private static final String TIME_KEY = "time_key";
    private static final String LAUNCH_TIMES_KEY = "launch_times_key";
    private static final int DAYS_DELAY = 3 * (24 * 60 * 60 * 1000);
    private static final int LAUNCH_TIMES = 1;


    private static SharedPreferences getSP( Context c ){
        return c.getSharedPreferences( "preferences", Context.MODE_PRIVATE);
    }

    public static void neverAskAgain( Context c ){
        SharedPreferences sp = getSP( c );
        sp.edit().putBoolean( NEVER_ASK_KEY, true ).apply();
    }
    private static boolean isNeverAskAgain( Context c ){
        SharedPreferences sp = getSP( c );
        return sp.getBoolean( NEVER_ASK_KEY, false );
    }


    public static void updateTime( Context c ){
        SharedPreferences sp = getSP( c );
        sp.edit().putLong( TIME_KEY, System.currentTimeMillis() + DAYS_DELAY ).apply();
    }
    private static boolean isTimeValid( Context c ){
        SharedPreferences sp = getSP( c );
        Long time = sp.getLong( TIME_KEY, 0 );


       // IF IT'S THE FIRST TIME DEVICE IS BEING USED.
        if( time == 0 ){
            updateTime(c);
            time = sp.getLong( TIME_KEY, 0 );
        }

        return time < System.currentTimeMillis();
    }


    public static void updateLaunchTimes( Context c ){
        SharedPreferences sp = getSP( c );
        sp.edit().putInt( LAUNCH_TIMES_KEY, 0).apply();
    }
    public static void updateLaunchTimes( Context c, Bundle savedInstanceState ){
        // STANDARD GUARD CLAUSE
        if( savedInstanceState != null ){
            return;
        }

        SharedPreferences sp = getSP( c );
        int launchTimes = sp.getInt( LAUNCH_TIMES_KEY, 0 );
        sp.edit().putInt( LAUNCH_TIMES_KEY, launchTimes + 1).apply();
    }
    private static boolean isLaunchTimesValid( Context c ){
        SharedPreferences sp = getSP( c );
        int launchTimes = sp.getInt( LAUNCH_TIMES_KEY, 0 );
        return launchTimes > 0 && launchTimes % LAUNCH_TIMES == 0;
    }


    public static boolean canShowDialog( Context c ){
        return !isNeverAskAgain(c)
                && ( isTimeValid(c)
                    || isLaunchTimesValid(c) );
    }
}
