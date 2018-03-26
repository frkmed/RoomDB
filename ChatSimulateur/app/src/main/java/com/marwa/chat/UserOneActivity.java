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

public class UserOneActivity extends AppCompatActivity {
    public final static int CHAT = 0;

    private DbHelper DB;
    private PreferencesHelper pref;

    private RecyclerView mRecyclerView;
    private ChatAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Chat> chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_one);
        pref = new PreferencesHelper(this, "CHAT_APP");
        setTitle("User :"+pref.getuserA());

        mRecyclerView = findViewById(R.id.chat_RecyclerA);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        loadData();
    }

    public void openActivity(View view) {

        Chat chat = new Chat();

        chat.setName(pref.getuserA());
        chat.setMsg(new Msg(((EditText) findViewById(R.id.txtTextToSendA)).getText().toString(), new Date()));

        DB.chatDao().insert(chat);

        mAdapter.notifyDataSetChanged();
        loadData();

        startActivityForResult(
                new Intent(this, UserTwoActivity.class), CHAT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHAT) {
            if (resultCode == RESULT_OK) {
                loadData();
            }
        }
    }


    public void loadData() {

        DB = DbHelper.getINSTANCE(this.getApplication());
        chatList = DB.chatDao().loadAllMsgByName(pref.getuserA(),pref.getuserB());
        mAdapter = new ChatAdapter(chatList, this,pref.getuserA(),pref.getuserB());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }




}
