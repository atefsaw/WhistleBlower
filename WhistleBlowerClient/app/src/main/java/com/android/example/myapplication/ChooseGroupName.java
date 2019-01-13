package com.android.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ChooseGroupName extends AppCompatActivity {
    ActionBar actionBar;
    ArrayList<String> contactsName;
    ArrayList<String> contactsNumber;
    String groupName;
    String userPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_group_name);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(getString(R.string.action_bar_color))));
        this.setTitle(R.string.choose_group_name);

        userPhoneNumber = getIntent().getStringExtra(getString(R.string.phoneNumberIntentKey));
        contactsName = getIntent().getStringArrayListExtra("contacts_name");
        contactsNumber = getIntent().getStringArrayListExtra("contacts_number");

    }

    private boolean getGroupName(String insertedName) {
        if (!insertedName.equals("")) {
            groupName = insertedName;
        }
        else {
            Toast.makeText(getBaseContext(), getString(R.string.insert_group_name_error_message), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public void createGroup(View view) {

        EditText groupNameInputBox = (EditText) findViewById(R.id.group_name);

        if (contactsName.isEmpty() || !getGroupName(groupNameInputBox.getText().toString())) {
            return;
        }

        Intent intent = new Intent(this, GroupsActivity.class);
        intent.putExtra(getString(R.string.groupNameIntentKey), groupName);
        intent.putExtra(getString(R.string.phoneNumberIntentKey), userPhoneNumber);
        intent.putStringArrayListExtra("contacts_name", contactsName);
        intent.putStringArrayListExtra("contacts_number", contactsNumber);

        Group newGroup = new Group(contactsNumber, groupName);
        RestHandler.createGroup(newGroup);

        setResult(RESULT_OK, intent);
        startActivity(intent);
        finish(); // This will kill the CreateActivity activity and will prevent access to it
                  // via the 'Back' button.
    }
}
