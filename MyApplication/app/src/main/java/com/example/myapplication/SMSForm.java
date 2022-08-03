package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SMSForm extends AppCompatActivity {

    final int SEND_SMS_PERMISSION_CODE = 1;

    TextView phoneNumber;
    EditText textMessage;
    Button sendSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsform);

        phoneNumber = findViewById(R.id.phoneNumber);
        textMessage = findViewById(R.id.textMessage);
        sendSMS = findViewById(R.id.sendSMS);

        String sPhone = getIntent().getStringExtra("dataPhoneNum");
        phoneNumber.setText(sPhone);

        sendSMS.setEnabled(false);
        if(checkPerm(android.Manifest.permission.SEND_SMS)){
            sendSMS.setEnabled(true);
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
            SEND_SMS_PERMISSION_CODE);
        }

        sendSMS.setOnClickListener(v -> {
            String phone = phoneNumber.getText().toString();
            String messageContent = textMessage.getText().toString();

            if(messageContent.length() == 0) {
                Toast.makeText(getApplicationContext(), "Please fill in content for your message.", Toast.LENGTH_SHORT).show();
            }
            if(checkPerm(Manifest.permission.SEND_SMS)){
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone, null, messageContent, null, null);
                Toast.makeText(getApplicationContext(), "SMS message sent.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), RequestForm.class);
                startActivity(i);
            }else{
                Toast.makeText(getApplicationContext(), "No permission granted for SMS.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public boolean checkPerm(String perm){
        int checkPerm = ContextCompat.checkSelfPermission(this, perm);
        return checkPerm == PackageManager.PERMISSION_GRANTED;
    }
}