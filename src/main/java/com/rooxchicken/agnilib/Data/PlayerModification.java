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

    public static HashMap<Player, Vector> playerVelocityMap = new HashMap<Player, Vector>();
    
    public static void setPlayerVelocity(Vector _velocity, Player ... _players)
    {
        for(Player _player : _players)
        {
            playerVelocityMap.put(_player, _velocity);
            // Bukkit.getLogger().info("Setting velocity: " + _velocity.getX() + " | " + _velocity.getY() + " | " + _velocity.getZ() + " for " + _player.getName());
        }
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

            // Bukkit.getLogger().info("SENDING velocity: " + _velocity.getX() + " | " + _velocity.getY() + " | " + _velocity.getZ() + " for " + _player.getName());

            AgniLib.sendData(_player, _buf.array());
        }
    }
}
