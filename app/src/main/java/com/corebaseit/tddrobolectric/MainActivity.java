package com.corebaseit.tddrobolectric;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int NAME_RESPONSE = 1;
    TextView nameView;

    private static SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (prefs == null) {
            prefs = this.getApplicationContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        }

       final View button1 = findViewById(R.id.welcome);
       final View button2 = findViewById(R.id.onactivityforresult);
       final View button3 = findViewById(R.id.fragment);

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

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(MainActivity.this, FragmentActivity.class), NAME_RESPONSE);
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

    public String getSharedPrefValue(final String key) {
        return prefs.getString(key, null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
