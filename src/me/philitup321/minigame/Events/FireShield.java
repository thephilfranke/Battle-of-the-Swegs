package me.philitup321.minigame.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FireShield implements Listener{
	@EventHandler
	public void onShieldClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
			if(((Player) p).getItemInHand().getType().equals(Material.QUARTZ)) {
				
				((Player) p).getInventory().remove(Material.QUARTZ);
				p.performCommand("shield");
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 700, 480));
			}
		}
	}

}
