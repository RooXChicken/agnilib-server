package com.rooxchicken.agnilib;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.util.Vector;
import org.bukkit.util.io.BukkitObjectInputStream;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.rooxchicken.agnilib.Commands.TestCommand;
import com.rooxchicken.agnilib.Data.Keybinding;
import com.rooxchicken.agnilib.Data.Parser;
import com.rooxchicken.agnilib.Data.PlayerModification;
import com.rooxchicken.agnilib.Data.Target;
import com.rooxchicken.agnilib.Events.PlayerKeybindEvent;
import com.rooxchicken.agnilib.Events.PlayerTargetEvent;
import com.rooxchicken.agnilib.Events.PlayerAgniLibInitializeEvent;
import com.rooxchicken.agnilib.Objects.Image;
import com.rooxchicken.agnilib.Objects.Payload;
import com.rooxchicken.agnilib.Objects.Text;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.VarInt;

public class AgniLib extends JavaPlugin implements Listener, PluginMessageListener
{
    public static AgniLib self;
    public static Keybinding keybinding;

    public static final int AgniLib_VERSION = 2;

    public static final String CHANNEL = "agnilib:channel";
    public static final short loginID = 0;

    @Override
    public void onEnable()
    {
        AgniLib.self = this;
        AgniLib.keybinding = new Keybinding();

        getServer().getPluginManager().registerEvents(this, this);
        initializeDataConnection();

        for(Player _player : Bukkit.getOnlinePlayers())
            initializePlayer(_player);

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
        {
            public void run()
            {
                keybinding.tickKeys();
            }
        }, 0, 1);

        getLogger().info("Allowing S2C & C2S since 1987! [made by roo]");
    }

    @Override
    public void onDisable()
    {
        for(Player _player : Bukkit.getOnlinePlayers())
            cleanupPlayer(_player);
    }

    public static void initializeDataConnection()
    {
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(AgniLib.self, AgniLib.CHANNEL);
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(AgniLib.self, AgniLib.CHANNEL, AgniLib.self);
    }

    private void initializePlayer(Player _player)
    {
        ByteBuf _loginReponse = Unpooled.buffer();
        VarInt.write(_loginReponse, loginID);
        VarInt.write(_loginReponse, AgniLib_VERSION);

        sendData(_player, _loginReponse.array());

        keybinding.registerPlayer(_player);

        PlayerAgniLibInitializeEvent _event = new PlayerAgniLibInitializeEvent(_player);
        Bukkit.getPluginManager().callEvent(_event);
    }

    public void reninitializePlayers()
    {
        for(Player _player : Bukkit.getOnlinePlayers())
            initializePlayer(_player);
    }

    @EventHandler
    private void registerPlayer(PlayerJoinEvent event)
    {
        initializePlayer(event.getPlayer());
    }

    @EventHandler
    private void unregisterPlayer(PlayerQuitEvent event)
    {
        cleanupPlayer(event.getPlayer());
    }

    private void cleanupPlayer(Player _player)
    {
        keybinding.unregisterPlayer(_player);
    }

    private static boolean checkPlayer(Player _player)
    {
        if(_player == null || !_player.isValid())
        {
            Bukkit.getLogger().warning("Player is not valid or null!");
            return false;
        }

        return true;
    }

    public static void sendData(Player _player, byte[] _data)
    {
        if(!checkPlayer(_player))
            return;
        
        ByteBuf _buf = Unpooled.buffer(0);
        VarInt.write(_buf, _data.length);

        byte[] _lengthData = new byte[_buf.readableBytes()];
        for(int i = 0; i < _lengthData.length; i++)
            _lengthData[i] = _buf.readByte();

        ByteArrayDataOutput _out = ByteStreams.newDataOutput();
        _out.write(_lengthData);
        _out.write(_data);
            
        _player.sendPluginMessage(self, CHANNEL, _out.toByteArray());
    }

    @Override
    public void onPluginMessageReceived(String _channel, Player _player, byte[] _data)
    {
        if(!_channel.equals(CHANNEL))
            return;
        
        ByteBuf _buf = Unpooled.copiedBuffer(_data);

        int _size = _buf.readInt();
        short _status = _buf.readShort();

        switch(_status)
        {
            case loginID:
                initializePlayer(_player);
            break;

            case Keybinding.keybindID:
                AgniLib.keybinding.registerKeyState(_player, Parser.readString(_buf), Parser.readString(_buf), _buf.readByte());
            break;

            case PlayerModification.playerModification:
                short _modType = _buf.readShort();
                switch(_modType)
                {
                    case PlayerModification.playerGetTarget:
                        boolean _hit = _buf.readBoolean();
                        if(!_hit)
                        {
                            PlayerModification.playerTargetMap.put(_player, null);
                            break;
                        }

                        Vector _hitPos = new Vector(_buf.readDouble(), _buf.readDouble(), _buf.readDouble());
                        
                        Entity _entity = null;
                        Block _block = null;
                        String _uuid = Parser.readString(_buf);

                        if(!_uuid.equals(""))
                            _entity = Bukkit.getEntity(UUID.fromString(_uuid));
                        else
                            _block = new Location(_player.getWorld(), _hitPos.getX(), _hitPos.getY(), _hitPos.getZ()).getBlock();
                        
                        Target _target = new Target(_block, _entity, _hitPos);
                        PlayerTargetEvent _event = new PlayerTargetEvent(_player, _target);
                        Bukkit.getPluginManager().callEvent(_event);

                        if(_event.isCancelled())
                            break;

                        PlayerModification.playerTargetMap.put(_player, _event.getTarget());
                    break;
                }
            break;
        }
    }
}
