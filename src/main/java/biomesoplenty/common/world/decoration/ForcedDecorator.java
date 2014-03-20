package biomesoplenty.common.world.decoration;

public class ForcedDecorator implements IBOPBiome
{
	protected BOPWorldFeatures bopWorldFeatures;

    public ForcedDecorator(int id)
    {
        bopWorldFeatures = BOPDecorationManager.getOrCreateBiomeFeatures(id);
    }

	@Override
	public BOPWorldFeatures getBiomeFeatures()
	{
		return bopWorldFeatures;
	}
}
