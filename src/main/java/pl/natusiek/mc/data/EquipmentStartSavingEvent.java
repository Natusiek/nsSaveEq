package pl.natusiek.mc.data;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class EquipmentStartSavingEvent extends PlayerEvent {

    private static final HandlerList handlerList = new HandlerList();

    public EquipmentStartSavingEvent(Player who) {
        super(who);
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

}
