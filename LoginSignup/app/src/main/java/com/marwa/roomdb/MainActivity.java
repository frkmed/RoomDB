package com.marwa.roomdb;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.marwa.roomdb.db.AppPreferencesHelper;
import com.marwa.roomdb.db.DbHelper;
import com.marwa.roomdb.db.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppPreferencesHelper pref;
    private DbHelper DB;

    private ListView listView;
    private List<User> usersList;
    private List<String> adapterList;
    private User user;

    private int itemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = DbHelper.getDatabase(this.getApplication());
        pref = new AppPreferencesHelper(this, "RoomDB");

        usersList = DB.userDao().getAll();
        user = DB.userDao().getUserById(pref.getuserId());

        listView = findViewById(R.id.listView);

        ((TextView) findViewById(R.id.txtUserName)).setText(user.getUserName());
        ((TextView) findViewById(R.id.txtUserEmail)).setText(user.getUsrEmail());


        adapterList=new ArrayList<>();
        for (User item : usersList) {
            adapterList.add(item.getUserName() + " / " + item.getUsrEmail());
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, adapterList);

        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                user = usersList.get(position);
                itemPosition = position;

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("Voulez-vous supprimer cet enregistrement?")
                        .setCancelable(false)
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                DB.userDao().delete(user);
                                usersList.remove(itemPosition);
                                adapter.remove(user.getUserName() + " / " + user.getUsrEmail());

                                if (pref.getuserId() == user.getId()) {
                                    pref.setLogged(false);
                                    pref.setUserId(0);
                                    seDeconnecter(new View(getApplicationContext()));
                                }
                            }
                        })
                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
                return false;
            }
        });
    }

    public void seDeconnecter(View view) {
        pref.setLogged(false);
        pref.setUserId(0);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


}
