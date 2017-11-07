package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class RepositoriesResultSearchFragment extends Fragment {
    SearchResponce searchResponce;
    SearchAdapter adapter;
    List<GitHubUserResponce> userList=new ArrayList<GitHubUserResponce>();
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
       String name= MyApplication.from(getContext()).getSearchName();
        MyApplication.from(getContext()).getMyService()
                .getSearch(name, new MyService.UpdateCallback<SearchResponce>() {
                    @Override
                    public void onComplete(SearchResponce response) {
                       searchResponce=response;
                        userList.addAll(Arrays.asList(searchResponce.getUsers()));
                        adapter.addAll(userList);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailed(Throwable t) {

                    }
                });
    }
}
