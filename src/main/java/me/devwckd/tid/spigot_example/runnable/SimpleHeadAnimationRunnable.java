package me.devwckd.tid.spigot_example.runnable;

import me.devwckd.tid.spigot_example.Timelines;
import me.devwckd.tid.property.Properties;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class SimpleHeadAnimationRunnable extends BukkitRunnable {

    private final Location initLocation;
    private final OfflinePlayer headOwningPlayer;

    private ArmorStand armorStand = null;
    private int tick = 0;

    public SimpleHeadAnimationRunnable(Location initLocation, OfflinePlayer headOwningPlayer) {
        this.initLocation = initLocation;
        this.headOwningPlayer = headOwningPlayer;
    }

    @Override
    public void run() {
        if(tick >= Timelines.SIMPLE_HEAD_ANIM_TIMELINE.getDuration()) {
            armorStand.remove();
            cancel();
            return;
        }

        final Properties properties = Timelines.SIMPLE_HEAD_ANIM_TIMELINE.at(tick);

        final Vector headPos = properties.get("headPos");
        final Double headRotation = properties.get("headRotation");
        final Vector leftParticle = properties.get("leftParticle");
        final Vector rightParticle = properties.get("rightParticle");

        final Location armorStandLocation = initLocation.clone().subtract(0.0, 2.0, 0.0).add(headPos);
        if (armorStand == null) {
            armorStand = createArmorStand(armorStandLocation, headOwningPlayer);
        } else {
            armorStand.setHeadPose(new EulerAngle(0.0, headRotation, 0.0));
            armorStand.teleport(armorStandLocation);
        }

        initLocation.getWorld().spawnParticle(Particle.REDSTONE, initLocation.clone().add(leftParticle), 1, new Particle.DustOptions(Color.WHITE, 1));
        initLocation.getWorld().spawnParticle(Particle.REDSTONE, initLocation.clone().add(rightParticle), 1, new Particle.DustOptions(Color.WHITE, 1));

        tick++;
    }

    private ArmorStand createArmorStand(Location initLocation, OfflinePlayer owningPlayer) {
        final ArmorStand armorStand = (ArmorStand) initLocation.getWorld().spawnEntity(initLocation, EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setGravity(false);

        final ItemStack helmet = new ItemStack(Material.PLAYER_HEAD);
        final SkullMeta skullMeta = (SkullMeta) helmet.getItemMeta();
        skullMeta.setOwningPlayer(owningPlayer);
        helmet.setItemMeta(skullMeta);

        armorStand.getEquipment().setHelmet(helmet);
        return armorStand;
    }
}
