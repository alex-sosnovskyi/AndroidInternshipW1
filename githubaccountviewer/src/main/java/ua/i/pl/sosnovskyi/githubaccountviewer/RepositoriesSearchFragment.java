package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;




public class RepositoriesSearchFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.search_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView searchText=(TextView) view.findViewById(R.id.search_string);
        final Button searchButton=(Button) view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(searchText.getText())){
                    return;
                }
                if(searchText.getText().equals(" ")){
                    return;
                }
                MyApplication.from(getContext()).setSerchName(String.valueOf(searchText.getText()));
               getFragmentManager().beginTransaction()
                       .addToBackStack("search")
                       .replace(R.id.fragment_show, new RepositoriesResultSearchFragment(), "search_result")
                       .commit();
            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();

    }
}
