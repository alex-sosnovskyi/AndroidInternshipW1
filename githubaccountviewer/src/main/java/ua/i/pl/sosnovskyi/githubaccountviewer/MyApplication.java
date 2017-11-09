package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import ua.i.pl.sosnovskyi.githubaccountviewer.database.DBHelper;
import ua.i.pl.sosnovskyi.githubaccountviewer.ui.MyService;
import ua.i.pl.sosnovskyi.githubaccountviewer.util.Repository;


public final class MyApplication extends Application {
    private MyService myService;
    private Repository repository;

    public static MyApplication from(@NonNull Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    public Repository getRepository() {
        return repository;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        repository  = new Repository(getApplicationContext());
        myService = new MyService(getApplicationContext(), repository);
    }

    public MyService getMyService() {
        return myService;
    }

}
