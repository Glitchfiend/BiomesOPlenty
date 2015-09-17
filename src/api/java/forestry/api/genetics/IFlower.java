/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.genetics;

import net.minecraft.block.Block;

public interface IFlower extends Comparable<IFlower> {

	Block getBlock();
	int getMeta();

	double getWeight();
	void setWeight(double weight);

	boolean isPlantable();

}
