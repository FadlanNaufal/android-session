package com.example.loginsession;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    SharedPreferences sharedPreferences;

    public static final String KEYPREF = "Key Preferences";
    public static final String KEYUSERNAME = "Key Username";
    public static final String KEYPASSWORD = "Key Password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Latihan SharedPreferences");
        getSupportActionBar().setSubtitle("Belajar");

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        sharedPreferences = getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(KEYUSERNAME) && (sharedPreferences.contains(KEYPASSWORD))){
            username.setText(sharedPreferences.getString(KEYUSERNAME, ""));
            password.setText(sharedPreferences.getString(KEYPASSWORD,""));
        }

        Button button = findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEYUSERNAME,user);
                editor.putString(KEYPASSWORD,pass);
                editor.apply();
                Intent i = new Intent(MainActivity.this,HomeActivity.class);
                i.putExtra("User",user);
                startActivity(i);
                Toast.makeText(MainActivity.this, "Username & Password Setted", Toast.LENGTH_SHORT).show();
            }
        });

        Button button1 = findViewById(R.id.btn_clear);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
                password.setText("");
            }
        });
    }


}
