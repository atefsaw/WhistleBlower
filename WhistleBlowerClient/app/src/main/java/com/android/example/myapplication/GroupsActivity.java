package com.android.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        groupsNumber = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        //TODO selects group from DB and add/update them to the groupItem array list
        this.groupsItems = new ArrayList<>();
        this.recyclerView = (RecyclerView) findViewById(R.id.GroupsRecyclerView);
        this.recyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(this);
        this.adapter = new GroupListAdapter(groupsItems);
        this.adapter = new GroupListAdapter(groupsItems);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(this.layoutManager);

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
