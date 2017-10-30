package ua.i.pl.sosnovskyi.googleplusclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditActivity extends AppCompatActivity {
    private EditText userEditImg;
    private EditText userName;
    private EditText itemName;
    private EditText describe;
    private EditText photo;
    private EditText lengthEdit;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        userEditImg = (EditText) findViewById(R.id.user_edit_img);
        userName = (EditText) findViewById(R.id.user_edit_name);
        itemName = (EditText) findViewById(R.id.item_edit_name);
        lengthEdit = (EditText) findViewById(R.id.length_edit);
        describe = (EditText) findViewById(R.id.description_edit);
        photo = (EditText) findViewById(R.id.photo_edit);
        Intent intent = getIntent();
        if (intent != null) {
            position = intent.getIntExtra("position", 0);
            userEditImg.setText(intent.getStringExtra("userImgUrl"));
            userName.setText(intent.getStringExtra("userName"));
            itemName.setText(intent.getStringExtra("itemName"));
            lengthEdit.setText(intent.getStringExtra("length"));
            describe.setText(intent.getStringExtra("describe"));
            photo.setText(intent.getStringExtra("photoUrl"));
        }
        FloatingActionButton fabOk = (FloatingActionButton) findViewById(R.id.fab_add);
        FloatingActionButton fabCancel = (FloatingActionButton) findViewById(R.id.fab_cancel);
        fabCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
        fabOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isValidUrl(String.valueOf(userEditImg.getText())) || !isValidUrl(String.valueOf(photo.getText()))) {
                    Toast.makeText(view.getContext(), "Wrong data", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                intent.putExtra("responceUserImgUrl", String.valueOf(userEditImg.getText()));
                intent.putExtra("responceUserName", String.valueOf(userName.getText()));
                intent.putExtra("responceItemName", String.valueOf(itemName.getText()));
                intent.putExtra("responceLength", String.valueOf(lengthEdit.getText()));
                intent.putExtra("responceDescribe", String.valueOf(describe.getText()));
                intent.putExtra("resposponcePhotoUrl", String.valueOf(photo.getText()));
                intent.putExtra("position", position);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private boolean isValidUrl(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url.toLowerCase());
        return m.matches();
    }
}
