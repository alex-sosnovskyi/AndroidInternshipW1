package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class MyService {
    private static final String TAG = MyService.class.getSimpleName();
    private static final String CLIENT_ID = "";
    private static final String CLIENT_SECRET = "";
    private static final String BASE_URL = "https://github.com/login/oauth/authorize";

    /**
     *
     * @return url string
     */
    public String getUrl() {
        return BASE_URL + "?scope=user:email&client_id=" + CLIENT_ID;
    }

    /**
     * Retrofit building
     * @return Retrofit
     */
    private GitHubService getGitHubService() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(new StringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(GitHubService.class);
    }

    /**
     * return user info
     * @param accessToken
     * @param updateCallback
     */
    public void getUserRepositorieInfo(final String accessToken, final UpdateCallback<GitHubUserResponce> updateCallback) {
        Call<GitHubUserResponce> call = getGitHubService().listUserInfo(accessToken);
        call.enqueue(new Callback<GitHubUserResponce>() {
            @Override
            public void onResponse(Call<GitHubUserResponce> call, Response<GitHubUserResponce> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "!response.isSuccessful");
                    return;
                }

                GitHubUserResponce body = response.body();
                if (body == null) {
                    Log.d(TAG, "body == null");
                    return;
                }
                Log.d(TAG, "onResponse");
                updateCallback.onComplete(response.body());
            }

            @Override
            public void onFailure(Call<GitHubUserResponce> call, Throwable t) {
                Log.d(TAG, "Response is failed", t);
                updateCallback.onFailed(t);
            }
        });
    }

    /**
     *  return access token
     * @param session String session id
     * @param updateCallback
     */
    public void gitHubAutorized(final String session, final UpdateCallback<String> updateCallback) {
        Call<String> call = getGitHubService().listOAuthRepos(CLIENT_ID, CLIENT_SECRET, session);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse");
                if (!response.isSuccessful()) {
                    return;
                }

                String body = response.body();
                if (body == null) {
                    return;
                }
                updateCallback.onComplete(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "Response is failed", t);
                updateCallback.onFailed(t);
            }
        });
    }

    /**
     *  return All public users repositories
     * @param updateCallback
     * @param account
     */
    public void getPublicRepositories(final UpdateCallback updateCallback, String account) {
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

                updateCallback.onComplete(response.body());
            }

            @Override
            public void onFailure(Call<List<GitHubResponce>> call, Throwable t) {
                Log.d(TAG, "onFailure");
                updateCallback.onFailed(t);
                Log.d(TAG, "Response is failed", t);
            }
        });
    }

    /**
     * Callback interface
     * @param <T>
     */
    public interface UpdateCallback<T> {
        void onComplete(T response);

        void onFailed(Throwable t);
    }

    private final class StringConverterFactory extends Converter.Factory {

        @Nullable
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            if (!type.equals(String.class)) {
                return super.responseBodyConverter(type, annotations, retrofit);
            }

            return new Converter<ResponseBody, String>() {
                @Override
                public String convert(ResponseBody value) throws IOException {
                    return new String(value.bytes());
                }
            };
        }
    }
}
