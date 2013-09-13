package thaumcraft.api.aspects;



/**
 * @author Azanor
 * 
 * This interface is implemented by tile entites (or possibly anything else) like jars, alembics and the 
 * crucible so that they can act as an essentia source for blocks like the infusion altar.
 *
 */
public interface IAspectSource {
	
	/**
	 * This method is used to add a certain amount of an aspect to the tile entity.
	 * @param tag 
	 * @param amount
	 * @return the amount of aspect left over that could not be added.
	 */
	public int addToSource(Aspect tag, int amount);

	/**
	 * Removes a certain amount of a specific aspect from the tile entity
	 * @param tag
	 * @param amount
	 * @return true if that amount of aspect was available and was removed
	 */
	public boolean takeFromSource(Aspect tag, int amount);
	
	/**
	 * removes a bunch of different aspects and amounts from the tile entity.
	 * @param ot the ObjectTags object that contains the aspects and their amounts.
	 * @return true if all the aspects and their amounts were available and successfully removed
	 */
	public boolean takeFromSource(AspectList ot);
	
	/**
	 * Checks if the tile entity contains the listed amount (or more) of the aspect
	 * @param tag
	 * @param amount
	 * @return
	 */
	public boolean doesSourceContainAmount(Aspect tag,int amount);
	
	/**
	 * Checks if the tile entity contains all the listed aspects and their amounts
	 * @param ot the ObjectTags object that contains the aspects and their amounts.
	 * @return
	 */
	public boolean doesSourceContain(AspectList ot);
	
	/**
	 * Returns how much of the aspect this tile entity contains
	 * @param tag
	 * @return the amount of that aspect found
	 */
	public int sourceContains(Aspect tag);
	
	/**
	 * Returns all the aspects and their amounts that this tile entity contains
	 * @return
	 */
	public AspectList getSourceTags();
}
