
package com.vitezkolya.devmod.proxy;

import net.minecraftforge.common.ForgeDirection;

public interface IProxy {
	
	void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation,
			byte state, String customName);
	
}
