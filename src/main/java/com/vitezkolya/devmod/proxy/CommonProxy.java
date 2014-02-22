
package com.vitezkolya.devmod.proxy;

import com.vitezkolya.devmod.tile.MBGhost;
import com.vitezkolya.devmod.tile.TileStoneCube;

import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy {
	
	public void registerTileEntities() {
	
		GameRegistry.registerTileEntity(TileStoneCube.class, "tile." + "blockStoneCube");
		GameRegistry.registerTileEntity(MBGhost.class, "tile.ghostTile");
	}
}
