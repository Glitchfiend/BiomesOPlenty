package worldcore.asm;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.FLOAD;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ForgeDummyContainer;

import org.lwjgl.opengl.GL11;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import worldcore.interfaces.IWCFog;

public class WCFogDistance implements IClassTransformer
{
    private static int fogX, fogZ;

    private static boolean fogInit;
    private static float storedFinalFogCloseness;
    
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
            targetMethodName ="a";
        else
            targetMethodName ="setupFog";

        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);

        Iterator<MethodNode> methods = classNode.methods.iterator();
        
        while (methods.hasNext())
        {
            MethodNode m = methods.next();
            int fdiv_index = -1;

            if (m.name.equals(targetMethodName) && (m.desc.equals("(IF)V")))
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

                    if (currentNode.getOpcode() == ALOAD)
                    {
                        if (timesFound == 22)
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
                mv.visitMethodInsn(INVOKESTATIC, "org/lwjgl/opengl/GL11", "glFogf", "(IF)V");
                mv.visitLabel(l71);
                mv.visitLineNumber(1922, l71);
                mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                mv.visitVarInsn(ALOAD, 3);
                mv.visitVarInsn(FLOAD, 6);
                mv.visitMethodInsn(INVOKESTATIC, "biomesoplenty/asm/BOPFogDistance", "setBiomeFogDistance", "(Lnet/minecraft/entity/Entity;F)V");
                mv.visitLabel(l32);
                */
                
                InsnList toInject = new InsnList();

                toInject.add(new VarInsnNode(ALOAD, 3));
                toInject.add(new VarInsnNode(ILOAD, 1));
                toInject.add(new VarInsnNode(FLOAD, 6));
                if (obfuscated)
                    toInject.add(new MethodInsnNode(INVOKESTATIC, "worldcore/asm/WCFogDistance", "setBiomeFogDistance", "(Lnn;IF)V"));
                else
                    toInject.add(new MethodInsnNode(INVOKESTATIC, "worldcore/asm/WCFogDistance", "setBiomeFogDistance", "(Lnet/minecraft/entity/Entity;IF)V"));
                
                m.instructions.insertBefore(m.instructions.get(fdiv_index), toInject);
                
                break;
            }
        }
        
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray();
    }
    
    public static void setBiomeFogDistance(Entity entity, int distance, float farPlaneDistance)
    {
        World world = entity.worldObj;
        
        int playerX = MathHelper.floor_double(entity.posX);
        int playerZ = MathHelper.floor_double(entity.posZ);
        
        if (playerX == fogX && playerZ == fogZ && fogInit)
        {
            if (distance < 0)
            {
                GL11.glFogf(GL11.GL_FOG_START, 0.0F);
                GL11.glFogf(GL11.GL_FOG_END, farPlaneDistance * 0.8F);
            }
            else
            {
                GL11.glFogf(GL11.GL_FOG_START, farPlaneDistance * (storedFinalFogCloseness / 10));
                GL11.glFogf(GL11.GL_FOG_END, Math.min(farPlaneDistance, 192.0F) * storedFinalFogCloseness);
            }
            return;
        }
        
        fogInit = true;
        
        int blenddistance = Minecraft.getMinecraft().gameSettings.fancyGraphics ? ForgeDummyContainer.blendRanges[Minecraft.getMinecraft().gameSettings.renderDistance] : 0;
        
        int divider = 0;
        
        float fogCloseness = 0.0F;

        for (int x = -blenddistance; x <= blenddistance; ++x)
        {
            for (int z = -blenddistance; z <= blenddistance; ++z)
            {
                BiomeGenBase biome = world.getBiomeGenForCoords(playerX + x, playerZ + z);

                if (biome instanceof IWCFog)
                {
                    fogCloseness += ((IWCFog)biome).getFogCloseness();
                }
                else
                {
                    fogCloseness += 1.0F;
                }
                
                divider++;
            }
        }
        
        float finalFogCloseness = fogCloseness / divider;
        
        fogX = playerX;
        fogZ = playerZ;
        storedFinalFogCloseness = finalFogCloseness;
        
        if (distance < 0)
        {
            GL11.glFogf(GL11.GL_FOG_START, 0.0F);
            GL11.glFogf(GL11.GL_FOG_END, farPlaneDistance * 0.8F);
        }
        else
        {
            GL11.glFogf(GL11.GL_FOG_START, farPlaneDistance * (finalFogCloseness / 10));
            GL11.glFogf(GL11.GL_FOG_END, Math.min(farPlaneDistance, 192.0F) * finalFogCloseness);
        }
    }
}
