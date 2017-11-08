package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import ua.i.pl.sosnovskyi.githubaccountviewer.database.DBHelper;
import ua.i.pl.sosnovskyi.githubaccountviewer.ui.MyService;


public final class MyApplication extends Application {
    private MyService myService;
    private DBHelper dbHelper;

    public static MyApplication from(@NonNull Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    public DBHelper getDbHelper() {
        return dbHelper;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new DBHelper(getApplicationContext());
        myService = new MyService(getApplicationContext(), dbHelper);
    }

    public MyService getMyService() {
        return myService;
    }

}
