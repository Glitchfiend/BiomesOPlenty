package biomesoplenty.common.world.forceddecorators;

import biomesoplenty.common.world.IBOPDecoration;

public class SwampForcedDecorator implements IBOPDecoration
{
	@Override
	public int getWorldGenPerChunk(String fieldName) 
	{
		if (fieldName.equals("mudPerChunk")) return 9;
		
		return 0;
	}
}
