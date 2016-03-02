/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.client.model;

import biomesoplenty.common.inventory.InventoryFlowerBasket;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;

public class ModelFlowerBasket extends IFlexibleBakedModel.Wrapper implements ISmartItemModel
{
    private IBakedModel emptyBakedModel;
    private IBakedModel filledBakedModel;
    
    public ModelFlowerBasket(IBakedModel emptyModel, IBakedModel filledModel)
    {
        super(null, DefaultVertexFormats.ITEM);
        
        this.emptyBakedModel = emptyModel;
        this.filledBakedModel = filledModel;
    }

    @Override
    public IBakedModel handleItemState(ItemStack stack) 
    {
        InventoryFlowerBasket inventory = new InventoryFlowerBasket(stack, null);
        boolean filled = false;
        
        for (int index = 0; index < inventory.getSizeInventory(); ++index)
        {
            if (inventory.getStackInSlot(index) != null)
            {
                filled = true;
                break;
            }
        }
        
        return filled ? this.filledBakedModel : this.emptyBakedModel;
    }
}
