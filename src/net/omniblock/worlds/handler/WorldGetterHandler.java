package net.omniblock.worlds.handler;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.World.Environment;

import net.omniblock.worlds.data.Worlds;
import net.omniblock.worlds.data.generators.CleanroomChunkGenerator;

public class WorldGetterHandler {

	public World getWorld(String name){
		
		System.out.println("GETWORLD -> REQUESTING WORLD " + name);
		
		World w = Bukkit.getWorld(name);
		if(w != null)
			return w;
		
		for(World world : Bukkit.getWorlds()){
			if(world.getName().equalsIgnoreCase(name)) return world;
		}
		
		WorldCreator creator = new WorldCreator(name);
		
		creator.environment(Environment.NORMAL);
		creator.generator(new CleanroomChunkGenerator("."));
		
		return creator.createWorld();
		
	}
	
	public World getWorldSafe(String name){
		
		System.out.println("GETWORLDSAFE -> REQUESTING WORLD " + name);
		
		World w = Bukkit.getWorld(name);
		if(w != null)
			return w;
		
		for(World world : Bukkit.getWorlds()){
			if(world.getName().equalsIgnoreCase(name)) return world;
		}
		
		World world = Worlds.GETTER.getWorld(name);
		
		if(Worlds.RESET.hasBackup(world.getName()))
			Worlds.RESET.resetWorld(world); 
		else
			Worlds.RESET.prepareWorld(world); 
		
		return world;
	}
	
}
