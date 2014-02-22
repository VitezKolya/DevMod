
package com.vitezkolya.devmod.block;

import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.vitezkolya.devmod.tile.TileTestMultiblock;

public class BlockTestMultiblock extends MBCoreBlock {
	
	HashMap<String, ItemStack> map;
	
	public BlockTestMultiblock(int id) {
	
		super(id);
		setUnlocalizedName("blockTestMultiblock");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
	
		return new TileTestMultiblock();
	}
	
}
