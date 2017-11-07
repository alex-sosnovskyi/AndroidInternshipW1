package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String savedToken = MyApplication.from(MainActivity.this).getPreferencesLoader().getAccessToken();
        if (!savedToken.equals("")) {
            Log.d(TAG, "ACCESS TOKEN EXISTS " + savedToken);
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
                String regex = "http:\\/\\/callback.url";

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
                MyApplication.from(MainActivity.this).getMyService().gitHubAutorized(session, new MyService.UpdateCallback<String>() {
                    @Override
                    public void onComplete(String response) {
                        Log.d(TAG, "onComplete: " + response);
                        MyApplication.from(MainActivity.this).getPreferencesLoader().setAccessToken(response);
                        MyApplication.from(MainActivity.this).getMyService()
                                .getUserRepositorieInfo(new MyService.UpdateCallback<GitHubUserResponce>() {
                                    @Override
                                    public void onComplete(GitHubUserResponce response) {
                                        MyApplication.from(MainActivity.this
                                        ).getPreferencesLoader().setAccount(response.getLogin());

                                    }

                                    @Override
                                    public void onFailed(Throwable throwable) {

                                    }
                                });
                        ShowActivity.startActivity(MainActivity.this);
                        finish();
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
}
