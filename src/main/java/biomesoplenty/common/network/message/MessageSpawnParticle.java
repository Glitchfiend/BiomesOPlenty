package biomesoplenty.common.network.message;

import io.netty.buffer.ByteBuf;

import java.util.Random;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageSpawnParticle implements IMessage, IMessageHandler<MessageSpawnParticle, IMessage>
{
	private Random random = new Random();
	
	public String particleName;
	public float posX;
	public float posY;
	public float posZ;
	public float offsetX;
	public float offsetY;
	public float offsetZ;
	public float velocity;
	public int amount;

	public MessageSpawnParticle() {}

	public MessageSpawnParticle(String particleName, float posX, float posY, float posZ, float offsetX, float offsetY, float offsetZ, float velocity, int amount)
	{
		this.particleName = particleName;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.offsetZ = offsetZ;
		this.velocity = velocity;
		this.amount = amount;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.particleName = ByteBufUtils.readUTF8String(buf);
		this.posX = buf.readFloat();
		this.posY = buf.readFloat();
		this.posZ = buf.readFloat();
		this.offsetX = buf.readFloat();
		this.offsetY = buf.readFloat();
		this.offsetZ = buf.readFloat();
		this.velocity = buf.readFloat();
		this.amount = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeUTF8String(buf, this.particleName);
		buf.writeFloat(this.posX);
		buf.writeFloat(this.posY);
		buf.writeFloat(this.posZ);
		buf.writeFloat(this.offsetX);
		buf.writeFloat(this.offsetY);
		buf.writeFloat(this.offsetZ);
		buf.writeFloat(this.velocity);
		buf.writeInt(this.amount);
	}
	
	@Override
	public IMessage onMessage(MessageSpawnParticle message, MessageContext ctx) 
	{
        if (this.amount == 0)
        {
            double d0 = (double)(this.velocity * this.offsetX);
            double d2 = (double)(this.velocity * this.offsetY);
            double d4 = (double)(this.velocity * this.offsetZ);
            //this.clientWorldController.spawnParticle(this.particleName, this.posX, this.posY, this.posZ, d0, d2, d4);
        }
        else
        {
            for (int i = 0; i < this.amount; ++i)
            {
                double offsetX = this.random.nextGaussian() * (double)this.offsetX;
                double offsetY = this.random.nextGaussian() * (double)this.offsetY;
                double offsetZ = this.random.nextGaussian() * (double)this.offsetZ;
                double d6 = this.random.nextGaussian() * (double)this.velocity;
                double d7 = this.random.nextGaussian() * (double)this.velocity;
                double d8 = this.random.nextGaussian() * (double)this.velocity;
                //this.clientWorldController.spawnParticle(this.particleName, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, d6, d7, d8);
            }
        }
		
		return null;
	}
}
