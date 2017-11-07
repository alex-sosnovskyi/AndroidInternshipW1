package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class MyApplication extends Application {
    private MyService myService;
    private PreferencesLoader preferencesLoader;
    private static String searchName;

    public static MyApplication from(@NonNull Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    public void setSerchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchName() {
        return searchName;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        preferencesLoader = PreferencesLoader.preferencesBuilder(getApplicationContext());
        myService = new MyService(preferencesLoader);
    }

    public MyService getMyService() {
        return myService;
    }


    public PreferencesLoader getPreferencesLoader() {
        return preferencesLoader;
    }
}
