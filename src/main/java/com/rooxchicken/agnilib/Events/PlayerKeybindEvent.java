package com.rooxchicken.agnilib.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerKeybindEvent extends Event implements Cancellable
{
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private Player player;
    private String category;
    private String key;

    private byte state;

    private boolean isCancelled;

    public PlayerKeybindEvent(Player _player, String _category, String _key, byte _state)
    {
        player = _player;
        category = _category;
        key = _key;
        
        state = _state;
    }
    
	@Override
	public boolean isCancelled()
    {
        return isCancelled;
	}

	@Override
	public void setCancelled(boolean _cancel)
    {
        isCancelled = _cancel;
	}

	@Override
	public HandlerList getHandlers()
    {
        return HANDLERS_LIST;
	}
    
    public static HandlerList getHandlerList()
    {
        return HANDLERS_LIST;
    }

    public String getKey()
    {
        return key;
    }

    public byte getState()
    {
        return state;
    }

    public void setState(byte _state)
    {
        state = _state;
    }

    public Player getPlayer()
    {
        return player;
    }

    public String getCategory()
    {
        return category;
    }
}
