package ua.i.pl.sosnovskyi.googleplusclone;


import android.content.Context;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends ArrayAdapter<MyItem> {

    private static final String TAG = MyAdapter.class.getSimpleName();


    public MyAdapter(@NonNull Context context, @NonNull List<MyItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MyItem currentItem = getItem(position);

        viewHolder.textUserNameView.setText(currentItem.getUserName());
        viewHolder.textNewsNameView.setText(currentItem.getNewsName());
        viewHolder.textLengthView.setText(currentItem.getLength());
        viewHolder.textDescriptionView.setText(currentItem.getDescription());
        viewHolder.textCurrentLikesView.setText(String.valueOf(currentItem.getCurrentLikes()));
        PicassoHolder.show(parent.getContext(), currentItem.getPictureUrl(), viewHolder.imageUserView);
        PicassoHolder.show(parent.getContext(), currentItem.getPhotoUrl(), viewHolder.imagePhotoView);

        return convertView;
    }

    private static class ViewHolder {
        private final ImageView imageUserView;
        private final TextView textUserNameView;
        private final TextView textNewsNameView;
        private final TextView textLengthView;
        private final TextView textDescriptionView;
        private final ImageView imagePhotoView;
        private final TextView textCurrentLikesView;

        private ViewHolder(View convertView) {
            imageUserView = (ImageView) convertView.findViewById(R.id.user_img);
            textUserNameView = (TextView) convertView.findViewById(R.id.user_name);
            textNewsNameView = (TextView) convertView.findViewById(R.id.item_name);
            textLengthView = (TextView) convertView.findViewById(R.id.length);
            textDescriptionView = (TextView) convertView.findViewById(R.id.description);
            imagePhotoView = (ImageView) convertView.findViewById(R.id.photo);
            textCurrentLikesView = (TextView) convertView.findViewById(R.id.current_likes);
        }
    }


    private static class PicassoHolder {

        private static void show(Context context, String url, ImageView view) {
            Picasso.with(context).load(url).into(view, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "onSuccess: ");
                }

                @Override
                public void onError() {
                    Log.d(TAG, "onError: ");
                }
            });
        }
    }
}
