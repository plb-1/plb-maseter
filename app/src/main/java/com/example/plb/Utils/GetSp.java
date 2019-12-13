package com.example.plb.Utils;

import android.app.Activity;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by rose on 2019/12/13.
 */

public class GetSp extends Activity{
    private static final GetSp ourInstance = new GetSp();
    private static SharedPreferences sharedPreferences;

    public static GetSp getInstance() {
        return ourInstance;
    }

    public SharedPreferences getSpInstance() {
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        return sharedPreferences;
    }

    private GetSp() {
    }
}
