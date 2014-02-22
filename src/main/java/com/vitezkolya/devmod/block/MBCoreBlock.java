
package com.vitezkolya.devmod.block;

import java.util.Random;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.vitezkolya.devmod.lib.Reference;
import com.vitezkolya.devmod.tile.MBCore;

public class MBCoreBlock extends BlockBasic implements ITileEntityProvider {
	
	public MBCoreBlock(int id) {
	
		super(id);
		setHardness(0.8F);
		setTickRandomly(true);
	}
	
	@Override
	public int tickRate(World world) {
	
		return 1;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
	
		MBCore core = (MBCore) world.getBlockTileEntity(x, y, z);
		
		if(core != null && !core.isValid()) {
			core.invalidateMultiblock();
		}
		
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random par5Random) {
	
		MBCore tileEntity = (MBCore) world.getBlockTileEntity(x, y, z);
		
		if(tileEntity != null) {
			
			if(Reference.DEBUG_MODE) {
				
				System.out.println("is structure already built?");
			}
			
			// Determine if the Multiblock is currently known to be valid
			if(!tileEntity.isValid()) {
				
				if(Reference.DEBUG_MODE) {
					
					System.out.println("Checking for valid structure");
				}
				
				if(tileEntity.isValidStructure()) {
					
					if(Reference.DEBUG_MODE) {
						
						System.out.println("Building Structure...");
					}
					
					tileEntity.buildStructure();
				}
			}
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int id) {
	
		MBCore tileEntity = (MBCore) world.getBlockTileEntity(x, y, z);
		
		if(tileEntity != null) {
			// Determine if the Multiblock is currently known to be valid
			
			if(Reference.DEBUG_MODE) {
				
				System.out.println("is structure already built?");
			}
			
			if(!tileEntity.isValid()) {
				
				if(Reference.DEBUG_MODE) {
					
					System.out.println("Checking for valid structure");
				}
				
				if(tileEntity.isValidStructure()) {
					
					if(Reference.DEBUG_MODE) {
						
						System.out.println("Building Structure...");
					}
					
					tileEntity.buildStructure();
				}
			}
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
	
		return new MBCore();
	}
}
