/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.asm;

import java.io.IOException;
import java.util.Iterator;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;

public class BOPTransformer implements IClassTransformer
{
	private static final boolean isObfuscated;
	private static final String registerVariantNames;
	
	static
	{
		boolean obfuscated = true;
		
		try
		{
			obfuscated = Launch.classLoader.getClassBytes("net.minecraft.world.World") == null;
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		isObfuscated = obfuscated;
		registerVariantNames = isObfuscated ? "func_177592_e" : "registerVariantNames";
	}
	
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass)
    {
	    if (name.equals("net.minecraft.client.resources.model.ModelBakery") || name.equals("cxh"))
	    {
	    	System.out.println("Tweaking ModelBakery...");
	    	
	    	ClassNode classNode = ASMUtil.getClassNode(basicClass);
	    	MethodNode variantsMethodNode = ASMUtil.getMethodNode(classNode, registerVariantNames, "()V");
	    	
	    	InsnList instructions = new InsnList();
	    	
	    	instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
	    	instructions.add(new FieldInsnNode(Opcodes.GETFIELD, "net/minecraft/client/resources/model/ModelBakery", "variantNames", "Ljava/util/Map;"));
	    	instructions.add(new FieldInsnNode(Opcodes.GETSTATIC, "biomesoplenty/client/util/ModelHelper", "customVariantNames", "Ljava/util/HashMap;"));
	    	instructions.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "java/util/Map", "putAll", "(Ljava/util/Map;)V", true));
	    	
			variantsMethodNode.instructions.insertBefore(getRegisterVariantsInsertionPoint(variantsMethodNode), instructions);
			
	    	return ASMUtil.getBytes(classNode);
	    }
    	
	    return basicClass;
    }

    private AbstractInsnNode getRegisterVariantsInsertionPoint(MethodNode methodNode)
    {
		Iterator<AbstractInsnNode> iterator = methodNode.instructions.iterator();
		AbstractInsnNode returnNode = null;
		
		while (iterator.hasNext())
		{
			AbstractInsnNode currentNode = iterator.next();
			
			if (currentNode.getOpcode() == Opcodes.RETURN) returnNode = currentNode;
		}
		
		if (returnNode != null) return returnNode;
		
		throw new RuntimeException("The insertion point for registerVariantsNames() in ModelBakery was not found");
    }
}
