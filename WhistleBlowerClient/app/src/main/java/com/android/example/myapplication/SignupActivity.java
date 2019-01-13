package com.android.example.myapplication;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.fasterxml.jackson.core.JsonProcessingException;

public class SignupActivity extends AppCompatActivity {

    private CurrentUserViewModel currentUserViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // This hides the action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


//        currentUserViewModel = ViewModelProviders.of(this, new ViewModelFactory(this.getApplication(), -1)).get(CurrentUserViewModel.class);
//        if (currentUserViewModel.getCurrentUser().size() > 0) {
//            String userPhoneNumber = currentUserViewModel.getCurrentUser().get(0).userPhoneNumber;
//            Intent groupsActivityIntent = new Intent(this, GroupsActivity.class);
//            groupsActivityIntent.putExtra(getString(R.string.phoneNumberIntentKey), userPhoneNumber);
//            startActivity(groupsActivityIntent);
//            finish(); // close the signup
//        }

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
            finish(); // close the signup
        }
    }
}
