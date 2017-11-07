package ua.i.pl.sosnovskyi.uritest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button dialButton;
        Button browserButton;
        Button mapButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapButton = (Button) findViewById(R.id.map);
        browserButton = (Button) findViewById(R.id.browser);
        dialButton = (Button) findViewById(R.id.dial);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:55.754283,37.62002"));

                try {
                    startActivity(intent);
                } catch (Exception e) {
//                    Toast.makeText(this,"This function is not avaleble" ).show();
                    return;
                }
            }
        });
        browserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://i.ua"));
                startActivity(intent);
            }
        });
        dialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:123456789"));
                try {
                    startActivity(intent);
                } catch (Exception e) {
//                    Toast.makeText(this,"This function is not avaleble" ).show();
                    return;
                }
            }
        });
    }


}
