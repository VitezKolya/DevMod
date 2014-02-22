
package com.vitezkolya.devmod.network.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;

import com.vitezkolya.devmod.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

public class PacketANT {
	
	public PacketTypeHandler packetType;
	public boolean isChunkDataPacket;
	
	public PacketANT(PacketTypeHandler packetType, boolean isChunkDataPacket) {
	
		this.packetType = packetType;
		this.isChunkDataPacket = isChunkDataPacket;
	}
	
	public byte[] populate() {
	
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		
		try {
			
			dos.writeByte(packetType.ordinal());
			writeData(dos);
		} catch(IOException e) {
			
			e.printStackTrace(System.err);
		}
		
		return bos.toByteArray();
	}
	
	public void readPopulate(DataInputStream data) {
	
		try {
			
			readData(data);
		} catch(IOException e) {
			
			e.printStackTrace(System.err);
		}
	}
	
	public void readData(DataInputStream data) throws IOException {
	
	}
	
	public void writeData(DataOutputStream data) throws IOException {
	
	}
	
	public void execute(INetworkManager network, Player player) {
	
	}
	
	public void setKey(int key) {
	
	}
}
