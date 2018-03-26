package com.marwa.roomdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.marwa.roomdb.db.AppPreferencesHelper;
import com.marwa.roomdb.db.DbHelper;
import com.marwa.roomdb.db.User;

public class InscriptionActivity extends AppCompatActivity {

    private EditText txtUserName;
    private EditText txtPassword;
    private EditText txtUserEmail;

    private DbHelper DB;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        DB = DbHelper.getDatabase(this.getApplication());
    }

    public void sInscrire(View view) {

        String userName = ((EditText) findViewById(R.id.txtUserName)).getText().toString();
        String userEmail = ((EditText) findViewById(R.id.txtUserEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.txtPassword)).getText().toString();


        if (userName == null || userName.isEmpty()) {
            Toast.makeText(this, "Le nom d'utilisateur est vide", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userEmail == null || userEmail.isEmpty()) {
            Toast.makeText(this, "L'adresse mail est obligatoire", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password == null || password.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir le mot de passe", Toast.LENGTH_SHORT).show();
            return;
        }

        user = new User();
        user.setUserName(userName);
        user.setUsrEmail(userEmail);
        user.setPassword(password);

        DB.userDao().insert(user);

        Toast.makeText(this, "Compte bien Créer", Toast.LENGTH_SHORT).show();

        this.finish();
    }


}
