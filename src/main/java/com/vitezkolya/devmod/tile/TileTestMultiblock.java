
package com.vitezkolya.devmod.tile;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.vitezkolya.devmod.block.ModBlocks;

public class TileTestMultiblock extends MBCore {
	
	public TileTestMultiblock() {
	
		blocksOfPattern.put("S", new ItemStack(Block.stone, 1, 0));
		blocksOfPattern.put("B", new ItemStack(Block.brick, 1, 0));
		blocksOfPattern.put("C", new ItemStack(ModBlocks.blockTestMultiblock, 1, 0));
		
		pattern = new String[][][] {
				{
						{
								"B", "S", "B"
						}, {
								"S", "S", "S"
						}, {
								"B", "S", "B"
						}
				}, {
						{
								"B", "S", "B"
						}, {
								"S", "S", "S"
						}, {
								"B", "C", "B"
						}
				}, {
						{
								"B", "S", "B"
						}, {
								"S", "S", "S"
						}, {
								"B", "S", "B"
						}
				}
		};
		
		coreTEID = "C";
		ghostBlock = new ItemStack(ModBlocks.ghostBlock, 1, 0);
		coreBlock = new ItemStack(ModBlocks.blockTestMultiblock, 1, 0);
		
		dropPatternBlocks = false;
		
		setMaxdimensions();
	}
}
