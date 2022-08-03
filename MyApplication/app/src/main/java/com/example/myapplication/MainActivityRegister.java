package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.CheckBox;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class MainActivityRegister extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);
        Button datePicker = findViewById(R.id.regDatePicker);
        datePicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment df = new DatePickerFragment();
                df.show(getSupportFragmentManager(), "date picker");
            }

        });

        Button regButton = findViewById(R.id.regButton);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.regEmail);
                String semail = email.getText().toString();

                EditText pass = findViewById(R.id.regPass);
                String spass = pass.getText().toString();

                EditText phone = findViewById(R.id.regPhone);
                String sphone = phone.getText().toString();

                TextView dateText = findViewById(R.id.dateText);
                String sdateText = dateText.getText().toString();

                boolean passwordHasNumber = spass.matches(".*[0-9].*");
                boolean passwordHasAlphabet = spass.matches(".*[a-zA-Z].*");

                CheckBox agree = findViewById(R.id.agreeCheckbox);

                TextView warning = (TextView) findViewById(R.id.regWarning);

                DatabaseHelper database = new DatabaseHelper(getApplicationContext());

                if(!semail.contains("@") || !semail.contains(".")){
                    warning.setText("Please enter a valid email address");
                }
                else if(spass.length() < 8){
                    warning.setText("Password must at least have 8 characters");
                }
                else if(!passwordHasAlphabet || !passwordHasNumber){
                    warning.setText("Password must contain an alphabet and a number");
                }
                else if(!sphone.startsWith("+62")){
                    warning.setText("Phone number must start with '+62'");
                }
                else if(sphone.length() < 10 || sphone.length() > 15){
                    warning.setText("Phone number must be at least 10 or below 15 characters");
                }
                else if(!agree.isChecked()){
                    warning.setText("Please accept the license agreement");
                }
                else if(sdateText.equals("Choose date")){
                    warning.setText("Please choose birth date");
                }
                else if(MainActivity.emailAddress.contains(semail)){
                    warning.setText("That email address has already been registered");
                }
                else if(MainActivity.phoneNumbers.contains(spass)){
                    warning.setText("That phone number has already been registered");
                }
                else {
                    MainActivity.numberOfAccounts++;
                    database.insertDataUser(semail, spass, sphone, sdateText);
                    Toast.makeText(getApplicationContext(), "Account successfully created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        String currDate = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());

        TextView dateChosen = (TextView) findViewById(R.id.dateText);
        dateChosen.setText(currDate);
    }
}