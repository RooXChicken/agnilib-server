package com.rooxchicken.agnilib.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.rooxchicken.agnilib.AgniLib;
import com.rooxchicken.agnilib.Events.PlayerKeybindEvent;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Keybinding {
    public static final short keybindID = 1;
    public static final short createKeybindID = 7;

    private HashMap<Player, HashSet<String>> justPressedKeys = new HashMap<Player, HashSet<String>>();
    private HashMap<Player, HashSet<String>> heldKeys = new HashMap<Player, HashSet<String>>();
    private HashMap<Player, HashSet<String>> justReleasedKeys = new HashMap<Player, HashSet<String>>();

    public void registerPlayer(Player _player) {
        justPressedKeys.put(_player, new HashSet<String>());
        heldKeys.put(_player, new HashSet<String>());
        justReleasedKeys.put(_player, new HashSet<String>());
    }

    public void unregisterPlayer(Player _player) {
        justPressedKeys.remove(_player);
        heldKeys.remove(_player);
        justReleasedKeys.remove(_player);
    }

    public boolean isJustPressed(Player _player, String _key) {
        return justPressedKeys.get(_player).contains(_key);
    }
    
    public boolean isPressed(Player _player, String _key) {
        return justPressedKeys.get(_player).contains(_key) || heldKeys.get(_player).contains(_key);
    }

    public boolean isJustReleased(Player _player, String _key) {
        return justReleasedKeys.get(_player).contains(_key);
    }

    public void registerKeyState(Player _player, String _category, String _key, KeyState _state) {
        PlayerKeybindEvent _event = new PlayerKeybindEvent(_player, _category, _key, _state);
        Bukkit.getPluginManager().callEvent(_event);

        if(_event.isCancelled())
            return;

        switch(_event.getState()) {
            case KeyState.JUST_PRESSED:
                justPressedKeys.get(_player).add(_key);
                heldKeys.get(_player).add(_key);
            break;
            case KeyState.JUST_RELEASED:
                if(justPressedKeys.get(_player).contains(_key))
                    justPressedKeys.get(_player).add(_key);

                heldKeys.get(_player).remove(_key);
                justReleasedKeys.get(_player).add(_key);
            break;
            
            default:
                break;
        }
    }

    public void tickKeys() {
        for(Entry<Player, HashSet<String>> _entry : justPressedKeys.entrySet())
            _entry.getValue().clear();

        for(Entry<Player, HashSet<String>> _entry : justReleasedKeys.entrySet())
            _entry.getValue().clear();
    }

    public void registerKeybind(Player _player, String _category, String _translation) {
        ByteBuf _buf = Unpooled.buffer();
        _buf.writeShort(createKeybindID);

        Parser.writeString(_category, _buf);
        Parser.writeString(_translation, _buf);

        AgniLib.sendData(_player, _buf.array());
    }
}
