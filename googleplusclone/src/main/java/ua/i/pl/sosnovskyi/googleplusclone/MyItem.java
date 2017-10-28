package ua.i.pl.sosnovskyi.googleplusclone;

class MyItem {
    private String pictureUrl;
    private String userName;
    private String newsName;
    private String length;
    private String description;
    private String photoUrl;
    private int currentLikes;

    public MyItem(String pictureUrl, String userName, String newsName, String length,
                  String description, String photoUrl, int currentLikes) {
        this.pictureUrl = pictureUrl;
        this.userName = userName;
        this.newsName = newsName;
        this.length = length;
        this.description = description;
        this.photoUrl = photoUrl;
        this.currentLikes = currentLikes;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getCurrentLikes() {
        return currentLikes;
    }

    public void setCurrentLikes(int currentLikes) {
        this.currentLikes = currentLikes;
    }
}
