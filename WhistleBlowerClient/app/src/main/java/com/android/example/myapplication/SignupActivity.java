package com.android.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.introduction_signup);


        EditText mEdit = (EditText) findViewById(R.id.input_name);
        String userPhoneNumber = "";
        if (mEdit != null) {
            userPhoneNumber = String.valueOf(mEdit.getText());
        }

        Intent intent = new Intent(this, GroupsActivity.class);
        intent.putExtra("PHONE_NUMBER", userPhoneNumber);


        final String toPassPhoneNumber = userPhoneNumber;
        final Intent toPassIntent = intent;
        Button button = (Button) findViewById(R.id.btn_signup);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!toPassPhoneNumber.equals("")) {
                    v.getContext().startActivity(toPassIntent);
                }
            }
        });

    }
}
