
package com.vitezkolya.devmod.block;

import java.util.HashMap;

import com.vitezkolya.devmod.tile.TileStoneCube;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockStoneCube extends MBCoreBlock {
	
	HashMap<String, ItemStack> map;
	
	public BlockStoneCube(int id) {
	
		super(id);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
	
		return new TileStoneCube();
	}
	
}
