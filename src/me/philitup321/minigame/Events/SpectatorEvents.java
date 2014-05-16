package me.philitup321.minigame.Events;

import me.philitup321.minigame.Main;

import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class SpectatorEvents implements Listener{
	
	@EventHandler
    public void onSpecDamage(EntityDamageByEntityEvent event) {
		Entity d = event.getDamager();
		
		if(d instanceof Player) {
			if(Main.spectators.contains(((Player) d).getName())) {
				event.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        if (Main.spectators.contains(player.getName())) {
            event.setCancelled(true);
        }
    }
	
	 @EventHandler
	    public void onInventoryClick(InventoryClickEvent event) {
	      HumanEntity player = event.getWhoClicked();
	        if (Main.spectators.contains(player.getName())) {
	                          event.setCancelled(true);
	                          player.closeInventory();
	                             
	            }
	        
	        return;
	    }
	
	

}
