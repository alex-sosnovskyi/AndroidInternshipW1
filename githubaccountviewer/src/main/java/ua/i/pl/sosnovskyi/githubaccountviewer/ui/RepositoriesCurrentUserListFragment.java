package ua.i.pl.sosnovskyi.githubaccountviewer.ui;

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

//       account = MyApplication.from(getContext()).getPreferencesLoader().getAccount();
        adapter = new GitHubCurrentUserRepositoriesAdapter(getContext(), new ArrayList<PublicReposResponce>());
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        MyService service = MyApplication.from(getContext()).getMyService();
        String account = MyApplication.from(getContext()).getPreferencesLoader().getAccount();
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
