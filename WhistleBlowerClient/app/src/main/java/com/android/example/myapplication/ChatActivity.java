package com.android.example.myapplication;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {


    private List<Message> messageList = new ArrayList<>();
    private EditText editText;

    private ArrayList<Message> messagesList;
    private int messageListIndex = 0;

    private MessageListAdapter messageAdapter;
    private ListView messagesView;

    private User currentUser;
    private Group currentGroup;

    ActionBar actionBar;

    public void sendMessage(View view) {
        String textMessage = editText.getText().toString();
        if (textMessage.length() > 0) {
            Message message = new Message(textMessage, currentUser, currentGroup, true);
            message.setContent(textMessage);
            editText.getText().clear();
            // TODO:        RestHandler.sendMessages(message);
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

        messagesList = new ArrayList<>();

        // this is where the message text goes
        editText = (EditText) this.findViewById(R.id.edittext_chatbox);

        // Get Intents
        String userPhoneNumber = getIntent().getStringExtra("CURRENT_PHONE_NUMBER");
        String groupName = getIntent().getStringExtra("GROUP_NAME");
        ArrayList<String> messages = getIntent().getStringArrayListExtra("GROUP_MESSAGES");

        // Set the group
        currentUser = new User(userPhoneNumber);
        User applicationAdmin = new User("0");
        ArrayList<User> users = new ArrayList<>();
        users.add(currentUser);
        users.add(applicationAdmin);
        currentGroup = new Group(users, groupName);

        // set the action bar with the group name
        this.setTitle(groupName);

        final Message welcomeMessage = new Message("This is a welcome message", currentUser, currentGroup, false );

        messageAdapter = new MessageListAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);

        messageAdapter.add(welcomeMessage);
        messagesView.setSelection(messagesView.getCount() - 1);

        /** TODO: runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageAdapter.add(welcomeMessage);
                // scroll the ListView to the last added element
                messagesView.setSelection(messagesView.getCount() - 1);
            }
        });*/
    }
}
