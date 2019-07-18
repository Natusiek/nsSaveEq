package pl.natusiek.mc.data;


import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import pl.natusiek.mc.helper.ItemBuilder;

public class EquipmentData {

    public static final EquipmentData DEFAULT_DATA = new EquipmentData(false);

    public static final ItemStack[] DEFAULT_ARMOR = new ItemStack[] {
            new ItemBuilder(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(),
            new ItemBuilder(Material.IRON_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(),
            new ItemBuilder(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(),
            new ItemBuilder(Material.IRON_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build()
    };
    public static final ItemStack[] DEFAULT_INVENTORY = new ItemStack[] {
            new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.DURABILITY, 2).addEnchantment(
                    Enchantment.DAMAGE_ALL, 3).build(),
            new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1),
            new ItemStack(Material.GOLDEN_APPLE, 8),
            new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.KNOCKBACK, 2).build(),
            new ItemStack(Material.ENDER_PEARL, 1),
            new ItemStack(Material.WATER_BUCKET),
            new ItemStack(Material.COOKED_BEEF, 32),
            new ItemStack(Material.COBBLESTONE, 48),
    };

    public static final ItemStack[] VIP_ARMOR = new ItemStack[] {
            new ItemBuilder(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(),
            new ItemBuilder(Material.IRON_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(),
            new ItemBuilder(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(),
            new ItemBuilder(Material.IRON_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build()
    };
    public static final ItemStack[] VIP_INVENTORY = new ItemStack[] {
            new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.DURABILITY, 2).addEnchantment(
                    Enchantment.DAMAGE_ALL, 4).build(),
            new ItemStack(Material.GOLDEN_APPLE, 2, (short) 1),
            new ItemStack(Material.GOLDEN_APPLE, 112),
            new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.KNOCKBACK, 2).build(),
            new ItemStack(Material.ENDER_PEARL, 2),
            new ItemStack(Material.WATER_BUCKET),
            new ItemStack(Material.COOKED_BEEF, 32),
            new ItemStack(Material.COBBLESTONE, 48),
    };
    private ItemStack[] armor;
    private ItemStack[] inventory;

    public EquipmentData(boolean premium) {
        if (premium) {
            this.armor = VIP_ARMOR;
            this.inventory = VIP_INVENTORY;
            return;
        }
        this.armor = DEFAULT_ARMOR;
        this.inventory = DEFAULT_INVENTORY;
    }

    public EquipmentData(ItemStack[] armor, ItemStack[] inventory) {
        this.armor = armor;
        this.inventory = inventory;
    }

    public ItemStack[] getArmor() {
        return armor;
    }

    public ItemStack[] getInventory() {
        return inventory;
    }

    public void setArmor(ItemStack[] armor) {
        this.armor = armor;
    }

    public void setInventory(ItemStack[] inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EquipmentData data = (EquipmentData) o;
        return Arrays.equals(armor, data.armor) &&
                Arrays.equals(inventory, data.inventory);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(armor);
        result = 31 * result + Arrays.hashCode(inventory);
        return result;
    }

  /*public static boolean hasIllegalArmor(ItemStack[] armor) {
    return Arrays.stream(armor)
        .anyMatch(playerArmor -> playerArmor != null && !Arrays.asList(DEFAULT_ARMOR).contains(playerArmor));
  }

  public static boolean hasIllegalInventory(ItemStack[] inventory) {
    return Arrays.stream(inventory)
        .anyMatch(playerInventory -> playerInventory != null && !Arrays.asList(DEFAULT_INVENTORY).contains(playerInventory));
  }*/
}
