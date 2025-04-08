package com.rooxchicken.agnilib.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.rooxchicken.agnilib.Data.Target;

public class PlayerTargetEvent extends Event {
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private Player player;
    private Target target;

    private boolean isCancelled = false;

    public PlayerTargetEvent(Player _player, Target _target) {
        player = _player;
        target = _target;
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

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target _target) {
        target = _target;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean _cancelled) {
        isCancelled = _cancelled;
    }
}
