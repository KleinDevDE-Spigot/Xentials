package de.kleindev.xentials.tools;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DevTweaks {
    public static Location stringToLocation(String location){
        String[] loc = location.split("\\|");
        World w = Bukkit.getWorld(loc[0]);
        double x = Double.parseDouble(loc[1]);
        double y = Double.parseDouble(loc[2]);
        double z = Double.parseDouble(loc[3]);
        float pitch = Float.parseFloat(loc[4]);
        float yaw = Float.parseFloat(loc[4]);
        return new Location(w, x, y, z, pitch, yaw);
    }

    public static String locationToString(Location location){
        String w = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float pitch = location.getPitch();
        float yaw = location.getYaw();
        return w + "|" + x + "|" + y + "|" + z + "|" + pitch + "|" + yaw;
    }

    public static void sendActionbar(Player player, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}");
        PacketPlayOutChat packet = new PacketPlayOutChat(icbc);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public static void setInventory(Player player, Inventory inventory){
        player.getInventory().clear();
        player.getInventory().setContents(inventory.getContents());
    }

    public static void saveInventory(Inventory inventory, FileConfiguration file){
        file.set("inventory", inventory.getContents());
    }

    public static Inventory loadInventory(FileConfiguration fileConfiguration){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.PLAYER);
        ArrayList<ItemStack> content = (ArrayList<ItemStack>) fileConfiguration.getList("inventory");
        ItemStack[] items = new ItemStack[content.size()];
        for (int i = 0; i < content.size(); i++) {
            ItemStack item = content.get(i);
            if (item != null) {
                items[i] = item;
            } else {
                items[i] = null;
            }
        }
        inventory.setContents(items);
        return inventory;
    }
}
