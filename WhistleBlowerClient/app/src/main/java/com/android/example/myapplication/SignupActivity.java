package com.android.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.USER_SIGNUP_SP_FILE), Activity.MODE_PRIVATE);
        boolean alreadySignedUp = sharedPreferences.getBoolean(getString(R.string.USER_SIGNUP_SP_KEY), false);
        String userPhoneNumber = sharedPreferences.getString(getString(R.string.USER_SIGNUP_SP_PHONENUMBER), "");

        if (alreadySignedUp) {
            Intent groupsActivityIntent = new Intent(this, GroupsActivity.class);
            groupsActivityIntent.putExtra(getString(R.string.phoneNumberIntentKey), userPhoneNumber);
            groupsActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(groupsActivityIntent);
            finish(); // close the signup
        }
        else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(getString(R.string.USER_SIGNUP_SP_KEY), false); // Save this locally to not prompt register layout again
            editor.apply();
            setContentView(R.layout.introduction_signup);

        }
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

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.USER_SIGNUP_SP_FILE), Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.USER_SIGNUP_SP_KEY), true); // Save this locally to not prompt register layout again
        editor.putString(getString(R.string.USER_SIGNUP_SP_PHONENUMBER), userPhoneNumber);
        editor.apply();


        Intent groupsActivityIntent = new Intent(this, GroupsActivity.class);
        groupsActivityIntent.putExtra(getString(R.string.phoneNumberIntentKey), userPhoneNumber);
        if (!userPhoneNumber.equals("")) {
            startActivity(groupsActivityIntent);
            finish();
        }
    }


}
