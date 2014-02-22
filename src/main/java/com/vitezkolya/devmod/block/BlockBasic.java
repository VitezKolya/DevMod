
package com.vitezkolya.devmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

import com.vitezkolya.devmod.DevMod;
import com.vitezkolya.devmod.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBasic extends Block {
	
	public BlockBasic(int id) {
	
		super(id, Material.rock);
		setCreativeTab(DevMod.creativeTab);
	}
	
	public BlockBasic(int id, Material material) {
	
		super(id, material);
		
	}
	
	@Override
	public String getUnlocalizedName() {
	
		return String.format("block.%s%s", Strings.RESOURCE_PREFIX,
				getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
	
		blockIcon = iconRegister.registerIcon(String.format("%s",
				getUnwrappedUnlocalizedName(getUnlocalizedName())));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
	
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}
