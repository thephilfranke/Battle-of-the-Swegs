package me.philitup321.minigame.Events;

import me.philitup321.minigame.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Freeze implements Listener{
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
	Player p = e.getPlayer();
	if(Main.frozen.contains(p.getName())) {
		e.setTo(e.getFrom());
	}
	}

}
