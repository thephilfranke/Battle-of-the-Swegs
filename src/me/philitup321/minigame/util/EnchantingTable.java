package me.philitup321.minigame.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class EnchantingTable {
	
	 public static boolean isNearBlock(Player p, int range, Material mat) {
	        Location loc = p.getLocation();
	        World world = p.getWorld();
	        double cX = loc.getX();
	        double cY = loc.getY();
	        double cZ = loc.getZ();
	        double bPow = Math.pow(range + 0.5D, 2);
	        double xPow;
	        double zPow;
	        for (int z = -range; z <= range; z++) {
	            zPow = Math.pow(z, 2);
	            for (int x = -range; x <= range; x++) {
	                xPow = Math.pow(x, 2);
	                for (int y = -range; y <= range; y++)
	                    if ((xPow + Math.pow(y, 2) + zPow) <= bPow)
	                        if (world.getBlockAt((int) (cX + x), (int) (cY + y), (int) (cZ + z)).getType() == mat)
	                            return true;
	            }
	        }
	        return false;
	    }

}
