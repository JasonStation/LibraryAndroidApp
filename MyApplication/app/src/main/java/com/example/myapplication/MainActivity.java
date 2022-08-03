package com.example.myapplication;

//2440051574 - Jason Leonardo Sutioso

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editPass;
    private EditText editPhone;
    private TextView editDob;
    private Button regButton, loginButton;
    public static ArrayList<Integer> userId = new ArrayList<Integer>();
    public static ArrayList<String> emailAddress = new ArrayList<String>();
    public static ArrayList<String> passwords = new ArrayList<String>();
    public static ArrayList<String> phoneNumbers = new ArrayList<String>();
    public static ArrayList<String> birthDate = new ArrayList<String>();
    public static int numberOfAccounts = 0;
    public static int loggedAccount = 0;
    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(userId.isEmpty()){
            userId.add(0);
            emailAddress.add("testaccount@test.com");
            passwords.add("12345678");
            phoneNumbers.add("+62123456789");
            birthDate.add("-");
        }

        editEmail = findViewById(R.id.enterEmail);
        editPass = findViewById(R.id.enterPass);
        regButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginbutton);

        database = new DatabaseHelper(getApplicationContext());

        Cursor cur = database.viewDataUser();

        if(cur.getCount() == 0) Toast.makeText(getApplicationContext(), "Nothing here.", Toast.LENGTH_SHORT).show();
        else{
            while(cur.moveToNext()){
                userId.add(cur.getInt(0));
                phoneNumbers.add(cur.getString(1));
                emailAddress.add(cur.getString(2));
                passwords.add(cur.getString(3));
                birthDate.add(cur.getString(4));
            }
        }

        regButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), MainActivityRegister.class);
                startActivity(intent);
            }

        });

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String iemail = editEmail.getText().toString();
                String ipass = editPass.getText().toString();
                if(emailAddress.contains(iemail) && passwords.contains(ipass)){
                    loggedAccount = userId.get(emailAddress.indexOf(iemail));
                    Toast.makeText(getApplicationContext(), "Login successful with UserID " + loggedAccount, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), MainForm.class);
                    startActivity(i);
                }
                    TextView warning = findViewById(R.id.loginWarning);
                    warning.setText("Invalid email or password");
                    //Toast.makeText(getApplicationContext(), emailAddress.get(0) + " " + iemail, Toast.LENGTH_SHORT).show();

            }

        });

    }


}