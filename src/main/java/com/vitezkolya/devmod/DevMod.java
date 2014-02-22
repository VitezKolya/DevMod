
package com.vitezkolya.devmod;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;

import com.vitezkolya.devmod.block.ModBlocks;
import com.vitezkolya.devmod.configuration.ConfigurationHandler;
import com.vitezkolya.devmod.lib.Reference;
import com.vitezkolya.devmod.proxy.IProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class DevMod {
	
	@Instance(Reference.MOD_ID)
	public static DevMod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	public static CreativeTabEx creativeTab = new CreativeTabEx(CreativeTabs.getNextID());
	
	/***
	 * This is code that is executed prior to your mod being initialized into of
	 * Minecraft
	 * Examples of code that could be run here;
	 * 
	 * Initializing your items/blocks (you must do this here)
	 * Setting up your own custom logger for writing log data to
	 * Loading your language translations for your mod (if your mod has
	 * translations for other languages)
	 * Registering your mod's key bindings and sounds
	 * 
	 * @param event
	 *            The Forge ModLoader pre-initialization event
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
	
		ConfigurationHandler.init(event.getModConfigurationDirectory().getAbsolutePath()
				+ File.separator + Reference.CHANNEL_NAME.toLowerCase() + File.separator);
		
		ModBlocks.init();
		// ModItems.init();
	}
	
	/***
	 * This is code that is executed when your mod is being initialized in
	 * Minecraft
	 * Examples of code that could be run here;
	 * 
	 * Registering your GUI Handler
	 * Registering your different event listeners
	 * Registering your different tile entities
	 * Adding in any recipes you have
	 * 
	 * @param event
	 *            The Forge ModLoader initialization event
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
	
	}
	
	/***
	 * This is code that is executed after all mods are initialized in Minecraft
	 * This is a good place to execute code that interacts with other mods (ie,
	 * loads an addon module
	 * of your mod if you find a particular mod).
	 * 
	 * @param event
	 *            The Forge ModLoader post-initialization event
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	
	}
}