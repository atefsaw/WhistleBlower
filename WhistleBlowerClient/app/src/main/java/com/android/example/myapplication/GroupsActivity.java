package com.android.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    List<Group> allGroups = new ArrayList<>();

    List<Group> groups;
    List<Message> lastGroupSeenMessages;

    Map<String, List<Message>> groupToMessages;

    User currentUser;

    private final static int INTERVAL = 500; // half-second

    public static Handler timerHandler;
    public static Runnable timerRunnable;

//    public static Handler LastMessagesHandler;
//    public static Runnable lastMessageRunnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
    
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00CED1")));
    
        groupsNumber = 0;
    
         String userPhoneNumber = getIntent().getStringExtra("PHONE_NUMBER");
//        String userPhoneNumber = "2";
        currentUser = new User(userPhoneNumber);
    
        this.groupsItems = new ArrayList<>();

//        allGroups = RestHandler.pullGroups(userPhoneNumber);
//        lastGroupSeenMessages = RestHandler.pullLastGroupMessages(userPhoneNumber);

        /*groupToMessages = new HashMap<>();
        for (Message message : lastGroupSeenMessages) {
            String groupName = GetGroupNameFromId(message.getGroupId());
            if (!groupToMessages.containsKey(groupName)) {
                ArrayList<Message> messagesArray = new ArrayList<>();
                messagesArray.add(message);
                groupToMessages.put(groupName, messagesArray);
            } else {
                groupToMessages.get(groupName).add(message);
            }
        }*/

       /* for (Group group : allGroups) {
            int messagesListSize = groupToMessages.get(group.getName()).size();
            String lastMessage;
            if (messagesListSize > 0){
                lastMessage = groupToMessages.get(group.getName()).get(messagesListSize - 1).getContent();
            }
            else {
                lastMessage = " ";
            }
            GroupItem item = new GroupItem(R.drawable.ic_android, group.getName(), lastMessage, group.getId());
            groupsItems.add(item);
        }*/


        // TODO: this should be removed when we restore the last message feature
        for (Group group : allGroups) {
            GroupItem item = new GroupItem(R.drawable.ic_android, group.getName(), "", group.getId());
            groupsItems.add(item);
        }


            timerHandler = new Handler();
            timerRunnable = new Runnable() {
                @Override
                public void run() {
                    long millis = System.currentTimeMillis();
                    groups = RestHandler.pullGroups(currentUser.getUserId());
                    allGroups.addAll(groups); // add the new groups to all groups
//                    lastGroupSeenMessages = RestHandler.pullLastGroupMessages(currentUser.getUserId());
                    for (Group group : groups) {
                        String groupName = group.getName();
//                        String lastSeenMessage = getLastSeenMessageForGroup(lastGroupSeenMessages, group.getId());
                        String lastSeenMessage = "";
                        GroupItem item = new GroupItem(R.drawable.ic_android, groupName,
                                                       lastSeenMessage, group.getId());
                        groupsItems.add(item);
                        // this.adapter.updateGroupItems(item);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(layoutManager);
                    }
                    timerHandler.postDelayed(this, 500);
                }
            };

            timerHandler.postDelayed(timerRunnable, 0);

        /*LastMessagesHandler = new Handler();
        lastMessageRunnable = new Runnable() {
            @Override
            public void run() {
                long millis = System.currentTimeMillis();
                lastGroupSeenMessages = RestHandler.pullLastGroupMessages(currentUser.getUserId());
                for (Message message : lastGroupSeenMessages) {
                    String lastSeenMessage = getLastSeenMessageForGroup(lastGroupSeenMessages, message.getGroupId());
                    // TODO: Update last seen message in GroupItem
                }
                timerHandler.postDelayed(this, 500);
            }
        };

        timerHandler.postDelayed(timerRunnable, 0);*/


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

    private String GetGroupNameFromId(int groupId) {
        for (Group group : allGroups) {
            if (group.getId() == groupId) {
                return group.getName();
            }
        }
        return "";
    }

    private String getLastSeenMessageForGroup(List<Message> messages, int groupId) {
        for (Message message : messages) {
            if (groupId == message.getGroupId()) {
                return message.getContent();
            }
        }
        return "";
    }


    /**
     * This method will be envoked when user clicks on the add group button.
     */
    public void addGroup(View view){

        timerHandler.removeCallbacks(timerRunnable);
//        LastMessagesHandler.removeCallbacks(lastMessageRunnable);

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

//    private class GetGroupsTask extends TimerTask {
//
//        public void run() {
//            groups = RestHandler.pullGroups(currentUser.getUserId());
//            for (Group receivedGroup : groups) {
//                String groupName = receivedGroup.getName();
//                GroupItem item = new GroupItem(R.drawable.ic_android, groupName,
//                                "Last message in group.", receivedGroup.getId());
//                groupsItems.add(item);
//                // this.adapter.updateGroupItems(item);
//                recyclerView.setAdapter(adapter);
//                recyclerView.setLayoutManager(layoutManager);
//            }
//            lastGroupSeenMessages = RestHandler.pullLastGroupMessages(currentUser.getUserId());
//        }
//    }
}
