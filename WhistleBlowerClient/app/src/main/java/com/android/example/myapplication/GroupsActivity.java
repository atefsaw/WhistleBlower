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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This activity is responsible on handling the groups in the application.
 */
public class GroupsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GroupListAdapter adapter; // the bridge between the list of the groups and the Recycler view
    private RecyclerView.LayoutManager layoutManager; // for aligning group's items in our list (creates the list view)

    ArrayList<GroupItem> groupsItems; // the group items that will be seen in the groups list
    int groupsNumber;  // the number of groups in the application

    User currentUser; // the user who signed up and will use the application

    List<Group> allGroups = new ArrayList<>();  // all the groups objects in the application
    List<Group> groups;

    public static Handler timerHandler;
    public static Runnable timerRunnable;
    private final static int GROUP_POLLING_INTERVAL = 500; // This interval sets the time for group polling thread

    private final static int CREATE_GROUP_REQUEST = 1;

    /**
    List<Message> lastGroupSeenMessages;*/
    Map<String, List<Message>> groupToMessages;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(getString(R.string.action_bar_color))));
    
        groupsNumber = 0;
    
        String userPhoneNumber = getIntent().getStringExtra(getString(R.string.phoneNumberIntentKey));
        currentUser = new User(userPhoneNumber);
    
        this.groupsItems = new ArrayList<>();

        timerHandler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long millis = System.currentTimeMillis();
                groups = RestHandler.pullGroups(currentUser.getUserId());
                allGroups.addAll(groups); // add the new groups to all groups
                for (Group group : groups) {
                        String groupName = group.getName();
                        String lastSeenMessage = "";
                        GroupItem item = new GroupItem(R.drawable.ic_android, groupName,
                                                       lastSeenMessage, group.getId());
                        groupsItems.add(item);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(layoutManager);
                }

                timerHandler.postDelayed(this, GROUP_POLLING_INTERVAL);
                }
        };

        timerHandler.postDelayed(timerRunnable, 0); // Run the above polling thread immediately

        // TODO selects group from DB and add/update them to the groupItem array list
        this.recyclerView = (RecyclerView) findViewById(R.id.GroupsRecyclerView);
        this.recyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(this);
        this.adapter = new GroupListAdapter(groupsItems, userPhoneNumber, groupToMessages);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(this.layoutManager);


        adapter.setOnItemClickListener(new GroupListAdapter.OnItemClickListener() {
            /**
             * This method is called when we click on a group item.
             */
            @Override
            public void onItemClick(int position) {
                adapter.notifyItemChanged(position);
                }
        });
    }

    /**
     * This method will be called when we get back from 'CreateGroup' activity.
     * @param data the Intent that was sent from 'CreateGroup'
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_GROUP_REQUEST)
        {
            if (resultCode == RESULT_OK)
            {
                // TODO: for feature, check if there is more than one group created on the same second (by two different users)
                Group createdGroup = RestHandler.pullGroups(data.getStringExtra(getString(R.string.phoneNumberIntentKey))).get(0);
                allGroups.add(createdGroup);
                String lastSeenMessage = "";
                GroupItem item = new GroupItem(R.drawable.ic_android, createdGroup.getName(),
                                               lastSeenMessage, createdGroup.getId());
                groupsItems.add(item);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
                timerHandler.postDelayed(timerRunnable, 0);
            }
        }
    }


    /**
     * This method will be envoked when user clicks on the add group button.
     */
    public void addGroup(View view){
        timerHandler.removeCallbacks(timerRunnable); // Stops the polling thread of getting groups
        Intent intent = new Intent(this, CreateGroup.class);
        intent.putExtra(getString(R.string.phoneNumberIntentKey), currentUser.getUserId());
        startActivityForResult(intent, CREATE_GROUP_REQUEST);
    }

}
