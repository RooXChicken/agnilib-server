package com.rooxchicken.agnilib.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerAgniLibInitializeEvent extends Event {
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private Player player;

    public PlayerAgniLibInitializeEvent(Player _player) {
        player = _player;
    }

	@Override
	public HandlerList getHandlers() {
        return HANDLERS_LIST;
	}
    
    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    public Player getPlayer() {
        return player;
    }
}
