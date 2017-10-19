package ua.i.pl.sosnovskyi.trainingday3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView edit1;
    private TextView edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button firstButton = (Button) findViewById(R.id.firstButton);
        firstButton.setOnClickListener(this);
        edit1 = (EditText) findViewById(R.id.textSource);
        // str1=String.valueOf(edit1.getText());
        Button send = (Button) findViewById(R.id.send);
        send.setOnClickListener(this);
        edit2 = (EditText) findViewById(R.id.message);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, OtherActivity.class);
        switch (v.getId()) {
            case R.id.firstButton: {
                intent.putExtra("message1", edit1.getText().toString());
                startActivity(intent);
                break;
            }
            case R.id.send: {
                intent.putExtra("message2", edit2.getText().toString());
                startActivityForResult(intent, 100);
                break;
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode != 100) || (resultCode != RESULT_OK)) {
            finish();
        }
//        edit2=(EditText) findViewById(R.id.message);
        edit2.setText(data.getDataString());
    }

}
