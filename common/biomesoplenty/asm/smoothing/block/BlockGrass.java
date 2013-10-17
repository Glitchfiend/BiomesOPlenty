package biomesoplenty.asm.smoothing.block;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.tree.AbstractInsnNode.*;

import java.util.Iterator;

import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class BlockGrass
{
    //ASM Injector
    public static byte[] patchColourMultiplier(String name, byte[] bytes, boolean obfuscated)
    {
        String targetMethodName = "";

        if (obfuscated)
            targetMethodName ="c";
        else
            targetMethodName ="colorMultiplier";

        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);

        Iterator<MethodNode> methods = classNode.methods.iterator();
        
        while (methods.hasNext())
        {
            MethodNode m = methods.next();
            int fdiv_index = -1;

            if (m.name.equals(targetMethodName) && (m.desc.equals("(Lnet/minecraft/world/IBlockAccess;III)I") || m.desc.equals("(Lacf;III)I")))
            {
                AbstractInsnNode currentNode = null;
                AbstractInsnNode targetNode = null;

                Iterator<AbstractInsnNode> iter = m.instructions.iterator();

                int index = -1;

                while (iter.hasNext())
                {
                    index++;
                    currentNode = iter.next();

                    if (currentNode.getOpcode() == ICONST_0)
                    {
                        targetNode = currentNode;
                        fdiv_index = index;
                    }
                }
                
                InsnList toInject = new InsnList();

                toInject.add(new VarInsnNode(ALOAD, 1));
                toInject.add(new VarInsnNode(ILOAD, 2));
                toInject.add(new VarInsnNode(ILOAD, 3));
                toInject.add(new VarInsnNode(ILOAD, 4));
                if (obfuscated)
                    toInject.add(new MethodInsnNode(INVOKESTATIC, "biomesoplenty/asm/smoothing/BOPBiomeTransitionSmoothing", "getGrassColourMultiplier", "(Lacf;III)I"));
                else
                    toInject.add(new MethodInsnNode(INVOKESTATIC, "biomesoplenty/asm/smoothing/BOPBiomeTransitionSmoothing", "getGrassColourMultiplier", "(Lnet/minecraft/world/IBlockAccess;III)I"));
                toInject.add(new InsnNode(IRETURN));
                
                m.instructions.insertBefore(targetNode, toInject);
                break;
            }
        }

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}
