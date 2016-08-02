package com.corebaseit.tddrobolectric;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OnActivityResultNameActivity extends AppCompatActivity {

    private String result = "Vincent Bevia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_chooser);

        final View button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",result);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",result);
        setResult(Activity.RESULT_OK,returnIntent);

        finish();

        /**
         If you don't want to return data:

         Intent returnIntent = new Intent();
         setResult(Activity.RESULT_CANCELED, returnIntent);
         finish();
         */
    }
}
