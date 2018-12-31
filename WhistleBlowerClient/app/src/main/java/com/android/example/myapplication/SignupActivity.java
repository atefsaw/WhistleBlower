package com.android.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.fasterxml.jackson.core.JsonProcessingException;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // This hides the action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.introduction_signup);
    }

    /**
     * This method gets invoked when a user presses the 'Signup' button.
     */
    public void signUpUser(View view) {
        EditText mEdit = (EditText) findViewById(R.id.input_name);
        String userPhoneNumber = "";
        if (mEdit != null) {
            userPhoneNumber = String.valueOf(mEdit.getText());
        }
        User newUser = new User(userPhoneNumber);
        try {
            RestHandler.createUser(newUser);  // Creates the user in the server
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Intent groupsActivityIntent = new Intent(this, GroupsActivity.class);
        groupsActivityIntent.putExtra(getString(R.string.phoneNumberIntentKey), userPhoneNumber);
        if (!userPhoneNumber.equals("")) {
            startActivity(groupsActivityIntent);
        }
    }


}
