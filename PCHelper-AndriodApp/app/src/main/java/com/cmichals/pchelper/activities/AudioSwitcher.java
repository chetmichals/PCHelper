package com.cmichals.pchelper.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cmichals.pchelper.R;
import com.cmichals.pchelper.messagesender.SendMessage;

public class AudioSwitcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_switcher);
    }

    public void musicPlay(View view) {
        SendMessage.sendMusicPlay();
    }

    public void musicStop(View view){
        SendMessage.sendMusicStop();
    }

    public void musicRandom(View view) {
        SendMessage.sendMusicRandom();
    }

    public void deviceNext(View view) {
        SendMessage.sendDeviceNext();
    }

    public void devicePrevious(View view) {
        SendMessage.sendDevicePrevious();
    }
}
