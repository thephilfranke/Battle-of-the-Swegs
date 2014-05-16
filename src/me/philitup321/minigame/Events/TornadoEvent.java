package me.philitup321.minigame.Events;

import me.philitup321.minigame.Main;
import me.philitup321.minigame.util.Tornados;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class TornadoEvent implements Listener{
	
	private Main plugin;
	
	@EventHandler
    public void dragonSpawn(final PlayerMoveEvent event){
		if(Main.gameStarted = true){
        if(!Main.cooldown1.contains(event.getPlayer().getName())){
            Main.cooldown1.add(event.getPlayer().getName());
            Vector vec = new Vector(2, 2, 2);
            int chance = (int)(Math.random() * 100) + 1;
            if(chance == 1){
                int x = event.getPlayer().getLocation().getBlockX();
                int y = event.getPlayer().getLocation().getBlockY();
                int z = event.getPlayer().getLocation().getBlockZ();
                //set the location of the ender dragon relative to the player
                Location loc = event.getPlayer().getWorld().getBlockAt(x + 5, y, z + 2).getLocation();
                Tornados.spawnTornado(plugin, Main.getRandom(Main.torns), Material.WEB, (byte) 0, vec, 0.3D, 100, 100, true, false);
                	Bukkit.getWorld("world").playSound(loc, Sound.FIZZ, 15, 1);
					event.getPlayer().sendMessage(Main.prefix + ChatColor.RED + "A tornado has spawned near you!! Be cautious!!");
                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
                    @Override
                    public void run(){
                        Main.cooldown1.remove(event.getPlayer().getName());
                    }
                }, 20L * 5);
            }
        }
    }
	}

}
