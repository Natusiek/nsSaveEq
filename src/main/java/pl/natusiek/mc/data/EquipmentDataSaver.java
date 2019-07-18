package pl.natusiek.mc.data;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import pl.natusiek.mc.SaveEquipmentPlugin;
import pl.natusiek.mc.helper.Serializer;

public class EquipmentDataSaver {

    private final EquipmentDataManager dataManager;

    private final Plugin plugin = SaveEquipmentPlugin.getInstance();
    private final File dir = new File(plugin.getDataFolder(), "/data");

    public EquipmentDataSaver(EquipmentDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void save() {
        //Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () ->
        this.dataManager.getEquipments().forEach((uuid, data) -> {
            //if (data.equals(EquipmentData.DEFAULT_DATA)) { //not storing useless data
            //  return;
            //}
            final File file = new File(this.dir, uuid.toString() + ".yml");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            final FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set("armor", Serializer.serializeInventory(data.getArmor()));
            configuration.set("inventory", Serializer.serializeInventory(data.getInventory()));
            try {
                configuration.save(file);
            }
            catch (IOException ex) {
                throw new RuntimeException("Cannot save player equipment data!", ex);
            }
        });
        //);
    }

    public void load() {
        if (!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdir();
        }
        if (!this.dir.exists()) {
            this.dir.mkdir();
        }
        if (this.dir.listFiles() == null) {
            return;
        }
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            for (File file : Objects.requireNonNull(this.dir.listFiles(), "Files are null")) {
                try {
                    final FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                    //or just use withoutextension method but i dont remember where is it
                    final EquipmentData data = this.dataManager
                            .createEquipmentData(UUID.fromString(StringUtils.replace(file.getName(), ".yml", "")), false);
                    data.setArmor(Serializer.deserializeInventory(configuration.getString("armor")));
                    data.setInventory(Serializer.deserializeInventory(configuration.getString("inventory")));
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
