package ua.i.pl.sosnovskyi.githubaccountviewer.ui;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.ToggleButton;

import ua.i.pl.sosnovskyi.githubaccountviewer.R;


public class ShowActivity extends AppCompatActivity implements Callback {
    private static final String TAG = ShowActivity.class.getSimpleName();
    private TAB activeTab;

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
        activeTab = TAB.PUBLIC;
        puplicRepo.setChecked(true);

        puplicRepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puplicRepo.setChecked(true);
                userInfo.setChecked(false);
                search.setChecked(false);
                if (activeTab == TAB.PUBLIC) {
                    return;
                }
                updateTabs(TAB.PUBLIC);
            }
        });
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puplicRepo.setChecked(false);
                search.setChecked(false);
                userInfo.setChecked(true);
                if (activeTab == TAB.INFO) {
                    return;
                }
                updateTabs(TAB.INFO);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setChecked(true);
                puplicRepo.setChecked(false);
                userInfo.setChecked(false);
                if (activeTab == TAB.SEARCH) {
                    return;
                }
                updateTabs(TAB.SEARCH);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateTabs(activeTab);
    }

    private void updateTabs(TAB nextActiveTab) {

        switch (nextActiveTab) {
            case PUBLIC: {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_show, new RepositoriesCurrentUserListFragment(), "repositoriesList")
                        .commit();
                break;
            }
            case INFO: {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_show, new UserInfoFragment(), "currentUserInfo")
                        .commit();
                break;
            }
            case SEARCH: {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_show, new RepositoriesSearchFragment(), "search")
                        .commit();
                break;
            }
        }

        activeTab = nextActiveTab;
    }

    @Override
    public void showSearchResultFragment(String arg) {
        Fragment fragment = RepositoriesResultSearchFragment.newInstance(arg);
        getSupportFragmentManager().beginTransaction()
                .addToBackStack("search")
                .replace(R.id.fragment_show, fragment, "search_result")
                .commit();
    }

    private enum TAB {
        PUBLIC,
        INFO,
        SEARCH
    }

}
