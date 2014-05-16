package me.philitup321.minigame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.philitup321.minigame.Events.BlockEvents;
import me.philitup321.minigame.Events.EntityDeath;
import me.philitup321.minigame.Events.FireShield;
import me.philitup321.minigame.Events.FireTracer;
import me.philitup321.minigame.Events.Freeze;
import me.philitup321.minigame.Events.GrapplingHookEvents;
import me.philitup321.minigame.Events.MobEvents;
import me.philitup321.minigame.Events.PlayJoinEvent;
import me.philitup321.minigame.Events.PlayerMoveEvents;
import me.philitup321.minigame.Events.RandomTeleport;
import me.philitup321.minigame.Events.SpectatorEvents;
import net.minecraft.server.v1_7_R1.EnumClientCommand;
import net.minecraft.server.v1_7_R1.PacketPlayInClientCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.kitteh.tag.AsyncPlayerReceiveNameTagEvent;

import de.slikey.effectlib.EffectManager;

public class Main extends JavaPlugin implements Listener{
	
	public static Main plugin;
	public static ArrayList<String> TSwag = new ArrayList<String>();
	public static ArrayList<String> AnTySwag = new ArrayList<String>();
	
	public static ArrayList<String> cooldown1; 
	
	public static ArrayList<String> players = new ArrayList<String>();
	public static ArrayList<String> spectators = new ArrayList<String>();
	public static ArrayList<String> frozen = new ArrayList<String>();
	
	public static List<Location> torns = new ArrayList<Location>();
	
	public static ArrayList<Player> cooldown = new ArrayList<Player>();
	public static ArrayList<Player> nofall = new ArrayList<Player>();
		
	public static EffectManager effectManager;
	
	public static Boolean gameStarted;
	public static Boolean gameEnded;
	public static Boolean EnchantingTable;
	
	
	public static String prefix;
	
	World ArenaWorld, LobbyWorld;
	
	public static Location spawns[] = new Location[5];
	
	public void onEnable() {
		plugin = this;
		ArenaWorld = Bukkit.getWorld("Arena");
		
		spawns[0] = new Location(ArenaWorld, 743.5, 65, 138.5); //T-Swag spawn #1
		spawns[1] = new Location(ArenaWorld, 745.5, 65, 136.5); //T-Swag spawn #2
		
		spawns[2] = new Location(ArenaWorld, -253.5, 65, -477.5); //AnTy-Swag spawn #1
		spawns[3] = new Location(ArenaWorld, -255.5, 65, -475.5); //AnTy-Swag spawn #1

		spawns[4] = new Location(ArenaWorld, -255.5, 111, -223.5); //Spectator spawn
		
		torns.add(new Location(ArenaWorld, -306, 66, -361));
		torns.add(new Location(ArenaWorld, 515, 81, -172));
		torns.add(new Location(ArenaWorld, 743, 66, 49));
		torns.add(new Location(ArenaWorld, 213, 104, 19));
		torns.add(new Location(ArenaWorld, 34, 85, 237));
		torns.add(new Location(ArenaWorld, -306, 66, -361));
		torns.add(new Location(ArenaWorld, 42, 148, -122));
		torns.add(new Location(ArenaWorld, 308, 102, -226));
		
		prefix = ChatColor.GRAY + "[" + ChatColor.GOLD + "B.O.T.S." + ChatColor.GRAY + "]: ";
		
		gameStarted = false;
		gameEnded = false;
		EnchantingTable = false;
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new BlockEvents(), this);
		pm.registerEvents(new EntityDeath(), this);
		pm.registerEvents(new FireShield(), this);
		pm.registerEvents(new FireTracer(), this);
		pm.registerEvents(new Freeze(), this);
		pm.registerEvents(new GrapplingHookEvents(), this);
		pm.registerEvents(new PlayerMoveEvents(), this);
		pm.registerEvents(new RandomTeleport(), this);
		pm.registerEvents(new SpectatorEvents(), this);
		pm.registerEvents(new MobEvents(), this);
		pm.registerEvents(new PlayJoinEvent(), this);

		ItemStack iron_chest = new ItemStack(Material.QUARTZ);
		ItemMeta meta = iron_chest.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Fire Shield");
		iron_chest.setItemMeta(meta);
		
		ItemStack grappling = new ItemStack(Material.FISHING_ROD);
		ItemMeta meta1 = grappling.getItemMeta();
		meta1.setDisplayName(ChatColor.GRAY + "Grappling Hook");
		grappling.setItemMeta(meta1);
		
		ItemStack tracker = new ItemStack(Material.BLAZE_ROD);
		ItemMeta meta2 = tracker.getItemMeta();
		meta2.setDisplayName(ChatColor.RED + "Fire Tracer");
		tracker.setItemMeta(meta2);
		
