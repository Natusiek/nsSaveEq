package pl.natusiek.mc.listener;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.PlayerInventory;

import pl.natusiek.mc.data.EquipmentData;
import pl.natusiek.mc.data.EquipmentDataManager;
import pl.natusiek.mc.helper.location.SafeLocations;

public class SavingEquipment implements Listener {

    public static final Set<UUID> savingEquipment = new HashSet<>();
    private final EquipmentDataManager equipmentDataManager;


    public SavingEquipment(EquipmentDataManager equipmentDataManager) {
        this.equipmentDataManager = equipmentDataManager;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.savingEquipment.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler//(priority = EventPriority.LOWEST)
    public void onClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) {
            return;
        }
        final Player player = (Player) event.getPlayer();
        if (!this.savingEquipment.contains(player.getUniqueId())) {
            return;
        }
        final EquipmentData data = this.equipmentDataManager.createEquipmentData(player.getUniqueId(),
                player.hasPermission("natusiek.vip"));

        final PlayerInventory inventory = player.getInventory();
    /*if (EquipmentData.hasIllegalArmor(inventory.getArmorContents())
        || EquipmentData.hasIllegalInventory(inventory.getContents())) {
      player.sendMessage(ChatColor.RED +
          "Posiadasz jakis niedozwolony item, ktory nie istnieje w defaultowym zestawie!");
      //player.openInventory(player.getInventory());
      return;
    }*/

        data.setArmor(inventory.getArmorContents());
        data.setInventory(inventory.getContents());

   /* player.getInventory().clear();
    player.getInventory().setContents(data.getInventory());
    player.getInventory().setArmorContents(data.getArmor());*/

        player.teleport(SafeLocations.SPAWN_LOCATION);
        player.sendMessage(ChatColor.YELLOW + "Zapisales swoj zestaw! =)");
        this.savingEquipment.remove(player.getUniqueId());

    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        final Player player = (Player) event.getEntity();
        if (this.savingEquipment.contains(player.getUniqueId())) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Nie mozesz sie bic podczas ustawiania ekwipunku!");
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onItemDrop(PlayerPickupItemEvent event) {
        final Player player = event.getPlayer();
        if (this.savingEquipment.contains(player.getUniqueId())) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Nie mozesz podnosic itemow podczas ustawiania ekwipunku!");
        }
    }


    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onItemPickup(PlayerDropItemEvent event) {
        final Player player = event.getPlayer();
        if (this.savingEquipment.contains(player.getUniqueId())) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Nie mozesz wyrzucac itemow podczas ustawiania ekwipunku!");
        }
    }

}
