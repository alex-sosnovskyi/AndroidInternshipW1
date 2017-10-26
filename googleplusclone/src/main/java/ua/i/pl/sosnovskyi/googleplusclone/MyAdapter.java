package ua.i.pl.sosnovskyi.googleplusclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Sosnovskyi on 26.10.2017.
 */

public class MyAdapter extends BaseAdapter {
    private List<MyItem> itemList = new ArrayList();

    public MyAdapter(List<MyItem> itemList) {
        if (itemList == null) {
            throw new NullPointerException("No data");
        }
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public MyItem getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageUserView = (ImageView) convertView.findViewById(R.id.user_img);
            viewHolder.textUserNameView = (TextView) convertView.findViewById(R.id.user_name);
            viewHolder.textNewsNameView = (TextView) convertView.findViewById(R.id.item_name);
            viewHolder.textLengthView = (TextView) convertView.findViewById(R.id.length);
            viewHolder.textDescriptionView = (TextView) convertView.findViewById(R.id.description);
            viewHolder.imagePhotoView = (ImageView) convertView.findViewById(R.id.photo);
            viewHolder.textAddLikesView = (TextView) convertView.findViewById(R.id.add_like);
            viewHolder.textCurrentLikesView = (TextView) convertView.findViewById(R.id.current_likes);
            viewHolder.imageNextView = (ImageView) convertView.findViewById(R.id.next);
            viewHolder.imageCommentView = (ImageView) convertView.findViewById(R.id.comment);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MyItem currentItem = itemList.get(position);

        PicassoHolder.show(parent.getContext(), currentItem.pictureUrl,  viewHolder.imageUserView);
        viewHolder.textUserNameView.setText(currentItem.userName);
        viewHolder.textNewsNameView .setText(currentItem.newsName);
        viewHolder.textLengthView .setText(currentItem.length);
        viewHolder.textDescriptionView .setText(currentItem.description);
        PicassoHolder.show(parent.getContext(), currentItem.photoUrl,   viewHolder.imagePhotoView);
//        viewHolder.textAddLikesView .setText(currentItem.);
        viewHolder.textCurrentLikesView .setText(currentItem.currentLikes);
//        viewHolder.imageNextView =
//        viewHolder.imageCommentView =
        return convertView;
    }

    private static class ViewHolder {
        ImageView imageUserView;
        TextView textUserNameView;
        TextView textNewsNameView;
        TextView textLengthView;
        TextView textDescriptionView;
        ImageView imagePhotoView;
        TextView textAddLikesView;
        TextView textCurrentLikesView;
        ImageView imageNextView;
        ImageView imageCommentView;
    }
    private static class PicassoHolder {

        public static void show(Context context, String url, ImageView view) {
            if (context == null) {
                throw new NullPointerException("context null");
            }
            Picasso.with(context).load(url).into(view, new Callback() {
                @Override
                public void onSuccess() {
                    System.err.print("Image downloaded");
                }

                @Override
                public void onError() {
                    System.err.print("Image did not downloaded");
                }
            });
        }
    }
}
