package me.philitup321.minigame.Events;

import me.philitup321.minigame.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.kitteh.tag.TagAPI;

public class PlayJoinEvent implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		 if(Main.gameStarted = true){
			p.setGameMode(GameMode.ADVENTURE);
			p.teleport(Bukkit.getWorld("world").getSpawnLocation());
			p.setFoodLevel(20);
			p.sendMessage(Main.prefix + ChatColor.WHITE + "Welcome to " + ChatColor.GOLD + "Battle of the Swegs" + ChatColor.WHITE + "!! Please step on the pressure plate that matches your team.");
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Location to = e.getTo();
		Block plateCheck = Bukkit.getWorld(e.getPlayer().getWorld().getName()).getBlockAt(to.getBlockX(),to.getBlockY(),to.getBlockZ());
		 
		if (plateCheck.getType().equals(Material.WOOD_PLATE)) {
		if(Main.gameStarted = true) {
			if(!(Main.TSwag.contains(p.getName()))) {
				Main.TSwag.add(p.getName());
				p.getInventory().clear();
				p.sendMessage(Main.prefix + ChatColor.WHITE + "You joined T-$wag!");
				TagAPI.refreshPlayer(p);
				p.setFoodLevel(20);
			}
			
		} else {
			p.sendMessage(Main.prefix + ChatColor.RED + "You cannot join a team! The game has already started!!");
		}
	} else if (plateCheck.getType().equals(Material.STONE_PLATE)) {
		if(Main.gameStarted = true) {
			if(!(Main.AnTySwag.contains(p.getName()))) {
				Main.AnTySwag.add(p.getName());
				p.getInventory().clear();
				p.sendMessage(Main.prefix + ChatColor.WHITE + "You joined AnTy-Swag!");
				TagAPI.refreshPlayer(p);
				p.setFoodLevel(20);
			}
			
		} else {
			p.sendMessage(Main.prefix + ChatColor.RED + "You cannot join a team! The game has already started!!");
		}
	}
	}
	

}
