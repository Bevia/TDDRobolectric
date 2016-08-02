package com.corebaseit.tddrobolectric;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int NAME_RESPONSE = 1;
    TextView nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final View button1 = findViewById(R.id.login);
       final View button2 = findViewById(R.id.forresult);

       nameView = (TextView)findViewById(R.id.nameView);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(MainActivity.this, WelcomeActivity.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(MainActivity.this, OnActivityResultNameActivity.class), NAME_RESPONSE);
            }
        });
    }

    public void askForUserName() {
        startActivityForResult(
                new Intent(this, OnActivityResultNameActivity.class), NAME_RESPONSE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NAME_RESPONSE) {
            if (resultCode == RESULT_OK) {
                nameView.setText(data.getStringExtra("result"));
            } else if (resultCode == RESULT_CANCELED) {
                nameView.setText("No name given.");
            }
        }
    }
}
