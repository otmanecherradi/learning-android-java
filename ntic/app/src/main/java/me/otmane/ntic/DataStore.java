package me.otmane.ntic;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class DataStore {
    public static final String TAG = "DataStore";

    private static final String SHARED_PREFERENCES_KEY = "me.otmane.ntic.DataStore";
    private static final String ACCESS_TOKEN_KEY = "accessToken";
    private static final String REFRESH_TOKEN_KEY = "refreshToken";

    private final SharedPreferences privateSharedPreferences;

    private static DataStore instance;

    public static void init(Context applicationContext) {
        instance = new DataStore(applicationContext);
    }

    public static DataStore getInstance() {
        return instance;
    }

    private DataStore(Context context) {
        Log.d(TAG, "initiate DataStore");

        this.privateSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    private void storeStringInSharedPreferences(String key, String content) {
        Log.d(TAG, "storing " + key);

        privateSharedPreferences.edit().putString(key, content).apply();
    }

    private String getStringFromSharedPreferences(String key) {
        Log.d(TAG, "getting " + key);

        return privateSharedPreferences.getString(key, null);
    }

    public void setAccessToken(String v) {
        this.storeStringInSharedPreferences(ACCESS_TOKEN_KEY, v);
    }

    public void setRefreshToken(String v) {
        this.storeStringInSharedPreferences(REFRESH_TOKEN_KEY, v);
    }

    public String getAccessToken() {
        return this.getStringFromSharedPreferences(ACCESS_TOKEN_KEY);
    }

    public String getRefreshToken() {
        return this.getStringFromSharedPreferences(REFRESH_TOKEN_KEY);
    }

}
