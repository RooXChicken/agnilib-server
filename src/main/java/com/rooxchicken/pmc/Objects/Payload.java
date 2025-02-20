package com.rooxchicken.pmc.Objects;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.rooxchicken.pmc.PMC;

public abstract class Payload
{
    public ArrayList<Player> players;
    protected String id = "";

    public boolean sendOnUpdate = true;

    public Payload(String _id)
    {
        id = _id;
        players = new ArrayList<Player>();
    }
    
    protected void checkAndSend(@Nullable List<Player> _players)
    {
        if(sendOnUpdate)
            sendData(_players);
    }

    // if null, it will send to all of the players in the `players` list
    protected abstract void _sendData(@Nullable List<Player> _players);
    public void sendData(@Nullable List<Player> _players)
    {
        if(_players == null)
            _sendData(players);
        else
            _sendData(_players);
    }

    // if null, it will send to all of the players in the `players` list
    protected abstract void _destroy(@Nullable List<Player> _players);
    public void destory(@Nullable List<Player> _players)
    {
        if(_players == null)
            _destroy(players);
        else
            _destroy(_players);
    }
}
