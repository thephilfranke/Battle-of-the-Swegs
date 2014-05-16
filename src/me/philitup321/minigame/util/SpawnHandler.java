package me.philitup321.minigame.util;

import me.philitup321.minigame.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SpawnHandler {

	public static void teleportToArena() {
		
		int counter = 0;
		int counter2 = 2;
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			if (Main.TSwag.contains(p.getName())) {
				p.teleport(Main.spawns[counter]);
				counter++;
				 
			} else if (Main.AnTySwag.contains(p.getName())) {
				p.teleport(Main.spawns[counter2]);
				counter2++;
				 
			}
		}
		
		
	}





}
