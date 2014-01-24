package biomesoplenty.common.core;

import static biomesoplenty.BiomesOPlenty.packetPipeline;

import biomesoplenty.common.network.packet.PacketBiomePosition;

public class BOPPackets
{
    public static void init()
    {
        registerPackets();
    }
    
    private static void registerPackets()
    {
        packetPipeline.registerPacket(PacketBiomePosition.class);
    }
}
