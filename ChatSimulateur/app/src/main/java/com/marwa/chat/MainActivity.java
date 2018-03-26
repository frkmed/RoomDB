package com.marwa.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.marwa.chat.DB.DbHelper;
import com.marwa.chat.Util.PreferencesHelper;

public class MainActivity extends AppCompatActivity {

    private PreferencesHelper pref;
    private DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = new PreferencesHelper(this, "CHAT_APP");
    }

    public void lancerLeChat(View view) {

        pref.setUserA(((EditText) findViewById(R.id.txtUserA)).getText().toString());
        pref.setUserB(((EditText) findViewById(R.id.txtUserB)).getText().toString());
        DB = DbHelper.getINSTANCE(this.getApplication());

        startActivity(new Intent(this, UserOneActivity.class));
    }

    public void intialiserChat(View view) {
        DB.chatDao().deleteAllMsg();
        DB.chatDao().deleteAllChat();
    }
}
