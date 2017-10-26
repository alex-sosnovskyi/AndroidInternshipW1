package ua.i.pl.sosnovskyi.googleplusclone;

/**
 * Created by Alex Sosnovskyi on 26.10.2017.
 */

class MyItem {
    String pictureUrl;
    String userName;
    String newsName;
    String length;
    String description;
    String photoUrl;
    int currentLikes;

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
}
