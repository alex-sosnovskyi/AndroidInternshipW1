package ua.i.pl.sosnovskyi.githubaccountviewer;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<GitHubResponce>> listRepos(@Path("user") String user);

    @POST("/login/oauth/{code}")
    Call<List<GitHubResponce>> listOAuthRepos(@Path("code") String user);
}
