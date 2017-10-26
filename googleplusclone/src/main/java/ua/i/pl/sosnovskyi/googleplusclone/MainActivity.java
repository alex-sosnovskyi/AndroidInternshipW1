package ua.i.pl.sosnovskyi.googleplusclone;

import android.content.ClipData;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<MyItem> itemList;

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
        MyAdapter adapter = new MyAdapter(itemList);
        listView.setAdapter(adapter);

        FloatingActionButton fBtn = (FloatingActionButton) findViewById(R.id.fab);
        fBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }
}
