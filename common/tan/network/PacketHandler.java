package tan.network;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import tan.network.packet.PacketTAN;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) 
    {
        PacketTAN packetTAN = PacketTypeHandler.buildPacket(packet.data);

        packetTAN.execute(manager, player);
    }
}
