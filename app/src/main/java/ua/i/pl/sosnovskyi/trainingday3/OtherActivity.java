package ua.i.pl.sosnovskyi.trainingday3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Alex Sosnovskyi on 18.10.2017.
 */

public class OtherActivity extends AppCompatActivity implements View.OnClickListener {
    TextView answer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        TextView textView = (TextView) findViewById(R.id.destination_text1);
        String sourceText1 = getIntent().getStringExtra("message1");
        String sourceText2 = getIntent().getStringExtra("message2");
        textView.setText(sourceText1);
        answer = (TextView) findViewById(R.id.destination_text2);
        answer.setText(sourceText2);
        Button answerBtn = (Button) findViewById(R.id.sendBack);
        answerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("responce", answer.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
