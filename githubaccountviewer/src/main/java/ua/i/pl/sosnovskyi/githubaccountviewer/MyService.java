package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class MyService {
    private static final String TAG = ListGitHubActivity.class.getSimpleName();
    private final GitHubAdapter adapter;
private final String account;
    MyService(GitHubAdapter adapter, String account) {
        this.adapter = adapter;
        this.account=account;
    }


    private GitHubService getGitHubService() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(GitHubService.class);
    }

    public void doPost() {

    }

    public void getPublicRepositories() {

        Call<List<GitHubResponce>> call = getGitHubService().listRepos(account);
        call.enqueue(new Callback<List<GitHubResponce>>() {
            @Override
            public void onResponse(Call<List<GitHubResponce>> call, @NonNull Response<List<GitHubResponce>> response) {
                Log.d(TAG, "onResponse");
                if (!response.isSuccessful()) {
                    return;
                }

                List<GitHubResponce> body = response.body();
                if (body == null) {
                    return;
                }

                adapter.clear();
                adapter.addAll(body);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GitHubResponce>> call, Throwable t) {
                Log.d(TAG, "onFailure");

                Log.d(TAG, "Response is failed", t);
            }
        });
    }
}
