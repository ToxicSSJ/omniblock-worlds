package net.omniblock.worlds.handler;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.World.Environment;

import net.omniblock.worlds.data.generators.CleanroomChunkGenerator;

public class WorldGetterHandler {

	public World getWorld(String name){
		
		for(World world : Bukkit.getWorlds()){
			if(world.getName() == name) return world;
		}
		
		WorldCreator creator = new WorldCreator(name);
		
		creator.environment(Environment.NORMAL);
		creator.generator(new CleanroomChunkGenerator("."));
		
		return creator.createWorld();
		
	}
	
}
