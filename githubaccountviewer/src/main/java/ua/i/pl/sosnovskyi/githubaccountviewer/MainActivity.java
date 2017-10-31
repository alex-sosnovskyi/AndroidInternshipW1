package ua.i.pl.sosnovskyi.githubaccountviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView accountName = (TextView) findViewById(R.id.account_name);
        accountName.setText("alex-sosnovskyi");
        Button show = (Button) findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(accountName.getText())) {
                    Toast.makeText(v.getContext(), R.string.toast_message, Toast.LENGTH_LONG).show();
                    return;
                }

                ListGitHubActivity.startActivityWithAccountName(MainActivity.this, accountName.getText().toString());
            }
        });
    }


}
