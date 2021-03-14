package me.katnissali.bungeechat.Util;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import java.io.*;

public class BungeeUtil {

    public static String getChannel(byte[] msg) {
        ByteArrayDataInput in = ByteStreams.newDataInput(msg);
        String subChannel = in.readUTF();
        return subChannel;
    }
    public static String getMessage(byte[] msg) {
        ByteArrayDataInput in = ByteStreams.newDataInput(msg);
        in.readUTF();

        short len = in.readShort();
        byte[] msgbytes = new byte[len];
        in.readFully(msgbytes);

        DataInputStream msgin = new DataInputStream(new ByteArrayInputStream(msgbytes));
        try {
            String somedata = msgin.readUTF();
            return somedata;
        } catch (IOException e) {
            e.printStackTrace();
        } // Read the data in the same way you wrote it
        return null;
    }

    public static void sendPluginMessage(String subChannel, String message) {
        if(Util.getRandomPlayer() != null) {

            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Forward");
            out.writeUTF("ALL");
            out.writeUTF(subChannel);
            ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
            DataOutputStream msgout = new DataOutputStream(msgbytes);
            try {
                msgout.writeUTF(message);
                msgout.writeShort(message.length());
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.writeShort(msgbytes.toByteArray().length);
            out.write(msgbytes.toByteArray());

            Util.getRandomPlayer().sendPluginMessage(Util.getMain(), "BungeeCord", out.toByteArray());
        }
    }


}
