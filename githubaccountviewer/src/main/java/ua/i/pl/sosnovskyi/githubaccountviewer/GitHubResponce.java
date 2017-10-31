package ua.i.pl.sosnovskyi.githubaccountviewer;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

class GitHubResponce {
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("created_at")
    private Date createdAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
