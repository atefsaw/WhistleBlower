package com.android.example.myapplication;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ChatActivity extends AppCompatActivity {


    private List<Message> messagesList;
    private EditText editText;

    private int messageListIndex = 0;

    private MessageListAdapter messageAdapter;
    private ListView messagesView;


    private User currentUser;
    private Group currentGroup;

    ActionBar actionBar;

    private String groupId;

    private final static int INTERVAL = 500; // half-second


    public void sendMessage(View view) {
        String textMessage = editText.getText().toString();
        if (textMessage.length() > 0) {
            Message message = new Message(textMessage, currentUser, Integer.parseInt(groupId), true);
            message.setContent(textMessage);
            editText.getText().clear();
            RestHandler.sendMessages(message);
            messageAdapter.add(message);
            messagesView.setSelection(messagesView.getCount() - 1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00CED1")));


        // this is where the message text goes
        editText = (EditText) this.findViewById(R.id.edittext_chatbox);

        // Get Intents
        String userPhoneNumber = getIntent().getStringExtra("PHONE_NUMBER");
        this.groupId = getIntent().getStringExtra("GROUP_ID");
        String groupName = getIntent().getStringExtra("GROUP_NAME");
        ArrayList<String> messages = getIntent().getStringArrayListExtra("GROUP_MESSAGES");

        // Set the group
        currentUser = new User(userPhoneNumber);
        User applicationAdmin = new User("0");
        ArrayList<User> users = new ArrayList<>();
        users.add(currentUser);
        users.add(applicationAdmin);

        ArrayList<String> usersIDs = new ArrayList<>();
        for (User user : users) {
            usersIDs.add(user.getUserId());
        }

        // set the action bar with the group name
        this.setTitle(groupName);

        messageAdapter = new MessageListAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);

        final Handler timerHandler = new Handler();
        Runnable timerRunnable = new Runnable() {
            @Override
            public void run() {
                long millis = System.currentTimeMillis();
                messagesList = RestHandler.pullMessages(currentUser.getUserId(), Integer.parseInt(groupId));
                for (Message receievedMessage : messagesList) {
                    messageAdapter.add(receievedMessage);
                    messagesView.setSelection(messagesView.getCount() - 1);
                }
                timerHandler.postDelayed(this, 300);
            }
        };

        timerHandler.postDelayed(timerRunnable, 0);
        /*
        final Message welcomeMessage = new Message("This is a welcome message", currentUser, currentGroup.getId(), false );
        messageAdapter.add(welcomeMessage);
        messagesView.setSelection(messagesView.getCount() - 1);*/

//        Timer myTimer = new Timer("GetMessagesTimer", true);
//        myTimer.scheduleAtFixedRate(new GetMessagesTask(), INTERVAL, INTERVAL);
    }

    private class GetMessagesTask extends TimerTask {

        public void run() {
            messagesList = RestHandler.pullMessages(currentUser.getUserId(), currentGroup.getId());
            for (Message receievedMessage : messagesList) {
                messageAdapter.add(receievedMessage);
                messagesView.setSelection(messagesView.getCount() - 1);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        GroupsActivity.LastMessagesHandler.postDelayed(GroupsActivity.lastMessageRunnable, 0);
        GroupsActivity.timerHandler.postDelayed(GroupsActivity.timerRunnable, 0);
    }

}
