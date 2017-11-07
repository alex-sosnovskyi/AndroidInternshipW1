package ua.i.pl.sosnovskyi.githubaccountviewer;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;


public class ShowActivity extends AppCompatActivity {
    private static final String TAG = ShowActivity.class.getSimpleName();
    private static boolean PUBLIC = false;
    private static boolean INFO = false;
    private static boolean SEARCH = false;

    public static void startActivity(@NonNull final Context context) {
        Intent intent = new Intent(context, ShowActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Log.d(TAG, "ShowActivuty on the top");
        final ToggleButton puplicRepo = (ToggleButton) findViewById(R.id.public_repo);
        final ToggleButton userInfo = (ToggleButton) findViewById(R.id.user_info);
        final ToggleButton search = (ToggleButton) findViewById(R.id.search);
        PUBLIC = true;

        MyApplication.from(ShowActivity.this).getMyService()
                .getUserRepositorieInfo(new MyService.UpdateCallback<GitHubUserResponce>() {
                    @Override
                    public void onComplete(GitHubUserResponce response) {
                        Log.d(TAG, "ShowActivity onComplete");
                        MyApplication.from(ShowActivity.this).getPreferencesLoader().setAccount(response.getLogin());
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        Log.d(TAG, "ShowActivity onFailed");
                        Toast.makeText(ShowActivity.this, "Responce failed", Toast.LENGTH_LONG).show();
                    }
                });
        puplicRepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PUBLIC) {
                    puplicRepo.setChecked(true);
                    return;
                }
                userInfo.setChecked(false);
                search.setChecked(false);
                PUBLIC = true;
                SEARCH = false;
                INFO = false;
                onResume();
            }
        });
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (INFO) {
                    userInfo.setChecked(true);
                    return;
                }
                puplicRepo.setChecked(false);
                search.setChecked(false);
                INFO = true;
                PUBLIC = false;
                SEARCH = false;
                onResume();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SEARCH) {
                    search.setChecked(true);
                    return;
                }
                puplicRepo.setChecked(false);
                userInfo.setChecked(false);
                SEARCH = true;
                PUBLIC = false;
                INFO = false;

                onResume();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PUBLIC) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_show, new RepositoriesCurrentUserListFragment(), "repositoriesList")
                    .commit();

        }
        if (INFO) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_show, new UserInfoFragment(), "currentUserInfo")
                    .commit();
            return;
        }
        if (SEARCH) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_show, new RepositoriesSearchFragment(), "search")
                    .commit();
            return;
        }
    }
}
