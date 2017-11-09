package ua.i.pl.sosnovskyi.githubaccountviewer.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.i.pl.sosnovskyi.githubaccountviewer.net.GitHubUserResponce;
import ua.i.pl.sosnovskyi.githubaccountviewer.MyApplication;
import ua.i.pl.sosnovskyi.githubaccountviewer.R;
import ua.i.pl.sosnovskyi.githubaccountviewer.util.Repository;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = MyApplication.from(MainActivity.this).getRepository();

        if (repository.isToken()) {
            Log.d("Class MainActivity start showActivity whith repository.isToken=", String.valueOf(repository.isToken()));
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
                String session = sessionParser(url);
                Log.d("Server return session", session);
                final MyService myService = MyApplication.from(MainActivity.this).getMyService();

                myService.gitHubAutorized(session, new MyService.UpdateCallback<String>() {
                    @Override
                    public void onComplete(String response) {
                        fetchUserInfoAndLaunchNextActivity(response, myService);
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

    private String sessionParser(String url) {
        String codeRegex = "(?<==)\\S{1,}";
        Pattern pattern = Pattern.compile(codeRegex);
        Matcher matcher = pattern.matcher(url);

        if (!matcher.find()) {
            return url;
        }

        return matcher.group();
    }

    private void fetchUserInfoAndLaunchNextActivity(String response, MyService myService) {
        Log.d(TAG, "onComplete: " + response);
        repository.setToken(response);
        myService.getUserRepositorieInfo(new MyService.UpdateCallback<GitHubUserResponce>() {
            @Override
            public void onComplete(GitHubUserResponce response) {
                repository.setUser(response);
                ShowActivity.startActivity(MainActivity.this);
                finish();
            }

            @Override
            public void onFailed(Throwable throwable) {

            }
        });
    }
}
