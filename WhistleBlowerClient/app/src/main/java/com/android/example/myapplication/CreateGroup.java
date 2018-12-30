package com.android.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateGroup extends AppCompatActivity {

    private ListView contactsListView;
    private Map<String, String> contactNameToNumber;
    ActionBar actionBar;
    final ArrayList<String> selectedGroupMemebers = new ArrayList<>();
    String userPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group2);
        this.contactNameToNumber = new HashMap<>();
        this.setTitle("Choose contacts...");
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00CED1")));

        userPhoneNumber = getIntent().getStringExtra("PHONE_NUMBER");

        contactsListView = (ListView) findViewById(R.id.contacts_listview);
        ArrayList<String> contacts =getContacts();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                contacts );
        contactsListView.setAdapter(arrayAdapter);


        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = contactsListView.getItemAtPosition(position);
                // TODO: Change color of item when been selected
                String name = (String )o; //As you are using Default String Adapter
                Toast.makeText(getBaseContext(), name, Toast.LENGTH_SHORT).show();
                selectedGroupMemebers.add(name);
            }
        });
    }

    public ArrayList<String> getContacts() {
        ArrayList<String> contacts = new ArrayList<>();
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            this.contactNameToNumber.put(name, phoneNumber);
            contacts.add(name);
        }
        phones.close();
        return contacts;
    }

    /**
     * This method will be invoked when we finish creating the group.
     */
    public void createGroup(View view) {

        // Get the new group name that must be created.
        EditText groupNameInputBox = (EditText) findViewById(R.id.group_name);
        String groupName = null;
        // Check if user inserted a group name
        if (!groupNameInputBox.getText().toString().equals("")) {
            groupName = groupNameInputBox.getText().toString();
        }
        else {
            Toast.makeText(getBaseContext(), "Insert group name", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<String> numbers = new ArrayList<>();
        numbers.add(userPhoneNumber); // Add user own number to the group
        for (String name : selectedGroupMemebers) {
            numbers.add(this.contactNameToNumber.get(name));
        }

        // Check if user selected at least one user
        if (numbers.size() < 2) {
            Toast.makeText(getBaseContext(), "Add at least one contact you your group", Toast.LENGTH_SHORT).show();
            return;
        }
        // Set the intent that will be sent to the ChatAcitvity
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("GROUP_NAME", groupName);
        intent.putExtra("PHONE_NUMBER", userPhoneNumber);
        intent.putStringArrayListExtra("GROUP_MEMBERS", numbers);

        // TODO: add Rest method to create group

        startActivity(intent);
        finish(); // This will kill the CreateActivity activity and will prevent access to it
                 // via the 'Back' button.
    }
}
