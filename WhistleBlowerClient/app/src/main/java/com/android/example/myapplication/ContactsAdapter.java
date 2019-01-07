package com.android.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContactsAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<Contact> currentContactList;
    ArrayList<Contact> contactsList; // todo

    public ContactsAdapter(Context context, List<Contact> contactList){
        this.context = context;
        this.currentContactList = contactList;
        inflater = LayoutInflater.from(context);
        this.contactsList = new ArrayList<>();
        this.contactsList.addAll(contactList);

    }

    public class ViewHolder{
        TextView contactName;

    }

    @Override
    public int getCount() {
        return currentContactList.size();
    }

    @Override
    public Object getItem(int position) {
        return currentContactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.contact_item, null);

            holder.contactName = convertView.findViewById(R.id.contact_name);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.contactName.setText(currentContactList.get(position).getName());


        convertView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                // todo on click function
            }
        });
        return convertView;
    }

    // Filters contacts list after searching

    public void filterContacts(String searchText){
        searchText = searchText.toLowerCase(Locale.getDefault());
        this.currentContactList.clear();
        if(searchText.length() == 0){
            this.currentContactList.addAll(this.contactsList);
        }
        else{
            for(Contact contact:this.contactsList){
                if (contact.getName().toLowerCase(Locale.getDefault()).contains(searchText)){
                    this.currentContactList.add(contact);
                }
            }
        }
        notifyDataSetChanged();
    }


}
