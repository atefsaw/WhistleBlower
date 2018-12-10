package com.android.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {


    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private List<Message> messageList = new ArrayList<>();
    private EditText editText;

    private Message lastSentMessage;

    public void sendMessage(View view) {
        String textMessage = editText.getText().toString();
        if (textMessage.length() > 0) {
            lastSentMessage.setContent(textMessage);
            messageList.add(lastSentMessage);
            editText.getText().clear();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);
        editText = (EditText) this.findViewById(R.id.edittext_chatbox);

        User atef = new User(1, "0523796040");
        List<User> usersOfGroup = new ArrayList<User>();
        usersOfGroup.add(atef);
        Group group = new Group(usersOfGroup, "UX-Group");
        this.setTitle(group.getName());

        lastSentMessage = new Message(null, atef, group);

//        button.setOnClickListener(new OnClickListener()
//        {
//            public void onClick(View v)
//            {
//                ImageView iv = (ImageView) findViewById(R.id.imageview1);
//                iv.setVisibility(View.VISIBLE);
//            }
//        });



        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        mMessageAdapter = new MessageListAdapter(this, messageList); // TODO: Get a messageList and show it
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
