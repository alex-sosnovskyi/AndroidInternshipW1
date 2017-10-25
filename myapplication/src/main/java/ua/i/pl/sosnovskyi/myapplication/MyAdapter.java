package ua.i.pl.sosnovskyi.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<String> list;
    private List<Item> itemList = new ArrayList<>();

    public MyAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private Item getCurrentItem(int position) {
        return itemList.get(position);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image1);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text1);
            viewHolder.aSwitch = (Switch) convertView.findViewById(R.id.switch1);
            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }
        itemList.add(new Item(list.get(position)));
        Item current = itemList.get(position);
        viewHolder.textView.setText(current.getText());
        viewHolder.aSwitch.setChecked(getCurrentItem(position).isChacked());
        if (current.isChacked()) {
            viewHolder.textView.setVisibility(View.INVISIBLE);
            viewHolder.imageView.setVisibility(View.VISIBLE);
            PicassoHolder.show(parent.getContext(), list.get(position), viewHolder.imageView);
        } else {
            viewHolder.textView.setVisibility(View.VISIBLE);
            viewHolder.imageView.setVisibility(View.INVISIBLE);
        }
        viewHolder.aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.get(position).setItemChacked();
                notifyDataSetChanged();
            }
        });
//        viewHolder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                itemList.get(position).setItemChacked();
//                notifyDataSetChanged();
//            }
//        });
        return convertView;
    }


    class Item {
        private boolean isItemChacked = false;
        String itemName;

        public Item(String text) {
            this.itemName = text;
        }

        public String getText() {
            return itemName;
        }

        public boolean isChacked() {
            return isItemChacked;
        }

        public void setItemChacked() {
            isItemChacked = !isItemChacked;
        }
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

    private static class ViewHolder {
        ImageView imageView;
        TextView textView;
        Switch aSwitch;
    }
}
