package azu1.azu1;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Azu1 extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onPlayerDamaged(EntityDamageEvent e) {
        World world = e.getEntity().getWorld();
        String Sw = getConfig().getString("spawn.world");
        double x = getConfig().getDouble("spawn.x");
        double y = getConfig().getDouble("spawn.y");
        double z = getConfig().getDouble("spawn.z");
        if (e.getEntityType() == EntityType.PLAYER) {
            if (world.getName().equals(Sw)) {
                e.setCancelled(true);

                if (e.getCause().equals(EntityDamageEvent.DamageCause.VOID))
                    e.getEntity().teleport(new Location(world, x, y, z, 0, 0));
        }
        }
    }


    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        World world = block.getWorld();
        String Sw = getConfig().getString("spawn.world");
        if (world.getName().equals(Sw)) {
            e.setCancelled(!player.hasPermission("azu1.permission.Admin"));
        }

    }

    @EventHandler
        public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        World w = Bukkit.getWorld(getConfig().getString("spawn.world"));
        double x = getConfig().getDouble("spawn.x");
        double y = getConfig().getDouble("spawn.y");
        double z = getConfig().getDouble("spawn.z");


        p.teleport(new Location(w, x, y, z, 0, 0));

        p.sendMessage(ChatColor.AQUA + "ようこそ");

        }

}











