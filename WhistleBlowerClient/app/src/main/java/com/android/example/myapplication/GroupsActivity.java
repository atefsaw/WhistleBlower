package com.android.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class GroupsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GroupListAdapter adapter; // the bridge between the list of the groups and the Recycler view
    private RecyclerView.LayoutManager layoutManager; // for aligning group's items in our list (creates the list view)
    ArrayList<GroupItem> groupsItems;
    int groupsNumber;
    private ImageButton addButton;

    ActionBar actionBar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        // TODO: make it a function
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00CED1")));

        groupsNumber = 0;

        String userPhoneNumber = getIntent().getStringExtra("PHONE_NUMBER");
        System.out.println("Phone number is : " + userPhoneNumber);
        
        // TODO selects group from DB and add/update them to the groupItem array list
        this.groupsItems = new ArrayList<>();
        this.recyclerView = (RecyclerView) findViewById(R.id.GroupsRecyclerView);
        this.recyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(this);
        this.adapter = new GroupListAdapter(groupsItems);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(this.layoutManager);

        adapter.setOnItemClickListener(new GroupListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

//                Intent myactivity = new Intent(context.getApplicationContext(), ChatActivity.class);
//                context.getApplicationContext().startActivity(myactivity);
                groupsItems.get(position).changeText("For example");
//                startActivity(intent);
                adapter.notifyItemChanged(position);

            }
        });
    }


    public void addGroup(View view){
        this.groupsNumber += 1;
        GroupItem item = new GroupItem(R.drawable.ic_android, "Group"+this.groupsNumber,
                "Last message in group.");
        groupsItems.add(item);
//        this.adapter.updateGroupItems(item);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(this.layoutManager);
    }

}
