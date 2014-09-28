/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class ASMUtil
{
	public static ClassNode getClassNode(byte[] classBytes)
	{
		ClassReader reader = new ClassReader(classBytes);
		ClassNode node = new ClassNode();
		reader.accept(node, 0);
		
		return node;
	}
	
	public static byte[] getBytes(ClassNode classNode)
	{
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		classNode.accept(writer);
		
		return writer.toByteArray();
	}
	
	public static MethodNode getMethodNode(ClassNode classNode, String methodName, String desc)
	{
		for (MethodNode method : classNode.methods)
		{
			if (method.name.equals(methodName) && method.desc.equals(desc)) return method;
		}
		
		throw new RuntimeException(methodName + " doesn't exist in " + classNode.name + "!");
	}
}
