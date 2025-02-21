package com.rooxchicken.pmc.Data;

import java.util.HashSet;

import org.bukkit.Bukkit;

import com.rooxchicken.pmc.Events.PlayerKeybindEvent;

public class Keybinding
{
    public static final short keybindID = 1;

    private HashSet<String> justPressedKeys = new HashSet<String>();
    private HashSet<String> heldKeys = new HashSet<String>();
    private HashSet<String> justReleasedKeys = new HashSet<String>();

    public boolean isJustPressed(String _key)
    {
        return justPressedKeys.contains(_key);
    }
    
    public boolean isPressed(String _key)
    {
        return justPressedKeys.contains(_key) || heldKeys.contains(_key);
    }

    public boolean isJustReleased(String _key)
    {
        return justReleasedKeys.contains(_key);
    }

    public void registerKeyState(String _key, byte _state)
    {
        PlayerKeybindEvent _event = new PlayerKeybindEvent(_key, _state);
        Bukkit.getPluginManager().callEvent(_event);
        if(_event.isCancelled())
            return;

        switch(_event.getState())
        {
            case 1:
                justPressedKeys.add(_key);
                heldKeys.add(_key);
            break;
            case 2:
                if(justPressedKeys.contains(_key))
                    justPressedKeys.remove(_key);

                heldKeys.remove(_key);
                justReleasedKeys.add(_key);
            break;
        }
    }

    public void tickKeys()
    {
        justPressedKeys.clear();
        justReleasedKeys.clear();
    }
}
