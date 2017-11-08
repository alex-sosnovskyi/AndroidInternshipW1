package ua.i.pl.sosnovskyi.githubaccountviewer.ui;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.i.pl.sosnovskyi.githubaccountviewer.database.DBHelper;
import ua.i.pl.sosnovskyi.githubaccountviewer.net.GitHubUserResponce;
import ua.i.pl.sosnovskyi.githubaccountviewer.MyApplication;
import ua.i.pl.sosnovskyi.githubaccountviewer.R;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper =MyApplication.from(MainActivity.this).getDbHelper();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("tokenTable", null, null, null, null, null, null);

        if (c.getCount()!=0) {
            Log.d("Class MainActivity start showActivity whith c.getCount=", String.valueOf(c.getCount()));
            c.close();
            db.close();
            ShowActivity.startActivity(MainActivity.this);
            finish();
            return;
        }

        setContentView(R.layout.autorization_view);
        final WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(TAG, url);

                if (!url.startsWith("http://callback.url")) {
                    return super.shouldOverrideUrlLoading(view, url);
                }

                Log.d("Server return string", url);
                String codeRegex = "(?<==)\\S{1,}";

                Pattern pattern = Pattern.compile(codeRegex);
                Matcher matcher = pattern.matcher(url);

                if (!matcher.find()) {
                    return super.shouldOverrideUrlLoading(view, url);
                }

                String session = matcher.group();
                Log.d("Server return session", session);
                final MyService myService = MyApplication.from(MainActivity.this).getMyService();


                myService.gitHubAutorized(session, new MyService.UpdateCallback<String>() {
                    @Override
                    public void onComplete(String response) {
                        fetchUserInfoAndLaunchNextActivity(response,  myService);
                    }

                    @Override
                    public void onFailed(Throwable t) {
                        Log.d(TAG, "Server return" + t.getMessage());
                    }
                });

                return false;
            }
        });

        webView.loadUrl(MyApplication.from(MainActivity.this).getMyService().getUrl());


    }
    private String tokenParser(String responce) {
        String regex = "(?<=access_token=)(\\S{1,40})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(responce);
        matcher.find();
        return matcher.group(1);
    }

    private void fetchUserInfoAndLaunchNextActivity(String response,  MyService myService) {
        Log.d(TAG, "onComplete: " + response);

         SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("tokenTable", null, null, null, null, null, null);
        if (c.getCount() == 0) {
            ContentValues cv = new ContentValues();
            String token=tokenParser(response);
            Log.d(TAG, token);
            cv.put("token", token);
            db.insert("tokenTable",null, cv);
            c.close();
            db.close();
        }
        myService.getUserRepositorieInfo(new MyService.UpdateCallback<GitHubUserResponce>() {
            @Override
            public void onComplete(GitHubUserResponce response) {
                SQLiteDatabase  db=dbHelper.getWritableDatabase();
                Cursor c = db.query("user", null, null, null, null, null, null);
                if(c.getCount()==0){
                    ContentValues cv=new ContentValues();
                    cv.put("userId", String.valueOf(response.getId()));
                    cv.put("login", response.getLogin());
                    cv.put("avatarUrl", response.getAvatarUrl());
                    cv.put("userName", response.getName());
                    cv.put("created", String.valueOf(response.getCreatedAt()));
                    db.insert("user", null, cv);
                    c.close();
                    db.close();
                }
                ShowActivity.startActivity(MainActivity.this);
                finish();
            }

            @Override
            public void onFailed(Throwable throwable) {
//                                        Toast.makeText()
            }
        });
    }
}
