package me.philitup321.minigame.Events;

import me.philitup321.minigame.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RandomTeleport implements Listener{
	@EventHandler
	public void onShieldClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
			if(p.getItemInHand().getType().equals(Material.EYE_OF_ENDER)) {
				p.getInventory().remove(Material.EYE_OF_ENDER);
				
				if(Main.AnTySwag.contains(p.getName())) {
					Bukkit.getPlayer("Tyitup").teleport(Main.getRandom(Main.torns));
					Bukkit.getPlayer("thatforeignkid").teleport(Bukkit.getPlayer("Tyitup").getLocation());
				} else if (Main.TSwag.contains(p.getName())) {
					Bukkit.getPlayer("philitup321").teleport(Main.getRandom(Main.torns));
					Bukkit.getPlayer("_Tay_Dizzle_").teleport(Bukkit.getPlayer("philitup321"));
				}
				
		}
				
			}
	}
	
}
