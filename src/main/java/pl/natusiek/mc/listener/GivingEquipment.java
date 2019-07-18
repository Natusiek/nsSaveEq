package pl.natusiek.mc.listener;

import java.util.Arrays;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import pl.natusiek.mc.data.EquipmentData;
import pl.natusiek.mc.data.EquipmentDataManager;
import pl.natusiek.mc.data.EquipmentStartSavingEvent;

public class GivingEquipment implements Listener {

    private final EquipmentDataManager equipmentDataManager;

    public GivingEquipment(EquipmentDataManager equipmentDataManager) {
        this.equipmentDataManager = equipmentDataManager;
    }

  /*@EventHandler
  public void onJoin(PlayerJoinEvent event) {
    final Player player = event.getPlayer();
    this.giveEquipment(player);
  }

  @EventHandler
  public void onDeath(PlayerRespawnEvent event) {
    Bukkit.getScheduler().runTaskLater(SaveEquipmentPlugin.getInstance(),
        () -> this.giveEquipment(event.getPlayer()), 35L);
  }*/

    @EventHandler
    public void onEquipmentStartSaving(EquipmentStartSavingEvent event) {
        this.giveEquipment(event.getPlayer());
    }

    private void giveEquipment(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        final EquipmentData data = this.equipmentDataManager.createEquipmentData(player.getUniqueId(),
                player.hasPermission("casual-saveeq.vip"));

        final ItemStack[] inventory = data.getInventory();
        Arrays.stream(inventory)
                .filter(eq -> eq != null && eq.getAmount() < 1)
                .forEach(eq -> eq.setAmount(1));
        player.getInventory().setContents(data.getInventory());

        final ItemStack[] armor = data.getArmor();
        Arrays.stream(armor)
                .filter(eq -> eq != null && eq.getAmount() < 1)
                .forEach(eq -> eq.setAmount(1));

        player.getInventory().setArmorContents(data.getArmor());
        player.updateInventory();
    }

}

