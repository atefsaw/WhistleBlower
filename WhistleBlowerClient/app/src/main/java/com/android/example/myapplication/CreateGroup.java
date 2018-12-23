package com.android.example.myapplication;

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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateGroup extends AppCompatActivity {

    private ListView contactsListView;
    private Map<String, String> contactNameToNumber;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group2);
        this.contactNameToNumber = new HashMap<>();
        this.setTitle("Choose contacts...");
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00CED1")));

        contactsListView = (ListView) findViewById(R.id.contacts_listview);
        List<String> contacts = getContacts();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                contacts );
        contactsListView.setAdapter(arrayAdapter);

        /*contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = prestListView.getItemAtPosition(position);
                prestationEco str = (prestationEco)o; //As you are using Default String Adapter
                Toast.makeText(getBaseContext(),str.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    public ArrayList<String> getContacts() {
        ArrayList<String> contacts = new ArrayList<>();
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            this.contactNameToNumber.put(name, phoneNumber);
            Toast.makeText(getApplicationContext(),name, Toast.LENGTH_LONG).show();
            contacts.add(name);
        }
        phones.close();
        return contacts;
    }

}
