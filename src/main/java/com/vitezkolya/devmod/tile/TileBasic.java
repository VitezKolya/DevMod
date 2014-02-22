
package com.vitezkolya.devmod.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import com.vitezkolya.devmod.lib.Strings;
import com.vitezkolya.devmod.network.PacketTypeHandler;
import com.vitezkolya.devmod.network.packet.PacketTileUpdate;

public class TileBasic extends TileEntity {
	
	protected ForgeDirection orientation;
	protected byte state;
	protected String tileName;
	
	public TileBasic() {
	
		orientation = ForgeDirection.SOUTH;
		state = (byte) 0;
		tileName = "";
	}
	
	public ForgeDirection getOrientation() {
	
		return orientation;
	}
	
	public void setOrientation(ForgeDirection orientation) {
	
		this.orientation = orientation;
	}
	
	public void setOrientation(int orientation) {
	
		this.orientation = ForgeDirection.getOrientation(orientation);
	}
	
	public short getState() {
	
		return state;
	}
	
	public void setState(byte state) {
	
		this.state = state;
	}
	
	public boolean hasTileName() {
	
		return tileName != null && tileName.length() > 0;
	}
	
	public String getTileName() {
	
		return tileName;
	}
	
	public void setTileName(String tileName) {
	
		this.tileName = tileName;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
	
		super.readFromNBT(nbtTagCompound);
		
		if(nbtTagCompound.hasKey(Strings.NBT_TE_DIRECTION_KEY)) {
			
			orientation = ForgeDirection.getOrientation(nbtTagCompound
					.getByte(Strings.NBT_TE_DIRECTION_KEY));
		}
		
		if(nbtTagCompound.hasKey(Strings.NBT_TE_STATE_KEY)) {
			
			state = nbtTagCompound.getByte(Strings.NBT_TE_STATE_KEY);
		}
		
		if(nbtTagCompound.hasKey(Strings.NBT_TE_TILE_NAME)) {
			
			tileName = nbtTagCompound.getString(Strings.NBT_TE_TILE_NAME);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
	
		super.writeToNBT(nbtTagCompound);
		
		nbtTagCompound
				.setByte(Strings.NBT_TE_DIRECTION_KEY, (byte) orientation.ordinal());
		nbtTagCompound.setByte(Strings.NBT_TE_STATE_KEY, state);
		
		if(hasTileName()) {
			nbtTagCompound.setString(Strings.NBT_TE_TILE_NAME, tileName);
		}
		
	}
	
	@Override
	public Packet getDescriptionPacket() {
	
		return PacketTypeHandler.populatePacket(new PacketTileUpdate(xCoord, yCoord,
				zCoord, orientation, state, tileName));
	}
	
	@Override
	public String toString() {
	
		return String
				.format("TileBasic Data - Class: %s, xCoord: %d, yCoord: %d, zCoord %d, tileName: '%s', orientation: %s, state: %d\n",
						this.getClass().getSimpleName(), xCoord, yCoord, zCoord,
						tileName, orientation, state);
	}
}
