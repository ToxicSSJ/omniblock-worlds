package net.omniblock.worlds;

import org.bukkit.plugin.java.JavaPlugin;

import net.omniblock.network.handlers.Handlers;

public class OmniWorlds extends JavaPlugin {

	public static OmniWorlds instance;
	
	@Override
	public void onEnable(){
		
		if(instance == null){
			
			instance = this;
			
			Handlers.LOGGER.sendModuleInfo("&7Se ha registrado OmniWorlds v" + this.getDescription().getVersion() + "!");
			
		} else { throw new RuntimeException("No se puede inicializar la instancia 2 o más veces."); }
		
	}
	
	public static OmniWorlds getInstance(){
		return instance;
	}
	
}
