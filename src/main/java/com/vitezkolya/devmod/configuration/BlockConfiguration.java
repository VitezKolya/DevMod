
package com.vitezkolya.devmod.configuration;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;

import com.vitezkolya.devmod.lib.Reference;

import cpw.mods.fml.common.FMLLog;

public class BlockConfiguration {
	
	public static void init(File configFile) {
	
		Configuration blockConfiguration = new Configuration(configFile);
		
		try {
			
			blockConfiguration.load();
			
		} catch(Exception e) {
			
			FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME
					+ " has had a problem loading its block configuration");
		} finally {
			
			blockConfiguration.save();
		}
		
	}
	
}
