
package com.vitezkolya.devmod.network;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.vitezkolya.devmod.network.packet.PacketANT;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {
	
	/**
	 * Handles Packet250CustomPayload packets that are registered to an Advanced Network
	 * Tech network channel
	 * 
	 * @param manager
	 *            The NetworkManager associated with the current platform(client/server)
	 * @param packet
	 *            The Packet250CustomPayload that was received
	 * @param player
	 *            The Player associated with the packet
	 */
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet,
			Player player) {
	
		// Build a PacketANT object from the data contained within the
		// Packet250CustomPayload packet
		PacketANT packetANT = PacketTypeHandler.buildPacket(packet.data);
		
		// Execute the appropriate actions based on the PacketANT type
		packetANT.execute(manager, player);
		
	}
	
}
