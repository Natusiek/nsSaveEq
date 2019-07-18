package pl.natusiek.mc.helper.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public final class SafeLocations {

    public static final Location SPAWN_LOCATION = new Location(Bukkit.getWorlds().get(0), 0, 80, 0);
    public static final Location SAVING_EQ_LOCATION = new Location(Bukkit.getWorlds().get(0), 100, 150, 100);

    private SafeLocations() {
    }

}
