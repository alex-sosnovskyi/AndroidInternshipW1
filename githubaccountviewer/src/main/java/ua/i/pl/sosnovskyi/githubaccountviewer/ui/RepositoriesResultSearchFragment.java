package ua.i.pl.sosnovskyi.githubaccountviewer.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ua.i.pl.sosnovskyi.githubaccountviewer.net.GitHubUserResponce;
import ua.i.pl.sosnovskyi.githubaccountviewer.MyApplication;
import ua.i.pl.sosnovskyi.githubaccountviewer.R;
import ua.i.pl.sosnovskyi.githubaccountviewer.net.SearchResponce;


public class RepositoriesResultSearchFragment extends Fragment {
    private SearchAdapter adapter;

    public static Fragment newInstance(String searchName) {
        Fragment fragment = new RepositoriesResultSearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", searchName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_result_fragment, container, false);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView list=(ListView) view.findViewById(R.id.search_result_list);
        adapter=new SearchAdapter(getContext(), new ArrayList<GitHubUserResponce>());
        list.setAdapter(adapter);
    }
    @Override
    public void onResume() {
        super.onResume();
        String name=  getArguments().getString("key");
        MyApplication.from(getContext()).getMyService()
                .getSearch(name, new MyService.UpdateCallback<SearchResponce>() {
                    @Override
                    public void onComplete(SearchResponce response) {
                        adapter.addAll(response.getUsers());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailed(Throwable t) {

                    }
                });
    }
}
