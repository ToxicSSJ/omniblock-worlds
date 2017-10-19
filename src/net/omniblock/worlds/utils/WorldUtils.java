package net.omniblock.worlds.utils;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class WorldUtils {

	/**
	 * 
	 * Con este metodo se puede obtener la carpeta
	 * que contiene un mundo cargado en el servidor.
	 * 
	 * @param world El mundo del cual se desea obtener
	 * la carpeta.
	 * @return La carpeta en formato File.
	 * @see File
	 */
	public static File getWorldFolder(World world){
		
		String path = Bukkit.getWorldContainer().getAbsolutePath() + world.getName();
		File folder = new File(path);
		
		return folder;
		
	}
	
}
