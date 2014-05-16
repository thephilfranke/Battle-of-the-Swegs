package me.philitup321.minigame.Events;

import me.philitup321.minigame.Main;
import me.philitup321.minigame.util.GameManager;
import net.minecraft.server.v1_7_R1.EnumClientCommand;
import net.minecraft.server.v1_7_R1.PacketPlayInClientCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftFirework;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class EntityDeath implements Listener{
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if(e.getEntity() instanceof Player) {
			Player victum = (Player) e.getEntity();
			
			Main.players.remove(victum.getName());
			
			
			if(Main.TSwag.contains(victum.getName())) {
				Main.TSwag.remove(victum.getName());
				launch(victum.getLocation().add(0,1,0),Color.PURPLE);
				if(Main.TSwag.size() <= 0){
					GameManager.endGame();
					Main.gameStarted = false;
					Bukkit.broadcastMessage(Main.prefix + ChatColor.YELLOW + "Team Anty-Swag has won B.O.T.S!!!");
				}
			} else if(Main.AnTySwag.contains(victum.getName())) {
				Main.AnTySwag.remove(victum.getName());
				launch(victum.getLocation().add(0,1,0), Color.GREEN);
				if(Main.AnTySwag.size() <= 0){
					GameManager.endGame();
					Main.gameStarted = false;
					Bukkit.broadcastMessage(Main.prefix + ChatColor.YELLOW + "Team T-$wag has won B.O.T.S!!!");
				}
			}
			
			((CraftPlayer)e.getEntity()).getHandle().playerConnection.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
			Main.spectators.add(victum.getName());
			victum.setGameMode(GameMode.CREATIVE);
			for(Player ps : Bukkit.getOnlinePlayers()) {
				ps.hidePlayer(victum);
			}
			victum.sendMessage(Main.prefix + ChatColor.WHITE + "You are now a spectator. Other players cannot see you, and you cannot interact with the arena. To view a certain player, right click the compass.");
                
			Bukkit.broadcastMessage(ChatColor.RED + Main.prefix + victum.getName() + " has died!!" + ChatColor.WHITE + " There are " + Main.players.size() + " players left.");
			
			
			
			
			
			
			
		}
	}

	
	public static void launch(Location l,Color color){
         Firework fw = l.getWorld().spawn(l,Firework.class);
         FireworkMeta meta = fw.getFireworkMeta();
         meta.addEffect(FireworkEffect.builder().flicker(false).with(Type.BALL).trail(false).withColor(color).build());
         fw.setFireworkMeta(meta);
         ((CraftFirework)fw).getHandle().expectedLifespan = 1;
     
     
}
	
}
