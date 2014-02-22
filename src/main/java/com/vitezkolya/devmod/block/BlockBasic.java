
package com.vitezkolya.devmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.vitezkolya.devmod.DevMod;
import com.vitezkolya.devmod.lib.Strings;
import com.vitezkolya.devmod.tile.TileBasic;

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
	
		return String.format("tile.%s%s", Strings.RESOURCE_PREFIX,
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
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase entityLiving, ItemStack itemStack) {
	
		if(world.getBlockTileEntity(x, y, z) instanceof TileBasic) {
			int direction = 0;
			int facing = MathHelper
					.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
			
			if(facing == 0) {
				
				direction = ForgeDirection.NORTH.ordinal();
			} else if(facing == 1) {
				
				direction = ForgeDirection.EAST.ordinal();
			} else if(facing == 2) {
				
				direction = ForgeDirection.SOUTH.ordinal();
			} else if(facing == 3) {
				
				direction = ForgeDirection.WEST.ordinal();
			}
			
			if(itemStack.hasDisplayName()) {
				
				((TileBasic) world.getBlockTileEntity(x, y, z)).setTileName(itemStack
						.getDisplayName());
			}
			
			((TileBasic) world.getBlockTileEntity(x, y, z)).setOrientation(direction);
		}
	}
}
