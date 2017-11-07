package ua.i.pl.sosnovskyi.githubaccountviewer;

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


public class RepositoriesCurrentUserListFragment extends Fragment {
    private String account;
    private static final String TAG = RepositoriesCurrentUserListFragment.class.getSimpleName();
    private GitHubCurrentUserRepositoriesAdapter adapter;


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

        account = MyApplication.from(getContext()).getPreferencesLoader().getAccount();
        adapter = new GitHubCurrentUserRepositoriesAdapter(getContext(), new ArrayList<GitHubResponce>());
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        MyService service = MyApplication.from(getContext()).getMyService();
        String account = MyApplication.from(getContext()).getPreferencesLoader().getAccount();
        service.getPublicRepositories(account,
                new MyService.UpdateCallback<List<GitHubResponce>>() {
                    @Override
                    public void onComplete(List<GitHubResponce> response) {
                        Log.d(TAG, "ShowActivity onComplete");
                        adapter.clear();
                        adapter.addAll(response);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        Log.d(TAG, "ShowActivity onFailed");
                        Toast.makeText(getContext(), "Responce failed", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
