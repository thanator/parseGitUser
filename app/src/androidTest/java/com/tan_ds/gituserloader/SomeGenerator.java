package com.tan_ds.gituserloader;

import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * Created by Tan-DS on 6/23/2017.
 */

public class SomeGenerator {
    private static final Random RANDOM = new Random();
    private static final int SIZE = 10;
    private static final int START_CHAR = (int) 'A';
    private static final int END_CHAR = (int) 'Z';

    public static Bundle createSomeBundle(){
        Bundle bundle = new Bundle();

        long lg = createRandomLong();
        String str = createRandomString();


        bundle.putString("login", str);
        bundle.putLong("id", lg);
        return bundle;
    }
    public static JSONObject createSomeJson(){
        JSONObject object = new JSONObject();

        try {
            object.put("id", createRandomLong());
            object.put("login", createRandomString());
        } catch (JSONException e){
            e.printStackTrace();
        }
        return object;
    }

    protected static long createRandomLong(){
        long lb = 0;
        for (int i = 0; i < SIZE; i++) {
            int value = START_CHAR + RANDOM.nextInt(
                    END_CHAR - START_CHAR
            );
            lb += value;
        }
        return lb;
    }

    protected static String createRandomString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            int value = START_CHAR + RANDOM.nextInt(
                    END_CHAR - START_CHAR
            );
            sb.append((char) value);
        }
        return sb.toString();
    }
}
