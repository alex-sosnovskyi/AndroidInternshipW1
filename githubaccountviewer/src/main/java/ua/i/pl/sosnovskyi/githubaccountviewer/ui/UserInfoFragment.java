package ua.i.pl.sosnovskyi.githubaccountviewer.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import ua.i.pl.sosnovskyi.githubaccountviewer.database.DBHelper;
import ua.i.pl.sosnovskyi.githubaccountviewer.net.GitHubUserResponce;
import ua.i.pl.sosnovskyi.githubaccountviewer.MyApplication;
import ua.i.pl.sosnovskyi.githubaccountviewer.R;
import ua.i.pl.sosnovskyi.githubaccountviewer.util.Repository;


public class UserInfoFragment extends Fragment {
    private TextView id;
    private TextView login;
    private ImageView avatarUrl;
    private TextView userName;
    private TextView createdAt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.user_info_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = (TextView) view.findViewById(R.id.user_id);
        login = (TextView) view.findViewById(R.id.user_login);
        avatarUrl = (ImageView) view.findViewById(R.id.avatar_url);
        userName = (TextView) view.findViewById(R.id.user_name);
        createdAt = (TextView) view.findViewById(R.id.created_at);
    }

    @Override
    public void onResume() {
        super.onResume();
        Repository repository = MyApplication.from(getContext()).getRepository();
        List<GitHubUserResponce> userList=repository.getUser();
            id.setText(String.valueOf(userList.get(0).getId()));
            login.setText(userList.get(0).getLogin());
            Picasso.with(getContext()).load(userList.get(0).getAvatarUrl()).into(avatarUrl);
            userName.setText(userList.get(0).getName());
            createdAt.setText(String.valueOf(userList.get(0).getCreatedAt()));


    }
}
