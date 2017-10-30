package ua.i.pl.sosnovskyi.googleplusclone;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int EDIT_NEW_REQUEST_CODE = 1;
    private static final int DELETE_AND_EDIT = 1;
    private List<MyItem> itemList;
    private MyAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        itemList = new ArrayList<>();
        itemList.add(new MyItem("https://images.pexels.com/photos/407035/model-face-beautiful-black-and-white-407035.jpeg?w=940&h=650&auto=compress&cs=tinysrgb",
                "Justin Oliver", "Snorkel Advanture", "5m", "On our way to Kaua!!! What a beautiful day!!!",
                "https://images.pexels.com/photos/132037/pexels-photo-132037.jpeg?w=940&h=650&auto=compress&cs=tinysrgb", 32));
        itemList.add(new MyItem("https://images.pexels.com/photos/407035/model-face-beautiful-black-and-white-407035.jpeg?w=940&h=650&auto=compress&cs=tinysrgb",
                "Justin Oliver", "Snorkel Advanture", "5m", "Seaside",
                "https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?w=940&h=650&auto=compress&cs=tinysrgb", 32));
        itemList.add(new MyItem("https://images.pexels.com/photos/407035/model-face-beautiful-black-and-white-407035.jpeg?w=940&h=650&auto=compress&cs=tinysrgb",
                "Justin Oliver", "Snorkel Advanture", "5m", "On our way to Kaua!!! What a beautiful day!!!",
                "https://res.cloudinary.com/twenty20/private_images/t_watermark-criss-cross-10/v1447381412000/photosp/cf6f1fcb-52e6-4b8e-9066-afcc7cf25a03/stock-photo-sky-beach-blue-fun-ocean-swing-california-san-francisco-vacation-cf6f1fcb-52e6-4b8e-9066-afcc7cf25a03.jpg", 32));
        itemList.add(new MyItem("https://images.pexels.com/photos/407035/model-face-beautiful-black-and-white-407035.jpeg?w=940&h=650&auto=compress&cs=tinysrgb",
                "Justin Oliver", "Snorkel Advanture", "5m", "On our way to Kaua!!! What a beautiful day!!!",
                "https://images.pexels.com/photos/132037/pexels-photo-132037.jpeg?w=940&h=650&auto=compress&cs=tinysrgb", 32));
        itemList.add(new MyItem("https://images.pexels.com/photos/407035/model-face-beautiful-black-and-white-407035.jpeg?w=940&h=650&auto=compress&cs=tinysrgb",
                "Justin Oliver", "Snorkel Advanture", "5m", "On our way to Kaua!!! What a beautiful day!!!",
                "https://images.pexels.com/photos/132037/pexels-photo-132037.jpeg?w=940&h=650&auto=compress&cs=tinysrgb", 32));
        itemList.add(new MyItem("https://images.pexels.com/photos/407035/model-face-beautiful-black-and-white-407035.jpeg?w=940&h=650&auto=compress&cs=tinysrgb",
                "Justin Oliver", "Snorkel Advanture", "5m", "On our way to Kaua!!! What a beautiful day!!!",
                "https://images.pexels.com/photos/132037/pexels-photo-132037.jpeg?w=940&h=650&auto=compress&cs=tinysrgb", 32));

        ListView listView = (ListView) findViewById(R.id.item_list);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final MyItem itemAtPosition = (MyItem) parent.getItemAtPosition(position);
              //  showDeleteDialog(DELETE_AND_EDIT, itemAtPosition, position, context);
                FragmentManager manager = getSupportFragmentManager();
                MyDialog myDialogFragment = new MyDialog();
                myDialogFragment.setPosition(position);
                myDialogFragment.show(manager, "dialog");
                return true;
            }
        });

        adapter = new MyAdapter(this, itemList);
        listView.setAdapter(adapter);

        FloatingActionButton fBtn = (FloatingActionButton) findViewById(R.id.fab);
        fBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditActivity.class);
                startActivityForResult(intent, EDIT_NEW_REQUEST_CODE);
            }
        });

    }


public void EditClicked(int position){
    MyItem itemToEdit = itemList.get(position);
    Intent intent = new Intent(context, EditActivity.class);
    intent.putExtra("userImgUrl", itemToEdit.getPictureUrl());
    intent.putExtra("userName", itemToEdit.getUserName());
    intent.putExtra("itemName",itemToEdit.getNewsName());
    intent.putExtra("length", itemToEdit.getLength());
    intent.putExtra("describe", itemToEdit.getDescription());
    intent.putExtra("photoUrl", itemToEdit.getPhotoUrl());
    startActivityForResult(intent, EDIT_NEW_REQUEST_CODE);

}
public void DeleteClicked(int position){
    itemList.remove(position);
    adapter.notifyDataSetChanged();
}
//    private void showDeleteDialog(final int id, final MyItem itemAtPosition, int position, Context context) {
//
//
//        itemList.remove(position);
//    }

//    @Nullable
//    @Override
//    protected Dialog onCreateDialog(int id) {
//
//        switch (id) {
//            case DELETE_AND_EDIT: {
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle(getString(R.string.action_dialog))
//                        .setMessage(getString(R.string.action_dialog_content))
//                        .setCancelable(false)
//                        .setPositiveButton(getString(R.string.delete_action),
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog,
//                                                        int id) {
//
//                                        dialog.cancel();
//                                    }
//                                })
//                        .setNeutralButton(R.string.edit_selected,
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog,
//                                                        int id) {
//
//                                        dialog.cancel();
//                                    }
//                                })
//                        .setNegativeButton(R.string.cancel_dialog,
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog,
//                                                        int id) {
//                                        dialog.cancel();
//                                    }
//                                });
//                return builder.create();
//            }
//            default:
//                return null;
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == EDIT_NEW_REQUEST_CODE) || (resultCode == RESULT_OK)) {

            MyItem newItem = new MyItem(data.getStringExtra("responceUserImgUrl"),
                    data.getStringExtra("responceUserName"),
                    data.getStringExtra("responceItemName"),
                    data.getStringExtra("responceLength"),
                    data.getStringExtra("responceDescribe"),
                    data.getStringExtra("resposponcePhotoUrl"), 0);
            itemList.add(newItem);

            adapter.notifyDataSetChanged();
        }


    }
}
