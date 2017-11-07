package ua.i.pl.sosnovskyi.githubaccountviewer;

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


public class UserInfoFragment extends Fragment {
    private TextView id;
    TextView login;
    ImageView avatarUrl;
    TextView userName;
    TextView createdAt;

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
        MyApplication.from(getContext()).getMyService()
                .getUserRepositorieInfo(new MyService.UpdateCallback<GitHubUserResponce>() {
                    @Override
                    public void onComplete(GitHubUserResponce response) {
                        id.setText(String.valueOf(response.getId()));
                        login.setText(response.getLogin());
                        Picasso.with(getContext()).load(response.getAvatarUrl()).into(avatarUrl);
                        userName.setText(response.getName());
                        createdAt.setText(String.valueOf(response.getCreatedAt()));

                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        Toast.makeText(getActivity(), "Responce failed", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
