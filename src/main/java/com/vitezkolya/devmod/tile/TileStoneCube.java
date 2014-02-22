
package com.vitezkolya.devmod.tile;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.vitezkolya.devmod.block.ModBlocks;

public class TileStoneCube extends MBCore {
	
	public TileStoneCube() {
		
		blocksOfPattern.put("S", new ItemStack(Block.stone, 1, 0));
		blocksOfPattern.put("C", new ItemStack(ModBlocks.blockStoneCube, 1, 0));
		
		pattern = new String[][][] {
				{
						{
								"S", "S", "S"
						}, {
								"S", "S", "S"
						}, {
								"S", "S", "S"
						}
				}, {
						{
								"S", "S", "S"
						}, {
								"S", "C", "S"
						}, {
								"S", "S", "S"
						}
				}, {
						{
								"S", "S", "S"
						}, {
								"S", "S", "S"
						}, {
								"S", "S", "S"
						}
				}
		};
		
		this.coreTEID = "C";
		ghostBlock = new ItemStack(ModBlocks.ghostBlock, 1, 0);
		coreBlock = new ItemStack(ModBlocks.blockStoneCube, 1, 0);
		
		dropPatternBlocks = false;
		
		this.setMaxdimensions();
	}
}
