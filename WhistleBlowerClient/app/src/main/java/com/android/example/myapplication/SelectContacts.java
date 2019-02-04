package com.android.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import java.util.ArrayList;

public class SelectContacts extends AppCompatActivity {
    ActionBar actionBar;
    ListView contactsListView;
    ContactsAdapter contactsAdapter;
    ArrayList<String> contact_names;
    ArrayList<String> phoneNumbers;
    ArrayList<Contact> contactsArrayList = new ArrayList<>();
    String userPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_contacts);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(getString(R.string.action_bar_color))));
        this.setTitle(R.string.choose_contacts_in_actionbar);

        contact_names = new ArrayList<>();
        phoneNumbers = new ArrayList<String>();
        userPhoneNumber = getIntent().getStringExtra(getString(R.string.phoneNumberIntentKey));
        contactsListView = (ListView) findViewById(R.id.contacts_listview);

        getContacts();
        contactsAdapter = new ContactsAdapter(this, contactsArrayList);

        contactsListView.setAdapter(contactsAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String string) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String string) {
                if (TextUtils.isEmpty(string)) {
                    contactsAdapter.filterContacts("");
                    contactsListView.clearTextFilter();
                } else {
                    contactsAdapter.filterContacts(string);
                }
                return true;
            }
        });
        return true;
    }

    public void getContacts() {
//        ArrayList<String> contacts = new ArrayList<>();
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            this.contact_names.add(name);
            this.phoneNumbers.add(phoneNumber);
            this.contactsArrayList.add(new Contact(name, phoneNumber));
        }
        phones.close();
//        return contacts;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getGroupId();

//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK)
            {
                finish();
            }
        }
    }
    public void chooseGroupName(View view) {
        if (!contactsAdapter.selectedContactsNumbers.isEmpty()) {
            contactsAdapter.selectedContactsNames.add("You");
            contactsAdapter.selectedContactsNumbers.add(userPhoneNumber);
            Intent intent = new Intent(this, ChooseGroupName.class);
            intent.putExtra(getString(R.string.phoneNumberIntentKey), userPhoneNumber);
            intent.putStringArrayListExtra("contacts_name", contactsAdapter.getSelectedContactsNames());
            intent.putStringArrayListExtra("contacts_number", contactsAdapter.getSelectedContactsNumbers());
//            startActivity(intent);
            startActivityForResult(intent, 1);
//            finish();
        }
        else {
            Toast.makeText(getBaseContext(), getString(R.string.add_contacts_error_message), Toast.LENGTH_SHORT).show();
        }
    }
}