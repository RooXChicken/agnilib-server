package com.rooxchicken.agnilib.Data;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.rooxchicken.agnilib.AgniLib;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class PlayerModification
{
    public static final short playerModification = 8;

    public static final short playerSetVelocity = 0;
    public static final short playerGetVelocity = 1;

    public static final short playerGetTarget = 3;

    public static HashMap<Player, Vector> playerVelocityMap = new HashMap<Player, Vector>();
    public static HashMap<Player, Target> playerTargetMap = new HashMap<Player, Target>();
    
    public static void setPlayerVelocity(Vector _velocity, Player ... _players)
    {
        for(Player _player : _players)
            playerVelocityMap.put(_player, _velocity);
    }

    public static Vector getPlayerVelocity(Player _player)
    {
        return playerVelocityMap.containsKey(_player) ? playerVelocityMap.get(_player).clone() : new Vector(0, 0, 0);
    }

    public static void sendPlayerVelocity(Player ... _players)
    {
        for(Player _player : _players)
        {
            ByteBuf _buf = Unpooled.buffer();
            _buf.writeShort(playerModification);
            _buf.writeShort(playerSetVelocity);

            Vector _velocity = getPlayerVelocity(_player);

            _buf.writeDouble(_velocity.getX());
            _buf.writeDouble(_velocity.getY());
            _buf.writeDouble(_velocity.getZ());

            AgniLib.sendData(_player, _buf.array());
        }
    }

    public static Target getPlayerTarget(Player _player)
    {
        return playerTargetMap.containsKey(_player) ? playerTargetMap.get(_player).clone() : null;
    }
}
