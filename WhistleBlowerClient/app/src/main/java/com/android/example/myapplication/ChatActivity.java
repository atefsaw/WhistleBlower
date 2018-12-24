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


    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
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
        Message message = new Message(textMessage, currentUser, currentGroup, true);

        if (textMessage.length() > 0) {
            // TODO: add a function to send this message to all participants
            message.setContent(textMessage);
            editText.getText().clear();
//            RestHandler.sendMessages(message);
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

        String userPhoneNumber = getIntent().getStringExtra("CURRENT_PHONE_NUMBER");
        ArrayList<String> messages = getIntent().getStringArrayListExtra("GROUP_MESSAGES");
        currentUser = new User(userPhoneNumber);

        ArrayList<User> users = new ArrayList<>();
        users.add(currentUser);
        users.add(new User("0"));

        String groupName = getIntent().getStringExtra("GROUP_NAME");
        Group group = new Group(users, groupName);
        this.setTitle(groupName);

        Message message = new Message(messages.get(0), currentUser,group, false );
        ArrayList<Message> messagesToSend = new ArrayList<>();
        messagesToSend.add(message);

        // Temporary until the server is ready
//        User currentUser = new User("1");
//        List<User> usersOfGroup = new ArrayList<User>();
//        usersOfGroup.add(atef);

        messageAdapter = new MessageListAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);


    }
}
