package me.philitup321.minigame.Events;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MobEvents implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
    public void onMobSpawn(CreatureSpawnEvent e) {
    	if(!(e.getEntityType() == EntityType.PIG || e.getEntityType() == EntityType.COW || e.getEntityType() == EntityType.CHICKEN || e.getEntityType() == EntityType.SHEEP || e.getEntityType() == EntityType.VILLAGER)){
        if (e.getEntityType() == EntityType.ZOMBIE) {
            Zombie zomb = (Zombie)e.getEntity();
            PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2);
            PotionEffect week = new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 5);
            speed.apply(e.getEntity());
            week.apply(e.getEntity());
            zomb.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
            zomb.setHealth(2);
        } else {
        	e.setCancelled(true);
        }
    }
    }

}
