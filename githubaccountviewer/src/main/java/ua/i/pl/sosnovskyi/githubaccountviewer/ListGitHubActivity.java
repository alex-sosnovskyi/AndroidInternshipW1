package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class ListGitHubActivity extends AppCompatActivity {

    private static final String ACCOUNT_KEY = "account";
    private String clientId = "797c34103a56609a3b70";
    private MyService myService;
    private ListView itemList;
    private GitHubAdapter adapter;
    private static final String TAG = ListGitHubActivity.class.getSimpleName();
    private String account;

    public static void startActivityWithAccountName(@NonNull final Context context, @NonNull final String accountName) {
        Intent intent = new Intent(context, ListGitHubActivity.class);
        intent.putExtra(ACCOUNT_KEY, String.valueOf(accountName));
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_list);
        Intent intent = getIntent();
        account = intent.getStringExtra(ACCOUNT_KEY);
        Log.d(TAG, account);
        itemList = (ListView) findViewById(R.id.itemsList);
        adapter = new GitHubAdapter(ListGitHubActivity.this, new ArrayList<GitHubResponce>());
        myService = new MyService(adapter, account);
        itemList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myService.getPublicRepositories();
    }
}
