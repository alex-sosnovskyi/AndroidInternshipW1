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

import ua.i.pl.sosnovskyi.githubaccountviewer.database.DBHelper;
import ua.i.pl.sosnovskyi.githubaccountviewer.net.GitHubUserResponce;
import ua.i.pl.sosnovskyi.githubaccountviewer.MyApplication;
import ua.i.pl.sosnovskyi.githubaccountviewer.R;


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
        DBHelper dbHelper = MyApplication.from(getContext()).getDbHelper();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("user", null, null, null, null, null, null);
        if (c.getCount() != 0) {
            c.moveToFirst();
            id.setText(String.valueOf(c.getString(c.getColumnIndex("userId"))));
            login.setText(c.getString(c.getColumnIndex("login")));
            Picasso.with(getContext()).load(c.getString(c.getColumnIndex("avatarUrl"))).into(avatarUrl);
            userName.setText(c.getString(c.getColumnIndex("userName")));
            createdAt.setText(String.valueOf(c.getString(c.getColumnIndex("created"))));

        }
        c.close();
        db.close();
//        MyApplication.from(getContext()).getMyService()
//                .getUserRepositorieInfo(new MyService.UpdateCallback<GitHubUserResponce>() {
//                    @Override
//                    public void onComplete(GitHubUserResponce response) {
//                        id.setText(String.valueOf(response.getId()));
//                        login.setText(response.getLogin());
//                        Picasso.with(getContext()).load(response.getAvatarUrl()).into(avatarUrl);
//                        userName.setText(response.getName());
//                        createdAt.setText(String.valueOf(response.getCreatedAt()));
//                    }
//
//                    @Override
//                    public void onFailed(Throwable throwable) {
//                        Toast.makeText(getActivity(), "Responce failed", Toast.LENGTH_LONG).show();
//                    }
//                });
    }
}
