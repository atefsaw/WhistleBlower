package com.android.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class GroupsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter; // the bridge between the list of the groups and the Recycler view
    private RecyclerView.LayoutManager layoutManager; // for aligning group's items in our list (creates the list view)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        //TODO selects group from DB and add/update them to the groupItem array list
        ArrayList<GroupItem> groupsItems = new ArrayList<>();
        groupsItems.add(new GroupItem(R.mipmap.ic_launcher, "Family",
                "Hello to my lovely family."));

        groupsItems.add(new GroupItem(R.drawable.ic_android, "Work",
                "I love my work."));

        groupsItems.add(new GroupItem(R.mipmap.ic_launcher, "Friends",
                "Let's go eat burger."));

        groupsItems.add(new GroupItem(R.drawable.ic_android, "University",
                "Please vote!"));
        groupsItems.add(new GroupItem(R.mipmap.ic_launcher, "חברים",
                "Let's go eat burger."));

        groupsItems.add(new GroupItem(R.drawable.ic_android, "תותחים",
                "It's so hard here!"));
        groupsItems.add(new GroupItem(R.mipmap.ic_launcher, "العيله",
                "Let's go eat burger."));

        groupsItems.add(new GroupItem(R.drawable.ic_android, "اصدقائي",
                "It's so hard here!"));
        groupsItems.add(new GroupItem(R.mipmap.ic_launcher, "Group1",
                "Last message in group."));

        groupsItems.add(new GroupItem(R.drawable.ic_android, "Group2",
                "Last message in group."));
        groupsItems.add(new GroupItem(R.mipmap.ic_launcher, "Group3",
                "Last message in group."));

        groupsItems.add(new GroupItem(R.drawable.ic_android, "Group4",
                "Last message in group."));
        groupsItems.add(new GroupItem(R.mipmap.ic_launcher, "Group5",
                "Last message in group."));

        groupsItems.add(new GroupItem(R.drawable.ic_android, "Group6",
                "Last message in group."));
        this.recyclerView = (RecyclerView) findViewById(R.id.GroupsRecyclerView);
        this.recyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(this);
        this.adapter = new GroupListAdapter(groupsItems);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(this.layoutManager);
    }
}
