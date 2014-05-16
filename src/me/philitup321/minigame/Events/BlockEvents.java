package me.philitup321.minigame.Events;

import me.philitup321.minigame.Main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEvents implements Listener{
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
		
		if(Main.spectators.contains(p.getName())) {
        event.setCancelled(true);
        p.sendMessage(Main.prefix + ChatColor.RED + "You cannot edit the terrain as the spectator!!");
    }
    }
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
			Player p = event.getPlayer();
			
			if(Main.spectators.contains(p.getName())) {
            event.setCancelled(true);
            p.sendMessage(Main.prefix + ChatColor.RED + "You cannot edit the terrain as the spectator!!");
        }
	}

}
