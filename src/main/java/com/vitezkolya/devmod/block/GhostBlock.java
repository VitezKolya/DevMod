
package com.vitezkolya.devmod.block;

import java.util.Random;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.vitezkolya.devmod.tile.MBCore;
import com.vitezkolya.devmod.tile.MBGhost;

public class GhostBlock extends BlockBasic implements ITileEntityProvider {
	
	int formerBlockId = -1;
	
	public GhostBlock(int id) {
	
		super(id);
		this.setHardness(0.8F);
		this.setTickRandomly(true);
	}
	
	@Override
	public int idDropped(int meta, Random par2Random, int par3) {
		
		System.out.println("Former BlockId: " + this.formerBlockId);
		
		if(formerBlockId != -1) {
			return formerBlockId;
		} else {
			return this.blockID;
		}
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int oldID, int oldMeta) {
		
		MBGhost ghost = (MBGhost) world.getBlockTileEntity(x, y, z);
		
		if(!world.isRemote) {
			if(ghost != null && ghost.getCore() != null) {
				
				this.formerBlockId = ghost.getFormerBlock().itemID;
				
				ghost.getCore().invalidateMultiblock();
			}
		}
		
		super.breakBlock(world, x, y, z, oldID, oldMeta);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random par5Random) {
		MBGhost ghost = (MBGhost) world.getBlockTileEntity(x, y, z);
		
		if(ghost != null && ghost.getCore() != null) {
			
			if(formerBlockId == -1) {
				ItemStack tile = ghost.getFormerBlock();
				
				formerBlockId = tile.itemID;
				//this.setTickRandomly(false);
			}
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
	
		return new MBGhost();
	}
}
