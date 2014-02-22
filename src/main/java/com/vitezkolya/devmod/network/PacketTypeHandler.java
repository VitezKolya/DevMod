
package com.vitezkolya.devmod.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.vitezkolya.devmod.lib.Reference;
import com.vitezkolya.devmod.network.packet.PacketANT;
import com.vitezkolya.devmod.network.packet.PacketRequestEvent;
import com.vitezkolya.devmod.network.packet.PacketTileUpdate;

public enum PacketTypeHandler {
	
	TILE(PacketTileUpdate.class), REQUEST_EVENT(PacketRequestEvent.class);
	
	private Class<? extends PacketANT> clazz;
	
	PacketTypeHandler(Class<? extends PacketANT> clazz) {
	
		this.clazz = clazz;
	}
	
	public static PacketANT buildPacket(byte[] data) {
	
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int selector = bis.read();
		DataInputStream dis = new DataInputStream(bis);
		
		PacketANT packet = null;
		
		try {
			
			packet = values()[selector].clazz.newInstance();
		} catch(Exception e) {
			
			e.printStackTrace(System.err);
		}
		
		packet.readPopulate(dis);
		
		return packet;
	}
	
	public static PacketANT builPacket(PacketTypeHandler type) {
	
		PacketANT packet = null;
		
		try {
			
			packet = values()[type.ordinal()].clazz.newInstance();
		} catch(Exception e) {
			
			e.printStackTrace(System.err);
		}
		
		return packet;
	}
	
	public static Packet populatePacket(PacketANT packetANT) {
	
		byte[] data = packetANT.populate();
		
		Packet250CustomPayload packet250 = new Packet250CustomPayload();
		packet250.channel = Reference.CHANNEL_NAME;
		packet250.data = data;
		packet250.length = data.length;
		packet250.isChunkDataPacket = packetANT.isChunkDataPacket;
		
		return packet250;
	}
}
