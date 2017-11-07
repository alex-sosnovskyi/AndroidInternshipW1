package ua.i.pl.sosnovskyi.githubaccountviewer;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

public class PreferencesLoader {

    private static final String PREFERENCES_NAME = "ua.i.pl.sosnovskyi.githubaccountviewer";
    private static final String ACCESS_TOKEN = "token";
    private static final String ACCOUNT = "account";
    private SharedPreferences sPref;

    private PreferencesLoader(Context context) {
        sPref = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
    }

    public static PreferencesLoader preferencesBuilder(Context context) {
        return new PreferencesLoader(context);
    }

    public void setAccount(String account) {
        sPref.edit()
                .putString(ACCOUNT, account).apply();
    }

    public String getAccount() {
        return sPref.getString(ACCOUNT, "");
    }

    private String tokenPatser(String responce) {
        String regex = "(?<=access_token=)(\\S{1,40})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(responce);
        matcher.find();
        return matcher.group(1);
    }

    public void setAccessToken(String responce) {
        String data = tokenPatser(responce);
        sPref.edit()
                .putString(ACCESS_TOKEN, data)
                .apply();
    }

    public String getAccessToken() {
        return sPref.getString(ACCESS_TOKEN, "");
    }
}
