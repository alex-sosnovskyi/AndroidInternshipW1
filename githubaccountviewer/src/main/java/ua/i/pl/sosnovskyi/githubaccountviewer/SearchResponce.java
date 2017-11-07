package ua.i.pl.sosnovskyi.githubaccountviewer;


import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class SearchResponce {
    @SerializedName("total_count")
    private long totalCount;
    @SerializedName("items")
    private GitHubUserResponce[] users;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public GitHubUserResponce[] getUsers() {
       // return users;
        return Arrays.copyOf(users, users.length);
    }

    public void setUsers(GitHubUserResponce[] users) {
        this.users = users;
    }
}
