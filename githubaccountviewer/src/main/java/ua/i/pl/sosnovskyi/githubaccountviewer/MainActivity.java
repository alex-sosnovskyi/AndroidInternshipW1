package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.content.SharedPreferences;
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
        String savedToken = MyApplication.from(MainActivity.this).getPreferensesLoader().getAccessToken();
        if (!savedToken.equals("")) {
            Log.d(TAG, "ACCESS TOKEN EXISTS " + savedToken);
            ShowActivity.startActivity(MainActivity.this);
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
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(url);
                while (matcher.find()) {
                    Log.d("Server return string", url);
                    String codeRegex = "(?<==)\\S{1,}";
                    pattern = Pattern.compile(codeRegex);
                    matcher = pattern.matcher(url);
                    while (matcher.find()) {
                        String session = matcher.group();
                        Log.d("Server return session", session);
                        MyApplication.from(MainActivity.this).getMyService().gitHubAutorized(session, new MyService.UpdateCallback<String>() {
                            @Override
                            public void onComplete(String response) {
                                Log.d(TAG, "onComplete: " + response);
                                MyApplication.from(MainActivity.this).getPreferensesLoader().setAccessToken(response);
                                ShowActivity.startActivity(MainActivity.this);
                            }

                            @Override
                            public void onFailed(Throwable t) {
                                Log.d(TAG, "Server return" + t.getMessage());
                            }
                        });

                        return false;
                    }
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        webView.loadUrl(MyApplication.from(MainActivity.this).getMyService().getUrl());
    }
}
