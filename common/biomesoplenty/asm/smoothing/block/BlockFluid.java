package biomesoplenty.asm.smoothing.block;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.IRETURN;

import java.util.Iterator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;

public class BlockFluid
{
    public static byte[] patchColourMultiplier(String name, byte[] bytes, boolean obfuscated)
    {
        String targetMethodName = "";

        if (obfuscated)
            targetMethodName ="b";
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
                toInject.add(new MethodInsnNode(INVOKESTATIC, "biomesoplenty/asm/smoothing/BOPBiomeTransitionSmoothing", "getWaterColourMultiplier", "(Lnet/minecraft/world/IBlockAccess;III)I"));
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
