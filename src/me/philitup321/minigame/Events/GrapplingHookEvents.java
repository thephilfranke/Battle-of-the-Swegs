package me.philitup321.minigame.Events;

import me.philitup321.minigame.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class GrapplingHookEvents implements Listener{
	@SuppressWarnings("deprecation")
    @EventHandler
    public void onGrappleThrow(ProjectileLaunchEvent e) {
            if (!e.getEntityType().equals(EntityType.FISHING_HOOK)) return;
            if (!(e.getEntity().getShooter() instanceof Player)) return;
           
            final Player p = (Player) e.getEntity().getShooter();
           
            if (Main.cooldown.contains(p)) {
                    e.setCancelled(true);
                    return;
            }
           
            Location target = null;
           
            for (Block block : p.getLineOfSight(null, 25)) {
                    if (!block.getType().equals(Material.AIR)) {
                            target = block.getLocation();
                            break;
                    }
            }
           
            if (target == null) {
                    e.setCancelled(true);
                    return;
            }
           
            p.teleport(p.getLocation().add(0, 0.5, 0));
           
            final Vector v = getVectorForPoints(p.getLocation(), target);
           
            e.getEntity().setVelocity(v);
           
            if (!Main.nofall.contains(p)) Main.nofall.add(p);
           
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this, new Runnable() {
                    public void run() {
                            p.setVelocity(v);
                    }
            }, 5);
           
            Main.cooldown.add(p);
           
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this, new Runnable() {
                    public void run() {
                            Main.cooldown.remove(p);
                    }
            }, 60);
    }
   
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
            if (!(e.getEntity() instanceof Player)) return;
            if (!e.getCause().equals(DamageCause.FALL)) return;
           
            Player p = (Player) e.getEntity();
           
            if (Main.nofall.contains(p)) {
                    e.setCancelled(true);
                    Main.nofall.remove(p);
            }
    }
   
    private Vector getVectorForPoints(Location l1, Location l2) {
            double g = -0.08;
            double d = l2.distance(l1);
            double t = d;
            double vX = (1.0+0.07*t) * (l2.getX() - l1.getX())/t;
            double vY = (1.0+0.03*t) * (l2.getY() - l1.getY())/t - 0.5*g*t;
            double vZ = (1.0+0.07*t) * (l2.getZ() - l1.getZ())/t;
            return new Vector(vX, vY, vZ);
    }
}