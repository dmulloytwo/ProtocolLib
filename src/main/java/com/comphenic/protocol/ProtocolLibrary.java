package com.comphenic.protocol;

import com.comphenic.protocol.concurrency.AbstractHashMap;
import org.bukkit.plugin.java.JavaPlugin;

public class ProtocolLibrary {

    public static ProtocolManager getProtocolManager(JavaPlugin javaPlugin){
        ProtocolManager protocolManager = new ProtocolManager();
        AbstractHashMap.reloadConfiguration(javaPlugin);
        return protocolManager;
    }

}
