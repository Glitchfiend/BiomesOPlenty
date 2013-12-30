package biomesoplenty.common.world.forceddecorators;

import biomesoplenty.common.world.decoration.IBOPDecoration;

public class SwampForcedDecorator implements IBOPDecoration
{
	@Override
	public int getWorldGenPerChunk(String fieldName) 
	{
		if (fieldName.equals("mudPerChunk")) return 3;
		
		return 0;
	}
}
