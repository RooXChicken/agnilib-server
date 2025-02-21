package com.rooxchicken.pmc.Events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerKeybindEvent extends Event implements Cancellable
{
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private String key;
    private byte state;
    private boolean isCancelled;

    public PlayerKeybindEvent(String _key, byte _state)
    {
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
}
