
package com.vitezkolya.devmod.block;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static Block blockStoneCube;
	public static Block ghostBlock;
	
	public static void init() {
	
		blockStoneCube = new BlockStoneCube(900);
		ghostBlock = new GhostBlock(901);
		
		GameRegistry.registerBlock(blockStoneCube, "block.blockStoneCube");
		GameRegistry.registerBlock(ghostBlock, "block.ghostBlock");
		
	}
	
}
