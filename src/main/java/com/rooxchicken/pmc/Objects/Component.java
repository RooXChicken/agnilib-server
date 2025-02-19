package com.rooxchicken.pmc.Objects;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.rooxchicken.pmc.PMC;
import com.rooxchicken.pmc.Data.Parser;

import net.minecraft.network.VarInt;

public abstract class Component extends Payload
{
    private static final short componentID = 1;

    protected double posX = 0;
    protected double posY = 0;

    protected double scaleX = 1;
    protected double scaleY = 1;

    public Component(String _id, double _posX, double _posY, double _scaleX, double _scaleY)
    {
        super(_id);

        posX = _posX;
        posY = _posY;

        scaleX = _scaleX;
        scaleY = _scaleY;
    }

    public void setPosition(double _posX, double _posY, @Nullable List<Player> _players)
    {
        if(_posX == posX && _posY == posY)
            return;

        posX = _posX;
        posY = _posY;
        
        checkAndSend(_players);
    }

    public void setScale(double _scaleX, double _scaleY, @Nullable List<Player> _players)
    {
        if(_scaleX == scaleX && _scaleY == scaleY)
            return;

        scaleX = _scaleX;
        scaleY = _scaleY;
        
        checkAndSend(_players);
    }

    @Override
    protected void _sendData(List<Player> _players)
    {
        for(Player _player : _players)
            PMC.sendData(_player, Parser.parseData(componentID, id, posX + "", posY + "", scaleX + "", scaleY + ""));
    }
}
