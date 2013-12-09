package tan.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import tan.network.packet.PacketSendStats;
import tan.network.packet.PacketTAN;

public enum PacketTypeHandler
{
    sendStats(PacketSendStats.class);

    private Class<? extends PacketTAN> clazz;

    PacketTypeHandler(Class<? extends PacketTAN> clazz) 
    {
        this.clazz = clazz;
    }

    public static PacketTAN buildPacket(byte[] data) 
    {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        int selector = bis.read();
        DataInputStream dis = new DataInputStream(bis);

        PacketTAN packet = null;

        try 
        {
            packet = values()[selector].clazz.newInstance();
        }
        catch (Exception e) 
        {
            e.printStackTrace(System.err);
        }

        packet.readPopulate(dis);

        return packet;
    }

    public static PacketTAN buildPacket(PacketTypeHandler type) 
    {
        PacketTAN packet = null;

        try 
        {
            packet = values()[type.ordinal()].clazz.newInstance();
        }
        catch (Exception e) 
        {
            e.printStackTrace(System.err);
        }

        return packet;
    }

    public static Packet populatePacket(PacketTAN PacketTAN)
    {
        byte[] data = PacketTAN.populate();

        Packet250CustomPayload packet250 = new Packet250CustomPayload();
        packet250.channel = "ToughAsNails";
        packet250.data = data;
        packet250.length = data.length;

        return packet250;
    }
}
