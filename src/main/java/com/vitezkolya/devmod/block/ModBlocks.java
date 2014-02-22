
package com.vitezkolya.devmod.block;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static Block blockTestMultiblock;
	public static Block ghostBlock;
	
	public static void init() {
	
		blockTestMultiblock = new BlockTestMultiblock(900);
		ghostBlock = new GhostBlock(901);
		
		GameRegistry.registerBlock(blockTestMultiblock, "tile.blockTestMultiblock");
		GameRegistry.registerBlock(ghostBlock, "tile.ghostBlock");
		
	}
	
}
