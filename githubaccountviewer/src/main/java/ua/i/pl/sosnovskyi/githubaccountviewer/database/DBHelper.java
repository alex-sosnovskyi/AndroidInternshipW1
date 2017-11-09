package ua.i.pl.sosnovskyi.githubaccountviewer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "gitHubViewerDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tokenTable"+
                " (id integer primary key autoincrement, token text);");
        db.execSQL("create table user"+
                "(id integer primary key autoincrement, userId text, login text, avatarUrl text, userName text, created date);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
