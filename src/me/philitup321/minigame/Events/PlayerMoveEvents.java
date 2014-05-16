package me.philitup321.minigame.Events;

import me.philitup321.minigame.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveEvents implements Listener{
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		Location loc = new Location(Bukkit.getWorld("world"), 283.5, 109, -215.5);
		
		for (Player p2: loc.getWorld().getPlayers()) {
		    if (loc.distance(p2.getLocation()) < 25) { 
		    	if(Main.gameStarted = true){
					if(Main.EnchantingTable = false) {
						if(Main.AnTySwag.contains(p2.getName())) {
							Bukkit.broadcastMessage(Main.prefix + ChatColor.GREEN + "AnTy-Swag " + ChatColor.WHITE + "has found the Enchanting Table!! Look out T-$wag!");
						} else if(Main.TSwag.contains(p2.getName())) {
							Bukkit.broadcastMessage(Main.prefix + ChatColor.DARK_PURPLE + "T-$wag " + ChatColor.WHITE + "has found the Enchanting Table!! SUCK IT ANTY-SWAG!");
							}
							Main.EnchantingTable = true;
							for(Player s : Bukkit.getOnlinePlayers()) {
								s.playSound(s.getLocation(), Sound.ENDERDRAGON_GROWL, 5, 2);
							}
						}
					}
		    }
		}
			
		}
		}
	


