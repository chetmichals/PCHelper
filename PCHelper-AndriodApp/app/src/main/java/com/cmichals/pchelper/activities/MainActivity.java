package com.cmichals.pchelper.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cmichals.pchelper.R;
import com.cmichals.pchelper.connectionmanager.ServiceFinder;
import com.cmichals.pchelper.messagesender.SendMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private static final int CONNECTION_PORT_NUMBER = 21016;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendWindowForwardMessage(View view) {
        SendMessage.sendRightWindow();
    }

    public void sendWindowBackMessage(View view) {
        SendMessage.sendLeftWindow();
    }

    public void sendWindowSelectMessage(View view) {
        SendMessage.sendSelectWindow();
    }

    public void sentTabMessage(View view) {
        SendMessage.sendTab();
    }

    public void sendShiftTabMessage(View view) {
        SendMessage.sendShiftTab();
    }

    public void sendEnterMessage(View view) {
        SendMessage.sendEnter();
    }

    public void sendEscMessage(View view) {
        SendMessage.sendEsc();
    }

    public void sendTextMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.textEntry);
        String message = editText.getText().toString();
        editText.setText("");
        SendMessage.sendText(message);
    }

    public void openAudioAcivity(View view) {
        Intent intent = new Intent(this, AudioSwitcher.class);
        startActivity(intent);
    }
}
