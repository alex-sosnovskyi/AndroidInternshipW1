package ua.i.pl.sosnovskyi.googleplusclone;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_REQUEST_CODE = 1;
    private static final int EDIT_REQUEST_CODE = 2;

    private final String[] dialogItemNamesArray = {"Edit", "Delete"};

    private List<MyItem> itemList;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.action_dialog))
                        .setItems(dialogItemNamesArray, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (dialogItemNamesArray[which]) {
                                    case "Edit": {
                                        editClicked(position);
                                        break;
                                    }
                                    case "Delete": {
                                        deleteClicked(position);
                                        break;
                                    }
                                }
                            }

                        });
                builder.show();
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
                startActivityForResult(intent, ADD_REQUEST_CODE);
            }
        });

    }


    public void editClicked(int position) {
        MyItem itemToEdit = itemList.get(position);
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("userImgUrl", itemToEdit.getPictureUrl());
        intent.putExtra("userName", itemToEdit.getUserName());
        intent.putExtra("itemName", itemToEdit.getNewsName());
        intent.putExtra("length", itemToEdit.getLength());
        intent.putExtra("describe", itemToEdit.getDescription());
        intent.putExtra("photoUrl", itemToEdit.getPhotoUrl());
        intent.putExtra("position", position);
        startActivityForResult(intent, EDIT_REQUEST_CODE);
    }

    public void deleteClicked(int position) {
        itemList.remove(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case ADD_REQUEST_CODE: {
                MyItem newItem = new MyItem(data.getStringExtra("responceUserImgUrl"),
                        data.getStringExtra("responceUserName"),
                        data.getStringExtra("responceItemName"),
                        data.getStringExtra("responceLength"),
                        data.getStringExtra("responceDescribe"),
                        data.getStringExtra("resposponcePhotoUrl"), 0);
                itemList.add(newItem);
                adapter.notifyDataSetChanged();
                break;
            }
            case EDIT_REQUEST_CODE: {
                int current = data.getIntExtra("position", 0);
                MyItem currentItem = itemList.get(current);

                currentItem.setPictureUrl(data.getStringExtra("responceUserImgUrl"));
                currentItem.setUserName(data.getStringExtra("responceUserName"));
                currentItem.setNewsName(data.getStringExtra("responceItemName"));
                currentItem.setLength(data.getStringExtra("responceLength"));
                currentItem.setDescription(data.getStringExtra("responceDescribe"));
                currentItem.setPhotoUrl(data.getStringExtra("resposponcePhotoUrl"));
                adapter.notifyDataSetChanged();
                break;
            }
            default:
                return;
        }


    }
}
