//package com.android.example.myapplication;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.Window;
//import android.view.WindowManager;
//
//public class ChatActivity extends AppCompatActivity {
//
//
//    private RecyclerView mMessageRecycler;
//    private MessageListAdapter mMessageAdapter;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // Hides default android application name on top, to enable us to show the group name
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
//        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//        //                          WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//
//
//        setContentView(R.layout.activity_chat);
//
//        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
//        mMessageAdapter = new MessageListAdapter(this, messageList);
//        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
//    }
//}
