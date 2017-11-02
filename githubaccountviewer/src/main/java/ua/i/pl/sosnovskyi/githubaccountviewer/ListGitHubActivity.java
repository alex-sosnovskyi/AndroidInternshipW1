package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListGitHubActivity extends AppCompatActivity {

    private static final String ACCOUNT_KEY = "account";
    private MyService myService;
    private ListView itemList;
    private GitHubAdapter adapter;
    private static final String TAG = ListGitHubActivity.class.getSimpleName();
    private String account;

    /**
     * Starting activity with params
     * @param context Context
     * @param accountName String
     */
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
        adapter = new GitHubAdapter(ListGitHubActivity.this, new ArrayList<GitHubUserResponce>());
        myService = new MyService();
        itemList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myService.getPublicRepositories(new MyService.UpdateCallback<List<GitHubUserResponce>>() {
            @Override
            public void onComplete(List<GitHubUserResponce> response) {
                adapter.clear();
                adapter.addAll(response);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(Throwable throwable) {
                Toast.makeText(ListGitHubActivity.this, "Responce failed", Toast.LENGTH_LONG).show();
            }
        }, account);
    }
}
