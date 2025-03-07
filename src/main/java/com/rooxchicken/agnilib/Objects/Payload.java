package com.rooxchicken.agnilib.Objects;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.rooxchicken.agnilib.AgniLib;

public abstract class Payload
{
    protected String id = "";

    public boolean sendOnUpdate = true;

    public Payload(String _id)
    {
        id = _id;
    }
    
    protected void checkAndSend(Player ... _players)
    {
        if(sendOnUpdate)
            sendData(_players);
    }

    // if null, it will send to all of the players in the `players` list
    protected abstract void _sendData(Player ... _players);
    public void sendData(Player ... _players)
    {
        _sendData(_players);
    }

    // if null, it will send to all of the players in the `players` list
    protected abstract void _destroy(Player ... _players);
    public void destory(Player ... _players)
    {
        _destroy(_players);
    }
}
