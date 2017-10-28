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
    public static final int EDIT_NEW_REQUEST_CODE = 1;
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
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final MyItem itemAtPosition = (MyItem) parent.getItemAtPosition(position);
                showDeleteDialog(itemAtPosition);
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

    private void showDeleteDialog(final MyItem itemAtPosition) {
        String title = getString(R.string.action_dialog);
        String message = getString(R.string.action_dialog_content);
        String deleting = getString(R.string.delete_action);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(deleting, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.remove(itemAtPosition);
            }
        });
        alertDialog.setNegativeButton(android.R.string.cancel, null);
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == EDIT_NEW_REQUEST_CODE) || (resultCode == RESULT_OK)) {

            MyItem newItem = new MyItem(data.getStringExtra("responceUserImgUrl"),
                   data.getStringExtra("responceUserName") ,
                    data.getStringExtra("responceItemName"),
                    data.getStringExtra("responceLength"),
                    data.getStringExtra("responceDescribe"),
                    data.getStringExtra("resposponcePhotoUrl"), 0);


            adapter.notifyDataSetChanged();
        }


    }
}