		ShapedRecipe recipe = new ShapedRecipe(new ItemStack(iron_chest)).shape("#%#", "%#%", "#%#").setIngredient('%', Material.IRON_INGOT).setIngredient('#', Material.WOOD);
		ShapedRecipe recipe2 = new ShapedRecipe(new ItemStack(grappling)).shape("  %", " %#", "% i").setIngredient('%', Material.STICK).setIngredient('#', Material.STRING).setIngredient('i', Material.IRON_INGOT);
		ShapedRecipe recipe3 = new ShapedRecipe(new ItemStack(tracker)).shape(" # ", " % ", " % ").setIngredient('%', Material.STICK).setIngredient('#', Material.TORCH);
		getServer().addRecipe(recipe);
		getServer().addRecipe(recipe2);
		getServer().addRecipe(recipe3);
		
		ShapelessRecipe wool = new ShapelessRecipe(new ItemStack(
				Material.STRING)).addIngredient(Material.WOOL);
				 
				getServer().addRecipe(wool);

		
		
	}
	
	@Override
	public void onDisable(){
	getServer().clearRecipes();
	}
	
	
	
	public static final Location getRandom(List<Location> locs) {
		return locs.get(new Random().nextInt(locs.size()));
		}
	
	@EventHandler
	public void onNameTag(AsyncPlayerReceiveNameTagEvent event) {
	if (TSwag.contains(event.getNamedPlayer().getName())) {
	event.setTag(ChatColor.DARK_PURPLE + event.getNamedPlayer().getName());
	}
	}
	
	@EventHandler
	public void onNameTag2(AsyncPlayerReceiveNameTagEvent event) {
	if (AnTySwag.contains(event.getNamedPlayer().getName())) {
	event.setTag(ChatColor.GREEN + event.getNamedPlayer().getName());
	}
	}
	
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		 Player player = (Player) sender; 
         if(cmd.getName().equalsIgnoreCase("startgame")){
        	
        	 if(gameStarted = true){
        	 
     		gameStarted = false;
     		
     		for(Player p : Bukkit.getOnlinePlayers()) {
     			players.add(p.getName());
     			frozen.add(p.getName());
     			p.setGameMode(GameMode.SURVIVAL);
     		}
     		Main.startCountdown();
     		Bukkit.getWorld("world").setTime(1000);
    		Bukkit.getPlayer("philitup321").teleport(new Location(Bukkit.getWorld("World"), 743.5, 65, 138.5));
    		Bukkit.getPlayer("_Tay_Dizzle_").teleport(new Location(Bukkit.getWorld("World"), 745.5, 65, 136.5));
    		
    		Bukkit.getPlayer("Tyitup").teleport(new Location(Bukkit.getWorld("World"), -253.5, 65, -477.5));
    		Bukkit.getPlayer("thatforeignkid").teleport(new Location(Bukkit.getWorld("World"), -255.5, 65, -475.5));
        	 }
         }
         
         if(cmd.getName().equalsIgnoreCase("spectate")){
        	Main.TSwag.remove(player.getName());
        	Main.AnTySwag.remove(player.getName());
        	Main.players.remove(player.getName());
        	player.getInventory().clear();
 			Main.spectators.add(player.getName());
 			player.setGameMode(GameMode.CREATIVE);
 			for(Player ps : Bukkit.getOnlinePlayers()) {
 				ps.hidePlayer(player);
 			}
 			player.sendMessage(Main.prefix + ChatColor.WHITE + "You are now a spectator. Other players cannot see you, and you cannot interact with the arena.");
                 
        	 }
         
		return true;
       
                	
                }
	
	
	 public static void startCountdown()
	    {
	        countdown(11, 0);
	    }
	
	static int time; 
    static int taskid; 
 
    public static void countdown(int num1, final int num2) { 
    	if(num1 < num2) return; 
    	plugin.time = num1;
    	Bukkit.broadcastMessage(prefix + ChatColor.WHITE + "The game is starting!!"); // broadcast the starting time
    	for(Player p : Bukkit.getOnlinePlayers()) {
    		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
    		p.setHealth(20d);
    		p.setFoodLevel(20);
    	}
    	taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() { // Bukkit runnable
    		public void run() { // running
    			time--; // subtract time by 1
    			Bukkit.broadcastMessage(prefix + ChatColor.WHITE + "Game starting in " + ChatColor.RED + time); // broadcast the starting time
    	    	for(Player p : Bukkit.getOnlinePlayers()) {
    	    		p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
    	    	}
    			if(time == num2) { // if time == the ending time
    				Bukkit.getScheduler().cancelTask(taskid); // cancel the countdown
    				Bukkit.broadcastMessage(prefix + ChatColor.WHITE + "The Game has started!!! May the best Sweg win!!! "); // broadcast saying the countdown is over
    		    	for(Player p : Bukkit.getOnlinePlayers()) {
    		    		p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 2);
    		    		frozen.clear();
    		    	} 
    			}
    		}
    	}, 0, 20); // happening ever 20 ticks (1 second)
    }
	

}
