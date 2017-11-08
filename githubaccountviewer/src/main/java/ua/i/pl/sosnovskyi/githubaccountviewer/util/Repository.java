package ua.i.pl.sosnovskyi.githubaccountviewer.util;


import android.content.Context;

import ua.i.pl.sosnovskyi.githubaccountviewer.database.DBHelper;

public class Repository {
    private DBHelper dbHelper;
    public Repository(Context context) {
        this.dbHelper= new DBHelper(context);
    }
}
