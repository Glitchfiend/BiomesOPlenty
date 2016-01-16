package biomesoplenty.client.gui.inventory;

import static biomesoplenty.common.inventory.InventoryFlowerBasket.INVENTORY_ROWS;

import biomesoplenty.common.inventory.ContainerFlowerBasket;
import biomesoplenty.common.inventory.InventoryFlowerBasket;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiFlowerBasket extends GuiContainer
{
    //Reuse the chest texture to save us some trouble
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
    
    private final InventoryPlayer playerInventory;
    private InventoryFlowerBasket inventory;
    
    public GuiFlowerBasket(EntityPlayer player, InventoryFlowerBasket inventoryFlowerBasket) 
    {
        super(new ContainerFlowerBasket(player, inventoryFlowerBasket));
        
        this.playerInventory = player.inventory;
        this.inventory = inventoryFlowerBasket;
        this.ySize = 114 + INVENTORY_ROWS * 18;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String inventoryName = this.inventory.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(inventoryName, 8, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
        
        int drawStartX = (this.width - this.xSize) / 2;
        int drawStartY = (this.height - this.ySize) / 2;
        
        //Draw the top half of the bag inventory. Starts from the top left of the texture, offset by 17 pixels from the
        //top to account for the inventory title. Each row of slots is 18 pixels tall.
        this.drawTexturedModalRect(drawStartX, drawStartY, 0, 0, this.xSize, INVENTORY_ROWS * 18 + 17);
    
        //Draw the player's hotbar and inventory (96 pixels tall). Begin drawing beneath the top half of the inventory, based
        //on the number of rows drawn. The bottom half of the inventory begins 126 pixels from the top of the image.
        this.drawTexturedModalRect(drawStartX, drawStartY + INVENTORY_ROWS * 18 + 17, 0, 126, this.xSize, 96);
    }

}
