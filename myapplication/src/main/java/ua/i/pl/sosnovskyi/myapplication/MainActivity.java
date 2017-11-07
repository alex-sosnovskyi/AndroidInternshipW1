package ua.i.pl.sosnovskyi.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.CollapsibleActionView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ua.i.pl.sosnovskyi.myapplication.R.id.list_item;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    FloatingActionButton fBtn;
    List<String> itemList;
    private static int count = 1;
    private MyAdapter adapter;
    int DELETE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final String TAG = "States";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "MainActivity: onCreate()");
        listView = (ListView) findViewById(R.id.itemsList);
        itemList = new ArrayList<>();
        adapter = new MyAdapter(itemList);
        listView.setAdapter(adapter);

        FloatingActionButton fBtn = (FloatingActionButton) findViewById(R.id.fab);
        fBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.add("http://i.imgur.com/DvpvklR.png");
                adapter.notifyDataSetChanged();
            }
        });

    }


}
