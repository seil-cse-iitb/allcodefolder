package com.example.sid.aravali_v_21;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText userNameET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameET = (EditText)findViewById(R.id.user_name_et);
        String user = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("username","");
        userNameET.setText(user);
        Button loginButton = (Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userNameET.getText().toString().trim().equals("")){
                    Toast.makeText(Login.this,"Enter Username",Toast.LENGTH_SHORT).show();
                }else {
                    //store username and check from server if it is valid

                    //TODO create a username and password database and check if the username is valid or not
                    //Store username
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("username", userNameET.getText().toString()).commit();
                    Intent goToMainPage = new Intent(Login.this, MainActivity.class);
                    startActivity(goToMainPage);
                    finish();
                }

            }
        });

    }
}
