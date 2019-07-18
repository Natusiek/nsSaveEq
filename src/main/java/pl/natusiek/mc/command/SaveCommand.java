package pl.natusiek.mc.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.natusiek.mc.data.EquipmentDataManager;
import pl.natusiek.mc.data.EquipmentStartSavingEvent;
import pl.natusiek.mc.helper.location.SafeLocations;
import pl.natusiek.mc.listener.SavingEquipment;

import static pl.natusiek.mc.helper.MessageHelper.colored;

public class SaveCommand implements CommandExecutor {

    private final EquipmentDataManager equipmentDataManager;

    public SaveCommand(EquipmentDataManager equipmentDataManager) {
        this.equipmentDataManager = equipmentDataManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        final Player player = (Player) sender;
        if (SavingEquipment.savingEquipment.contains(player.getUniqueId())) {
            player.sendMessage(colored( "&4Zapisujesz juz ekwipunek! =("));
            return false;
        }
        player.sendMessage(colored( "&eUstaw swoj ekwipunek tak jak chcesz i zamknij go, aby zostal zapisany!"));

        Bukkit.getPluginManager().callEvent(new EquipmentStartSavingEvent(player));
        SavingEquipment.savingEquipment.add(player.getUniqueId());
        player.teleport(SafeLocations.SAVING_EQ_LOCATION);
        //player.openInventory(player.getInventory());

        return true;
    }

}
