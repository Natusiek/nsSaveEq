package pl.natusiek.mc.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EquipmentDataManager {

    private final Map<UUID, EquipmentData> equipments = new HashMap<>();

    public Map<UUID, EquipmentData> getEquipments() {
        return new HashMap<>(equipments);
    }

    public EquipmentData createEquipmentData(UUID playerUUID, boolean premium) {
        EquipmentData data = this.equipments.get(playerUUID);
        if (data == null) {
            this.equipments.put(playerUUID, data = new EquipmentData(premium));
        }
        return data;
    }

}