package ua.i.pl.sosnovskyi.githubaccountviewer.util;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.i.pl.sosnovskyi.githubaccountviewer.database.DBHelper;
import ua.i.pl.sosnovskyi.githubaccountviewer.net.GitHubUserResponce;

public class Repository {
    private DBHelper dbHelper;

    public Repository(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    private String tokenParser(String responce) {
        String regex = "(?<=access_token=)(\\S{1,40})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(responce);
        matcher.find();
        return matcher.group(1);
    }

    public boolean isToken() {
        boolean result;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("tokenTable", null, null, null, null, null, null);
        result = c.getCount() != 0;
        c.close();
        db.close();
        return result;
    }

    public void setToken(String arg) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("tokenTable", null, null, null, null, null, null);
        if (c.getCount() == 0) {
            ContentValues cv = new ContentValues();
            String token = tokenParser(arg);
            cv.put("token", token);
            db.insert("tokenTable", null, cv);
            c.close();
            db.close();
        }
    }

    public void setUser(GitHubUserResponce user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("user", null, null, null, null, null, null);
        if (c.getCount() == 0) {
            ContentValues cv = new ContentValues();
            cv.put("userId", String.valueOf(user.getId()));
            cv.put("login", user.getLogin());
            cv.put("avatarUrl", user.getAvatarUrl());
            cv.put("userName", user.getName());
            cv.put("created", String.valueOf(user.getCreatedAt()));
            db.insert("user", null, cv);
            c.close();
            db.close();
        }
    }

    public String getToken() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("tokenTable", null, null, null, null, null, null);
        if (c.getCount() == 0) {
            Log.d("Class MyService method getUserRepositorieInfo", "Table tokenTable is empty");
        }
        c.moveToFirst();
        String token = c.getString(c.getColumnIndex("token"));
        Log.d("Class MyService method getUserRepositorieInfo", token);
        c.close();
        db.close();
        return token;
    }

    public List<GitHubUserResponce> getUser() {
        GitHubUserResponce user = new GitHubUserResponce();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("user", null, null, null, null, null, null);
        List<GitHubUserResponce> result = new ArrayList<>();
        if (c.getCount() != 0) {
            c.moveToFirst();
            do{
            user.setId(Long.valueOf(c.getString(c.getColumnIndex("userId"))));
            user.setLogin(c.getString(c.getColumnIndex("login")));
            user.setAvatarUrl(c.getString(c.getColumnIndex("avatarUrl")));
            Log.d("Repository date string", c.getString(c.getColumnIndex("created")));
            SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM HH:mm:ss z yyyy",
                    Locale.ENGLISH);
            try {
                user.setCreatedAt(formatter.parse(c.getString(c.getColumnIndex("created"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            user.setName(c.getString(c.getColumnIndex("userName")));
                result.add(user);
        }while(c.moveToNext());
            c.close();
            db.close();
        }
        return result;
    }
}
