package ua.i.pl.sosnovskyi.githubaccountviewer;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {

    @GET("users/{user}/repos")
    Call<List<GitHubResponce>> listRepos(@Path("user") String user);

    @POST("https://github.com/login/oauth/access_token")
    Call<String> listOAuthRepos(
            @Query("client_id") String userID,
            @Query("client_secret") String userSecret,
            @Query("code") String session
    );

    @GET("user")
    Call<GitHubUserResponce> listUserInfo(@Query("access_token") String accessToken);

    @GET("search/users")
    Call<SearchResponce> searchResult(@Query("q") String searchStr);
}

