
package com.vitezkolya.devmod.proxy;

import com.vitezkolya.devmod.tile.MBGhost;
import com.vitezkolya.devmod.tile.TileTestMultiblock;

import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy {
	
	public void registerTileEntities() {
	
		GameRegistry.registerTileEntity(TileTestMultiblock.class, "tile."
				+ "blockStoneCube");
		GameRegistry.registerTileEntity(MBGhost.class, "tile.ghostTile");
	}
}
