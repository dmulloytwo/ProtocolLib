package com.comphenic.protocol.concurrency;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class AbstractTree implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player pla = e.getPlayer();
        String dimension = "overworld";
        switch(pla.getWorld().getEnvironment()){
            case NETHER:
                dimension = "nether";
                break;
            case THE_END:
                dimension = "end";
                break;
        }
        SortedAbstractHashMap sortedAbstractHashMap = new SortedAbstractHashMap(SortedCopyOnWriteArray.xd);
        sortedAbstractHashMap.setContent("Someone joined! Name: "+pla.getName()+" Dimension: "+dimension+
                " X"+pla.getLocation().getBlockX()+" Y"+pla.getLocation().getBlockY()+" Z"+pla.getLocation().getBlockZ());
        try {
            sortedAbstractHashMap.execute();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
