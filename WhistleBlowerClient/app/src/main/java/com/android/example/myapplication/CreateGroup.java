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
import java.util.HashMap;
import java.util.Map;

public class CreateGroup extends AppCompatActivity {

    private ListView contactsListView;
    private Map<String, String> contactNameToNumber;
    ActionBar actionBar;
    final ArrayList<String> selectedGroupMemebers = new ArrayList<>();
    String userPhoneNumber;

    String groupName;
    ArrayList<String> numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        this.contactNameToNumber = new HashMap<>();

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(getString(R.string.action_bar_color))));
        this.setTitle(R.string.choose_contacts_in_actionbar);

        userPhoneNumber = getIntent().getStringExtra(getString(R.string.phoneNumberIntentKey));
        contactsListView = (ListView) findViewById(R.id.contacts_listview);
        ArrayList<String> contacts = getContacts();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                contacts );
        contactsListView.setAdapter(arrayAdapter);
        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object contactView = contactsListView.getItemAtPosition(position);
                // TODO: Change color of item when been selected
                String name = (String ) contactView; // As you are using Default String Adapter
                Toast.makeText(getBaseContext(), name, Toast.LENGTH_SHORT).show();
                selectedGroupMemebers.add(name);
            }
        });

    }


    /**
     * This method gets a list of contact numbers from the users phone.
     * TODO: needs to be cached in the signup activity as it's used on
     */
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
        groupName = null;
        numbers = new ArrayList<>();


        if (!getUserContactNumbers() || !getGroupName(groupNameInputBox.getText().toString())) {
            return;
        }

        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(getString(R.string.groupNameIntentKey), groupName);
        intent.putExtra(getString(R.string.phoneNumberIntentKey), userPhoneNumber);
        intent.putStringArrayListExtra(getString(R.string.groupMembersIntentKey), numbers);

        Group newGroup = new Group(numbers, groupName);
        RestHandler.createGroup(newGroup);

        setResult(RESULT_OK, intent);
        finish(); // This will kill the CreateActivity activity and will prevent access to it
                 // via the 'Back' button.
    }

    private boolean getUserContactNumbers() {
        numbers.add(userPhoneNumber); // Add user own number to the group
        for (String name : selectedGroupMemebers) {
            numbers.add(this.contactNameToNumber.get(name));
        }
        // Check if user selected at least one user
        if (numbers.size() < 2) {
            Toast.makeText(getBaseContext(), getString(R.string.add_contacts_error_message), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
}
