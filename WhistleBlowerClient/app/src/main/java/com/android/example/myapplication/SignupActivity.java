package com.android.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.fasterxml.jackson.core.JsonProcessingException;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.introduction_signup);

        
    }

    public void signUpUser(View view) {
        EditText mEdit = (EditText) findViewById(R.id.input_name);
        String userPhoneNumber = "";
        if (mEdit != null) {
            userPhoneNumber = String.valueOf(mEdit.getText());
        }

        User newUser = new User(userPhoneNumber);
        try {
            RestHandler.createUser(newUser);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, GroupsActivity.class);
        intent.putExtra("PHONE_NUMBER", userPhoneNumber);
        if (!userPhoneNumber.equals("")) {
            startActivity(intent);
        }
    }


}
