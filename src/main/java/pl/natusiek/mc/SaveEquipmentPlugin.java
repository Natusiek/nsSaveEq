package pl.natusiek.mc;

import org.bukkit.plugin.java.JavaPlugin;

import pl.natusiek.mc.command.SaveCommand;
import pl.natusiek.mc.data.EquipmentDataManager;
import pl.natusiek.mc.data.EquipmentDataSaver;
import pl.natusiek.mc.listener.GivingEquipment;
import pl.natusiek.mc.listener.SavingEquipment;

public final class SaveEquipmentPlugin extends JavaPlugin {

    private EquipmentDataSaver equipmentDataSaver;
    private EquipmentDataManager equipmentDataManager;

    @Override
    public void onEnable() {

        this.equipmentDataManager = new EquipmentDataManager();
        this.equipmentDataSaver = new EquipmentDataSaver(this.equipmentDataManager);

        this.equipmentDataSaver.load();

        this.getServer().getPluginManager().registerEvents(new GivingEquipment(this.equipmentDataManager), this);

        final SavingEquipment savingEquipment = new SavingEquipment(this.equipmentDataManager);
        this.getCommand("saveequipment").setExecutor(new SaveCommand(equipmentDataManager));
        this.getServer().getPluginManager().registerEvents(savingEquipment, this);

        this.getServer().getScheduler().runTaskTimerAsynchronously(this,
                () -> this.equipmentDataSaver.save(), 20L * 5, 20L * 60);
    }

    public static SaveEquipmentPlugin getInstance() {
        return getPlugin(SaveEquipmentPlugin.class);
    }
}
