package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import ua.i.pl.sosnovskyi.githubaccountviewer.ui.MyService;
import ua.i.pl.sosnovskyi.githubaccountviewer.ui.PreferencesLoader;


public final class MyApplication extends Application {
    private MyService myService;
    private PreferencesLoader preferencesLoader;
    private static String searchName;

    public static MyApplication from(@NonNull Context context) {
        return (MyApplication) context.getApplicationContext();
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
