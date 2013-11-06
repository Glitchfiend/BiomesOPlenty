package worldcore.asm;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.FLOAD;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ForgeDummyContainer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import worldcore.interfaces.IWCFog;

public class WCFogColour implements IClassTransformer
{
    @Override
    public byte[] transform(String name, String newname, byte[] bytes)
    {
        if (name.equals("net.minecraft.client.renderer.EntityRenderer")) 
        {
            return patchEntityRenderer(newname, bytes, false);
        }
        
        if (name.equals("bfe")) 
        {
            return patchEntityRenderer(newname, bytes, true);
        }
        
        return bytes;
    }
    
    public static byte[] patchEntityRenderer(String name, byte[] bytes, boolean obfuscated)
    {
        String targetMethodName = "";

        if (obfuscated)
            targetMethodName ="i";
        else
            targetMethodName ="updateFogColor";

        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);

        Iterator<MethodNode> methods = classNode.methods.iterator();
        
        while (methods.hasNext())
        {
            MethodNode m = methods.next();
            int fdiv_index = -1;

            if (m.name.equals(targetMethodName) && (m.desc.equals("(F)V")))
            {
                AbstractInsnNode currentNode = null;
                AbstractInsnNode targetNode = null;

                Iterator<AbstractInsnNode> iter = m.instructions.iterator();

                int index = -1;
                int timesFound = 0;

                while (iter.hasNext())
                {
                    index++;
                    currentNode = iter.next();

                    if (currentNode.getOpcode() == INVOKEVIRTUAL)
                    {
                        if (timesFound == 1)
                        {
                            targetNode = currentNode;
                            fdiv_index = index;
                            break;
                        }
                        else
                        {
                            timesFound++;
                        }
                    }
                }
                
                /*
                mv.visitLineNumber(1658, l8);
                mv.visitVarInsn(ALOAD, 3);
                mv.visitVarInsn(ALOAD, 2);
                mv.visitMethodInsn(INVOKESTATIC, "biomesoplenty/asm/BOPFogColour", "getFogVec", "(Lnet/minecraft/entity/Entity;Lnet/minecraft/world/World;)Lnet/minecraft/util/Vec3;");
                mv.visitVarInsn(ASTORE, 9);
                */
                
                InsnList toInject = new InsnList();

                toInject.add(new VarInsnNode(ALOAD, 3));
                toInject.add(new VarInsnNode(ALOAD, 2));
                toInject.add(new VarInsnNode(FLOAD, 1));
                if (obfuscated)
                    toInject.add(new MethodInsnNode(INVOKESTATIC, "worldcore/asm/WCFogColour", "getFogVec", "(Lnn;Labw;F)Latc;"));
                else
                    toInject.add(new MethodInsnNode(INVOKESTATIC, "worldcore/asm/WCFogColour", "getFogVec", "(Lnet/minecraft/entity/Entity;Lnet/minecraft/world/World;F)Lnet/minecraft/util/Vec3;"));
                toInject.add(new VarInsnNode(ASTORE, 9));
                
                m.instructions.insert(m.instructions.get(fdiv_index + 1), toInject);
                
                /*
                mv.visitLineNumber(1654, l8);
                mv.visitVarInsn(ALOAD, 2);
                mv.visitVarInsn(FLOAD, 1);
                mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/multiplayer/WorldClient", "getFogColor", "(F)Lnet/minecraft/util/Vec3;");
                mv.visitVarInsn(ASTORE, 9); 
                 */
                
                List<AbstractInsnNode> remNodes = new ArrayList();
                
                for (int i = -2; i <= 1; i++)
                {
                    remNodes.add(m.instructions.get(fdiv_index + i));
                }
                
                for (AbstractInsnNode remNode : remNodes)
                {
                    m.instructions.remove(remNode);
                }

                break;
            }
        }

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray();
    }
    
    public static int getFogBlendColour(World world, float partialRenderTick, int playerX, int playerZ)
    {
        int distance = Minecraft.getMinecraft().gameSettings.fancyGraphics ? ForgeDummyContainer.blendRanges[Minecraft.getMinecraft().gameSettings.renderDistance] : 0;
        
        int r = 0;
        int g = 0;
        int b = 0;
        
        float celestialAngle = world.getCelestialAngle(partialRenderTick);
        
        int wr = (int)(world.provider.getFogColor(celestialAngle, partialRenderTick).xCoord * 255);
        int wg = (int)(world.provider.getFogColor(celestialAngle, partialRenderTick).yCoord * 255);
        int wb = (int)(world.provider.getFogColor(celestialAngle, partialRenderTick).zCoord * 255);
        
        int defaultcolour = (wr << 16) + (wg << 8) + (wb);
        int divider = 0;
        
        for (int x = -distance; x <= distance; ++x)
        {
            for (int z = -distance; z <= distance; ++z)
            {
                BiomeGenBase biome = world.getBiomeGenForCoords(playerX + x, playerZ + z);
                int colour = 0;
                
                if (biome instanceof IWCFog)
                {
                    colour = ((IWCFog)biome).getFogColour();
                }
                else
                {
                    colour = defaultcolour;
                }
                    
                r += (colour & 0xFF0000) >> 16;
                g += (colour & 0x00FF00) >> 8;
                b += colour & 0x0000FF;
                divider++;
            }
        }
        
        float celestialAngleMultiplier = MathHelper.cos(celestialAngle * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (celestialAngleMultiplier < 0.0F)
        {
            celestialAngleMultiplier = 0.0F;
        }

        if (celestialAngleMultiplier > 1.0F)
        {
            celestialAngleMultiplier = 1.0F;
        }
        
        r *= celestialAngleMultiplier;
        g *= celestialAngleMultiplier;
        b *= celestialAngleMultiplier;

        int multiplier = (r / divider & 255) << 16 | (g / divider & 255) << 8 | b / divider & 255;

        return multiplier;
    }
    
    public static Vec3 getFogVec(Entity entity, World world, float partialRenderTick)
    {
        int x = MathHelper.floor_double(entity.posX);
        int z = MathHelper.floor_double(entity.posZ);
        
        int multiplier = getFogBlendColour(world, partialRenderTick, x, z);
        
        float r = (float)(multiplier >> 16 & 255) / 255.0F;
        float g = (float)(multiplier >> 8 & 255) / 255.0F;
        float b = (float)(multiplier & 255) / 255.0F;
        
        return world.getWorldVec3Pool().getVecFromPool(r, g, b);
    }
}
