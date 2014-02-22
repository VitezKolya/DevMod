
package com.vitezkolya.devmod;

import net.minecraft.creativetab.CreativeTabs;

import com.vitezkolya.devmod.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabEx extends CreativeTabs {
	
	public CreativeTabEx(int tablID) {
	
		super(tablID, Reference.MOD_ID);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex() {
	
		return 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
	
		return Reference.MOD_NAME;
	}
}
