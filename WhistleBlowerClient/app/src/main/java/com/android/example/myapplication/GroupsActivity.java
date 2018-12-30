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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GroupListAdapter adapter; // the bridge between the list of the groups and the Recycler view
    private RecyclerView.LayoutManager layoutManager; // for aligning group's items in our list (creates the list view)
    ArrayList<GroupItem> groupsItems;
    int groupsNumber;
    private ImageButton addButton;

    ActionBar actionBar;

    List<Group> groups;
    List<Message> messages;

    Map<String, List<Message>> groupToMessages;

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00CED1")));

        groupsNumber = 0;

        // TODO:       String userPhoneNumber = getIntent().getStringExtra("PHONE_NUMBER");
        String userPhoneNumber = "1";
        currentUser = new User(userPhoneNumber);

        this.groupsItems = new ArrayList<>();

        /**groups = RestHandler.pullGroups(userPhoneNumber);
        messages = RestHandler.pullMessages(userPhoneNumber);

        groupToMessages = new HashMap<>();
        for (Message message : messages) {
            String groupName = message.getGroup().getName();
            if (!groupToMessages.containsKey(groupName)) {
                ArrayList<Message> messagesArray = new ArrayList<>();
                messagesArray.add(message);
                groupToMessages.put(groupName, messagesArray);
            } else {
                groupToMessages.get(groupName).add(message);
            }
        }*/

        /*for (Group group : groups) {
            int messagesListSize = this.groupToMessages.get(group.getName()).size();
            String lastMessage;
            if (messagesListSize > 0){
                lastMessage = this.groupToMessages.get(group.getName()).get(messagesListSize - 1).getContent();
            }
            else {
                lastMessage = " ";
            }
            GroupItem item = new GroupItem(R.drawable.ic_android, group.getName(), lastMessage);
            this.groupsItems.add(item);
        }
*/
        // TODO selects group from DB and add/update them to the groupItem array list
        this.recyclerView = (RecyclerView) findViewById(R.id.GroupsRecyclerView);
        this.recyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(this);
        this.adapter = new GroupListAdapter(groupsItems, userPhoneNumber, groupToMessages);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(this.layoutManager);

        adapter.setOnItemClickListener(new GroupListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                groupsItems.get(position).changeText("For example");
                adapter.notifyItemChanged(position);

            }
        });
    }

    /**
     * This method will be envoked when user clicks on the add group button.
     */
    public void addGroup(View view){

        Intent intent = new Intent(this, CreateGroup.class);
        intent.putExtra("PHONE_NUMBER", currentUser.getUserId());
        startActivity(intent);

        /**this.groupsNumber += 1;
        String groupName = "Group" + this.groupsNumber;
        // TODO:  RestHandler.createGroup();
        GroupItem item = new GroupItem(R.drawable.ic_android, "Group" + this.groupsNumber,
                "Last message in group.");
        groupsItems.add(item);
//        this.adapter.updateGroupItems(item);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(this.layoutManager);*/
    }
}
