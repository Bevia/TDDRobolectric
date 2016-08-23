package com.corebaseit.tddrobolectric;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myAsyncTask = new MyAsyncTask();
        myAsyncTask.onPostExecute("Hello there");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
