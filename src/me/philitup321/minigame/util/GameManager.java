package me.philitup321.minigame.util;

import me.philitup321.minigame.Main;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameManager {
	
	public static void startGame() {
		SpawnHandler.teleportToArena();
		Main.gameStarted = true;
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			Main.players.add(p.getName());
			Main.frozen.add(p.getName());
			p.setGameMode(GameMode.SURVIVAL);
		}
		Main.startCountdown();
	}
	
	public static void endGame() {
		Main.players.clear();
		Main.TSwag.clear();
		Main.AnTySwag.clear();
		Main.players.clear();
		Main.frozen.clear();
		
	}

}
