package com.android.example.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;


/**
 * This activity responsible on the chat page
 */
public class ChatActivity extends AppCompatActivity {

    private List<Message> messagesList;
    private EditText editText;

    private MessageListAdapter messageAdapter;
    private ListView messagesView;

    private User currentUser;
    private Group currentGroup;
    private String groupId;

    public ChatViewModel chatViewModel;
    ActionBar actionBar;

    private final static int MESSAGE_POLLING_INTERVAL = 300;

    /**
     * This method sends a message to the group.
     */
    public void sendMessage(View view) {
        String textMessage = editText.getText().toString();
        if (textMessage.length() > 0) {
            Message message = new Message(textMessage, currentUser.getUserId(), Integer.parseInt(groupId), true);
            message.setContent(textMessage);
            editText.getText().clear();
            RestHandler.sendMessages(message);
            chatViewModel.insertMessage(message);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(getString(R.string.action_bar_color))));

        // this is where the message text goes
        editText = (EditText) this.findViewById(R.id.edittext_chatbox);

        // Get Intents
        String userPhoneNumber = getIntent().getStringExtra(getString(R.string.phoneNumberIntentKey));
        this.groupId = getIntent().getStringExtra(getString(R.string.groupIdIntentKey));
        String groupName = getIntent().getStringExtra(getString(R.string.groupNameIntentKey));
//        ArrayList<String> messages = getIntent().getStringArrayListExtra(getString(R.string.groupMessagesIntentKey));
        messageAdapter = new MessageListAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);


        chatViewModel = ViewModelProviders.of(this, new ViewModelFactory(this.getApplication(), Integer.parseInt(this.groupId))).get(ChatViewModel.class);


        chatViewModel.getAllMessagesForGroup().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(@Nullable List<Message> groupMessages) {
                messageAdapter.add(groupMessages);
            }
        });

        // Set the group
        currentUser = new User(userPhoneNumber);
        User applicationAdmin = new User(getString(R.string.application_user_id));
        ArrayList<User> users = new ArrayList<>();
        users.add(currentUser);
        users.add(applicationAdmin);

        ArrayList<String> usersIDs = new ArrayList<>();
        for (User user : users) {
            usersIDs.add(user.getUserId());
        }

        // set the action bar with the group name
        this.setTitle(groupName);


        final Handler timerHandler = new Handler();
        Runnable timerRunnable = new Runnable() {
            @Override
            public void run() {
                long millis = System.currentTimeMillis();
                messagesList = RestHandler.pullMessages(currentUser.getUserId(), Integer.parseInt(groupId));
                for (Message receievedMessage : messagesList) {
                    chatViewModel.insertMessage(receievedMessage);
//                    messagesView.setSelection(messagesView.getCount() - 1);
                }
                timerHandler.postDelayed(this, MESSAGE_POLLING_INTERVAL);
            }
        };
        timerHandler.postDelayed(timerRunnable, 0);
    }

    /**
     * this is a thread task that responsible for polling the messages from the server.
     */
    private class GetMessagesTask extends TimerTask {

        public void run() {
            messagesList = RestHandler.pullMessages(currentUser.getUserId(), currentGroup.getId());
            for (Message receievedMessage : messagesList) {
               chatViewModel.insertMessage(receievedMessage);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GroupsActivity.timerHandler.postDelayed(GroupsActivity.timerRunnable, 0);
    }

}
