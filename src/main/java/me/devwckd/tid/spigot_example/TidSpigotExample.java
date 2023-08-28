package me.devwckd.tid.spigot_example;

import me.devwckd.tid.spigot_example.runnable.SimpleHeadAnimationRunnable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TidSpigotExample extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        if (event.isSneaking()) {
            final Player player = event.getPlayer();
            new SimpleHeadAnimationRunnable(player.getLocation(), player).runTaskTimer(this, 0, 1);
        }
    }

}
