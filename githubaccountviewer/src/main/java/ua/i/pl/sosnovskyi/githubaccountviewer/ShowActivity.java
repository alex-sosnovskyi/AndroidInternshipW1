package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ShowActivity extends AppCompatActivity {
    private GitHubAdapter adapter;
    private static final String TAG = ShowActivity.class.getSimpleName();
    private String accessToken;

    public static void startActivity(@NonNull final Context context) {
        Intent intent = new Intent(context, ShowActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ListView listView = (ListView) findViewById(R.id.item_list);
        Log.d(TAG, "ShowActivuty on the top");
        accessToken = MyApplication.from(ShowActivity.this).getPreferensesLoader().getAccessToken();
        adapter = new GitHubAdapter(ShowActivity.this, new ArrayList<GitHubUserResponce>());
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.from(ShowActivity.this).getMyService().getUserRepositorieInfo(accessToken, new MyService.UpdateCallback<GitHubUserResponce>() {
            @Override
            public void onComplete(GitHubUserResponce response) {
                Log.d(TAG, "ShowActivity onComplete");
                adapter.clear();
                adapter.addAll(response);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(Throwable throwable) {
                Log.d(TAG, "ShowActivity onFailed");
                Toast.makeText(ShowActivity.this, "Responce failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
