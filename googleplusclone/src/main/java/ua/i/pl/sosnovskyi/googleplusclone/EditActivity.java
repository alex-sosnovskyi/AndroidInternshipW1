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
    EditText userEditImg;
    EditText userName;
    EditText itemName;
    EditText describe;
    EditText photo;
    EditText lengthEdit;

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
                if (!isValidUrl(String.valueOf(userEditImg.getText())) || !isValidUrl(String.valueOf(photo.getText()))){
                    Toast.makeText(view.getContext(), "Wrong data", Toast.LENGTH_LONG).show();

                }
                    Intent intent = new Intent(getParent(), MainActivity.class);
                intent.putExtra("responceUserImgUrl", String.valueOf(userEditImg.getText()));
                intent.putExtra("responceUserName", String.valueOf(userName.getText()));
                intent.putExtra("responceItemName", String.valueOf(itemName.getText()));
                intent.putExtra("responceLength", String.valueOf(lengthEdit.getText()));
                intent.putExtra("responceDescribe", String.valueOf(describe.getText()));
                intent.putExtra("resposponcePhotoUrl", String.valueOf(photo.getText()));
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
