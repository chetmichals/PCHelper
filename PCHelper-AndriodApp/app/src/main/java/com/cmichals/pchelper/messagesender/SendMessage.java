package com.cmichals.pchelper.messagesender;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chet on 4/23/2017.
 */

public class SendMessage {

    private final static String testHost = "192.168.0.3";
    private final static int testPort =  21016;

    public static void sendRightWindow() {
        sendGenericMessage("{'command':'windowForward'}");
    }

    public static void sendLeftWindow() {
        sendGenericMessage("{'command':'windowBack'}");
    }

    public static void sendSelectWindow() {
        sendGenericMessage("{'command':'selectWindow'}");
    }

    public static void sendTab() {
        sendGenericMessage("{'command':'tab'}");
    }

    public static void sendShiftTab() {
        sendGenericMessage("{'command':'shiftTab'}");
    }

    public static void sendEnter() {
        sendGenericMessage("{'command':'pressEnter'}");
    }

    public static void sendEsc() {
        sendGenericMessage("{'command':'pressEsc'}");
    }

    public static void sendMusicPlay() {
        sendGenericMessage("{'command':'playMusic'}");
    }

    public static void sendMusicStop() {
        sendGenericMessage("{'command':'stopMusic'}");
    }

    public static void sendMusicRandom() {
        sendGenericMessage("{'command':'randomSong'}");
    }

    public static void sendDeviceNext() {
        sendGenericMessage("{'command':'playbackDeviceNext'}");
    }

    public static void sendDevicePrevious() {
        sendGenericMessage("{'command':'playbackDevicePrevious'}");
    }

    public static void sendText(String text) {
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("command", "textSend");
        messageMap.put("text", text);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(messageMap);
        sendGenericMessage(json);
    }


    private static void sendGenericMessage(final String message) {
        new Thread(new Runnable() {
            public void run() {
                Socket socket = null;
                try {
                    socket = new Socket(testHost, testPort);
                    DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
                    dOut.writeUTF(message);
                    dOut.flush();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
