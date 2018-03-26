package com.marwa.roomdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.marwa.roomdb.db.AppPreferencesHelper;
import com.marwa.roomdb.db.DbHelper;
import com.marwa.roomdb.db.User;

public class LoginActivity extends AppCompatActivity {

    private DbHelper DB;
    private AppPreferencesHelper pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DB = DbHelper.getDatabase(this.getApplication());

        pref = new AppPreferencesHelper(this, "RoomDB");

        if (pref.getLogged() != false) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    public void seConnecter(View view) {

        String userName = ((EditText) findViewById(R.id.txtUserName)).getText().toString();
        String password = ((EditText) findViewById(R.id.txtPassword)).getText().toString();

        if (userName == null || userName.isEmpty()) {
            Toast.makeText(this, "Le nom d'utilisateur est vide", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password == null || password.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir le mot de passe", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = DB.userDao().testLogin(userName, password);

        if (user != null) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                pref.setLogged(true);
                pref.setUserId(user.getId());

                Toast.makeText(this, "Vous etes bien connectée", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        } else {
            Toast.makeText(this, "L'utilisateur n'existe pas !", Toast.LENGTH_SHORT).show();
        }
    }

    public void creerCompte(View view) {
        startActivity(new Intent(this, InscriptionActivity.class));
    }
}
