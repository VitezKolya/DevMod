
package com.vitezkolya.devmod.tile;

import net.minecraft.item.ItemStack;

public class MBGhost extends TileBasic {
	
	protected MBCore core;
	
	public void setCore(MBCore core) {
	
		this.core = core;
		
	}
	
	public MBCore getCore() {
	
		return core;
	}
	
	public ItemStack getFormerBlock() {
	
		int[] localCoord = core.getCoreTELocalCoords();
		int[] worldCoord = core.getCoreTEWorldCoords();
		localCoord[0] = xCoord - (worldCoord[0] - localCoord[0]);
		localCoord[1] = yCoord - (worldCoord[1] - localCoord[1]);
		localCoord[2] = zCoord - (worldCoord[2] - localCoord[2]);
		// System.out.println("Local Coords - X: " + localCoord[0] + " Y: " +
		// localCoord[1] + " Z: " + localCoord[2]);
		
		return core.getPatternItem(localCoord[0], localCoord[1], localCoord[2]);
	}
}
