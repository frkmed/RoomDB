package com.marwa.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.marwa.chat.Util.adapter.ChatAdapter;
import com.marwa.chat.DB.entity.Chat;
import com.marwa.chat.DB.DbHelper;
import com.marwa.chat.DB.entity.Msg;
import com.marwa.chat.Util.PreferencesHelper;

import java.util.Date;
import java.util.List;

public class UserTwoActivity extends AppCompatActivity {
    public final static int CHAT = 0;

    private DbHelper DB;

    private RecyclerView mRecyclerView;
    private ChatAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private String searchString = "";
    private List<Chat> chatList;


    private PreferencesHelper pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_two);
        pref = new PreferencesHelper(this, "CHAT_APP");
        setTitle("User :"+pref.getuserA());

        mRecyclerView = findViewById(R.id.chat_RecyclerB);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        loadData();
    }


    public void respondActivity(View view) {
        Chat chat = new Chat();

        chat.setName(pref.getuserB());
        chat.setMsg(new Msg(((EditText) findViewById(R.id.txtTextToSendB)).getText().toString(), new Date()));

        DB.chatDao().insert(chat);

        mAdapter.notifyDataSetChanged();

        loadData();

        setResult(RESULT_OK, new Intent(this, UserTwoActivity.class));
        super.finish();
    }


    public void loadData() {
        DB = DbHelper.getINSTANCE(this.getApplication());
        chatList = DB.chatDao().loadAllMsgByName(pref.getuserA(),pref.getuserB());

        mAdapter = new ChatAdapter(chatList, this, pref.getuserA(), pref.getuserB());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


}
