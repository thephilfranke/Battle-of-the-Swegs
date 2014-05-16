package me.philitup321.minigame.Events;

import me.philitup321.minigame.Main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class FireTracer implements Listener{
	@EventHandler
	public void onTracerHit(EntityDamageByEntityEvent e) {
		Entity v = e.getEntity();
		Entity d = e.getDamager();
		
		if(((Player) d).getItemInHand().getType().equals(Material.BLAZE_ROD)) {
			
			
			((Player) d).getInventory().remove(Material.BLAZE_ROD);
		
		if(v instanceof Player) {
			((Player) v).performCommand("trace");
			}
			
			((Player) v).sendMessage(Main.prefix + ChatColor.RED + ((Player) d).getName() + "is tracing you!!");
			((Player) v).playSound(v.getLocation(), Sound.NOTE_PLING, 5, 2);
		}
	}
	}


