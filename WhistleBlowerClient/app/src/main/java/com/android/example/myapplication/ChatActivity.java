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
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.chat_title_bar);

        // this is where the message text goes
        editText = (EditText) this.findViewById(R.id.edittext_chatbox);

        // Temporary until the server is ready
        User atef = new User(1, "0523796040");
        List<User> usersOfGroup = new ArrayList<User>();
        usersOfGroup.add(atef);
        Group group = new Group(usersOfGroup, "Software and UX Course");
        this.setTitle(group.getName());
        currentUser = atef;
        currentGroup = group;

        messageAdapter = new MessageListAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);

    }
}
