package net.omniblock.worlds.handler;

import java.io.File;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.World.Environment;

import net.omniblock.worlds.OmniWorlds;
import net.omniblock.worlds.data.generators.CleanroomChunkGenerator;
import net.omniblock.worlds.utils.FileUtils;

public class WorldResetHandler {
	
	public static final ExecutorService TASKS_PROCESSOR = Executors
            .newFixedThreadPool(10);
	
	public static final File BACKUP_FOLDER = new File(
			OmniWorlds.getInstance().getDataFolder() + File.separator  + "OmniWorlds/backups"
			);
	
	public boolean hasBackup(String name) {
		return new File(BACKUP_FOLDER, name).exists();
	}
	
	public void prepareWorld(World world){
		
		prepareWorld(world.getName());
		return;
		
	}
	
	public void prepareWorld(String world){
		
		File backupFolder = BACKUP_FOLDER;
		
		if(!backupFolder.exists()) {
			backupFolder.mkdirs();
		}
		
		File backupWorldFolder = new File(".", world);
		File backupWorldDestFolder = new File(backupFolder, world);
		
		if(!backupWorldFolder.exists()) {
			throw new IllegalArgumentException("La carpeta del mundo '" + world + "' no fue encontrado en: " + backupWorldFolder.getAbsolutePath());
		}
		
		FileUtils.copyDirectory(backupWorldFolder, backupWorldDestFolder);
		
	}
	
	public void resetWorld(World world){
		
		Iterator<World> worlds = Bukkit.getWorlds().iterator();
		
		String name = world.getName();
		
		boolean loaded = false;
		
		while(worlds.hasNext()){
			
			World cache = worlds.next();
			if(cache.getName() == world.getName()){
				
				loaded = true;
				break;
				
			}
			
		}
		
		if(!loaded) throw new UnsupportedOperationException("No se puede resetear el mundo " + world.getName() + " porque aún no ha sido cargado.");
		
		if(world.getPlayers().size() == 0){
			
			// Hacer el Reseteo
			// del mundo:
			
			Bukkit.unloadWorld(world, false);
			
			File worldFolder = new File(".", name);
			File worldBackupFolder = new File(BACKUP_FOLDER, name);
			
			if(!worldBackupFolder.exists()) throw new UnsupportedOperationException("No se puede restaurar un mundo sin antes haberlo preparado o que almenos exista una copia de el en la carpeta de backups!");
			
			FileUtils.purgeDirectory(worldFolder);
			FileUtils.copyDirectory(worldBackupFolder, worldFolder);
			
			WorldCreator creator = new WorldCreator(name);
			
			creator.environment(Environment.NORMAL);
			creator.generator(new CleanroomChunkGenerator("."));
			
		} else { throw new UnsupportedOperationException("No se puede resetear el mundo " + world.getName() + " porque aún hay jugadores!"); }
		
	}
	
}
