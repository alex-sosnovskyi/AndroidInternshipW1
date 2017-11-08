package ua.i.pl.sosnovskyi.githubaccountviewer.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ua.i.pl.sosnovskyi.githubaccountviewer.MyApplication;
import ua.i.pl.sosnovskyi.githubaccountviewer.R;
import ua.i.pl.sosnovskyi.githubaccountviewer.database.DBHelper;
import ua.i.pl.sosnovskyi.githubaccountviewer.net.PublicReposResponce;


public class RepositoriesCurrentUserListFragment extends Fragment {
    private static final String TAG = RepositoriesCurrentUserListFragment.class.getSimpleName();
    private GitHubCurrentUserRepositoriesAdapter adapter;
    private String account;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.public_repo_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = (ListView) view.findViewById(R.id.list_items);
        adapter = new GitHubCurrentUserRepositoriesAdapter(getContext(), new ArrayList<PublicReposResponce>());
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        MyService service = MyApplication.from(getContext()).getMyService();
        DBHelper dbHelper = MyApplication.from(getContext()).getDbHelper();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("user", null, null, null, null, null, null);
        String account = "";
        if (c.getCount() != 0) {
            c.moveToFirst();
            account = c.getString(c.getColumnIndex("login"));
            c.close();
            db.close();
            Log.d("Class RepositoriesCurrentUserFragment c.getCount() != 0 account=", account);
        }else{
            Log.d("Class RepositoriesCurrentUserFragment c.getCount==0 account=", account);
        }

        service.getPublicRepositories(account,
                new MyService.UpdateCallback<List<PublicReposResponce>>() {
                    @Override
                    public void onComplete(List<PublicReposResponce> response) {
                        Log.d(TAG, "ShowActivity onComplete");
                        adapter.clear();
                        adapter.addAll(response);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        Log.d(TAG, "ShowActivity onFailed");
                    }
                });
    }
}
