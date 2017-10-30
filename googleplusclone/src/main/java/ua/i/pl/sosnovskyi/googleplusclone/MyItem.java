package ua.i.pl.sosnovskyi.googleplusclone;

import android.os.Parcel;
import android.os.Parcelable;

class MyItem implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pictureUrl);
        dest.writeString(userName);
        dest.writeString(newsName);
        dest.writeString(length);
        dest.writeString(description);
        dest.writeString(photoUrl);
        dest.writeInt(currentLikes);
    }

    private MyItem(Parcel in) {
        pictureUrl = in.readString();
        userName = in.readString();
        newsName = in.readString();
        length = in.readString();
        description = in.readString();
        photoUrl = in.readString();
        currentLikes = in.readInt();
    }

    public static final Creator<MyItem> CREATOR = new Creator<MyItem>() {
        @Override
        public MyItem createFromParcel(Parcel in) {
            return new MyItem(in);
        }

        @Override
        public MyItem[] newArray(int size) {
            return new MyItem[size];
        }
    };
}
