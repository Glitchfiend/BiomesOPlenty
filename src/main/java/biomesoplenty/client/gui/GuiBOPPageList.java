/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.client.gui;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class GuiBOPPageList extends GuiBOPPageDelegate
{
    public GuiBOPPageList(int width, int height, int top, int bottom, int slotHeight, int pageNumber)
    {
        super(width, height, top, bottom, slotHeight, pageNumber);
    }

    @Override
    protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {}

    @Override
    protected boolean isSelected(int slotIndex)
    {
        return false;
    }

    @Override
    protected void drawBackground() {}

    @Override
    protected void drawSlot(int entryID, int p_180791_2_, int p_180791_3_, int p_180791_4_, int mouseXIn, int mouseYIn)
    {
        this.getListEntry(entryID).drawEntry(entryID, p_180791_2_, p_180791_3_, this.getListWidth(), p_180791_4_, mouseXIn, mouseYIn, this.getSlotIndexFromScreenCoords(mouseXIn, mouseYIn) == entryID);
    }

    @Override
    protected void func_178040_a(int p_178040_1_, int p_178040_2_, int p_178040_3_)
    {
        this.getListEntry(p_178040_1_).setSelected(p_178040_1_, p_178040_2_, p_178040_3_);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseEvent)
    {
        if (this.isMouseYWithinSlotBounds(mouseY))
        {
            int l = this.getSlotIndexFromScreenCoords(mouseX, mouseY);

            if (l >= 0)
            {
                int i1 = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
                int j1 = this.top + 4 - this.getAmountScrolled() + l * this.slotHeight + this.headerPadding;
                int k1 = mouseX - i1;
                int l1 = mouseY - j1;

                if (this.getListEntry(l).mousePressed(l, mouseX, mouseY, mouseEvent, k1, l1))
                {
                    this.setEnabled(false);
                }
            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state)
    {
        for (int l = 0; l < this.getSize(); ++l)
        {
            int i1 = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
            int j1 = this.top + 4 - this.getAmountScrolled() + l * this.slotHeight + this.headerPadding;
            int k1 = mouseX - i1;
            int l1 = mouseY - j1;
            this.getListEntry(l).mouseReleased(l, mouseX, mouseY, state, k1, l1);
        }

        this.setEnabled(true);
    }

    public abstract GuiListExtended.IGuiListEntry getListEntry(int rowNum);
    
    @SideOnly(Side.CLIENT)
    public static class GuiSlideEntry extends GuiBOPPageList.GuiFieldEntry
    {
        private final GuiSlider.FormatHelper formatHelper;
        private final float min;
        private final float max;
        private final float value;
    
        public GuiSlideEntry(int fieldId, String labelText, boolean isVisible, GuiSlider.FormatHelper formatHelper, float min, float max, float value)
        {
            super(fieldId, labelText, isVisible);
            this.formatHelper = formatHelper;
            this.min = min;
            this.max = max;
            this.value = value;
        }
    
        public GuiSlider.FormatHelper getFormatHelper()
        {
            return this.formatHelper;
        }
    
        public float getMin()
        {
            return this.min;
        }
    
        public float getMax()
        {
            return this.max;
        }
    
        public float getValue()
        {
            return this.value;
        }
    }

    @SideOnly(Side.CLIENT)
    public interface GuiResponder extends GuiPageButtonList.GuiResponder
    {
        void handleEnumSelection(int fieldId, int ordinal);
    
        void handleBooleanSelection(int fieldId, boolean value);
    
        void handleFloatSelection(int fieldId, float value);
    
        void handleStringSelection(int fieldId, String value);
    
        void handleIntSelection(int fieldId, int value);   
    
    }

    @SideOnly(Side.CLIENT)
    public static class GuiFieldEntry
    {
        private final int fieldID;
        private final String labelText;
        private final boolean isVisible;
    
        public GuiFieldEntry(int fieldId, String labelText, boolean isVisible)
        {
            this.fieldID = fieldId;
            this.labelText = labelText;
            this.isVisible = isVisible;
        }
    
        public int getFieldId()
        {
            return this.fieldID;
        }
    
        public String getLabelText()
        {
            return this.labelText;
        }
    
        public boolean isVisible()
        {
            return this.isVisible;
        }
    }

    @SideOnly(Side.CLIENT)
    public static class GuiLabelEntry extends GuiFieldEntry
    {
        public GuiLabelEntry(int fieldId, String labelText, boolean isVisible)
        {
            super(fieldId, labelText, isVisible);
        }
    }

    @SideOnly(Side.CLIENT)
    public static class GuiRowEntry implements GuiListExtended.IGuiListEntry
    {
        private final Minecraft minecraft = Minecraft.getMinecraft();
        private final Gui guiLeft;
        private final Gui guiRight;
        Gui focusedGui;
    
        public GuiRowEntry(Gui guiLeft, Gui guiRight)
        {
            this.guiLeft = guiLeft;
            this.guiRight = guiRight;
        }
    
        public Gui getGuiLeft()
        {
            return this.guiLeft;
        }
    
        public Gui getGuiRight()
        {
            return this.guiRight;
        }
    
        @Override
        public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isVisible)
        {
            this.drawGui(this.guiLeft, y, mouseX, mouseY, false);
            this.drawGui(this.guiRight, y, mouseX, mouseY, false);
        }
    
        private void drawGui(Gui gui, int y, int mouseX, int mouseY, boolean isVisible)
        {
            if (gui != null)
            {
                if (gui instanceof GuiButton)
                {
                    this.drawGuiButton((GuiButton)gui, y, mouseX, mouseY, isVisible);
                }
                else if (gui instanceof GuiTextField)
                {
                    this.drawGuiTextField((GuiTextField)gui, y, isVisible);
                }
                else if (gui instanceof GuiLabel)
                {
                    this.drawGuiLabel((GuiLabel)gui, y, mouseX, mouseY, isVisible);
                }
            }
        }
    
        private void drawGuiButton(GuiButton guiButton, int y, int mouseX, int mouseY, boolean isVisible)
        {
            guiButton.yPosition = y;
    
            if (!isVisible)
            {
                guiButton.drawButton(this.minecraft, mouseX, mouseY);
            }
        }
    
        private void drawGuiTextField(GuiTextField guiTextField, int y, boolean isVisible)
        {
            guiTextField.yPosition = y;
    
            if (!isVisible)
            {
                guiTextField.drawTextBox();
            }
        }
    
        private void drawGuiLabel(GuiLabel guiLabel, int y, int mouseX, int mouseY, boolean isVisible)
        {
            guiLabel.field_146174_h = y;
    
            if (!isVisible)
            {
                guiLabel.drawLabel(this.minecraft, mouseX, mouseY);
            }
        }
    
        @Override
        public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_)
        {
            this.drawGui(this.guiLeft, p_178011_3_, 0, 0, true);
            this.drawGui(this.guiRight, p_178011_3_, 0, 0, true);
        }
    
    
        @Override
        public boolean mousePressed(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
        {
            return this.doMousePressed(this.guiLeft, x, y, mouseEvent) || this.doMousePressed(this.guiRight, x, y, mouseEvent);
        }
    
        private boolean doMousePressed(Gui gui, int x, int y, int mouseEvent)
        {
            if (gui == null)
            {
                return false;
            }
            else if (gui instanceof GuiButton)
            {
                if (((GuiButton)gui).mousePressed(this.minecraft, x, y))
                {
                    this.focusedGui = gui;
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else if (gui instanceof GuiTextField)
            {
                ((GuiTextField)gui).mouseClicked(x, y, mouseEvent);
    
                if (((GuiTextField)gui).isFocused())
                {
                    this.focusedGui = gui;
                }
                return false;
            }
            return false;
        }
    
    
        @Override
        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY)
        {
            this.doMouseReleased(this.guiLeft, x, y);
            this.doMouseReleased(this.guiRight, x, y);
        }
    
        public void doMouseReleased(Gui gui, int x, int y)
        {
            if (gui != null && (gui instanceof GuiButton))
            {
                ((GuiButton)gui).mouseReleased(x, y);
            }
        }
    
    }

    @SideOnly(Side.CLIENT)
    public static class GuiEnumButtonEntry<T extends Enum> extends GuiFieldEntry
    {
        private final T value;
    
        public GuiEnumButtonEntry(int fieldId, String textLabel, boolean isVisible, T value)
        {
            super(fieldId, textLabel, isVisible);
            this.value = value;
        }
    
        public T getValue()
        {
            return this.value;
        }
    }

    @SideOnly(Side.CLIENT)
    public static class GuiButtonEntry extends GuiFieldEntry
    {
        private final boolean value;
    
        public GuiButtonEntry(int fieldId, String textLabel, boolean isVisible, boolean value)
        {
            super(fieldId, textLabel, isVisible);
            this.value = value;
        }
    
        public boolean getValue()
        {
            return this.value;
        }
    }

    @SideOnly(Side.CLIENT)
    public static class EditBoxEntry extends GuiFieldEntry
    {
        private final Predicate validator;
    
        public EditBoxEntry(int fieldId, String labelText, boolean isVisible, Predicate validator)
        {
            super(fieldId, labelText, isVisible);
            this.validator = (Predicate)Objects.firstNonNull(validator, Predicates.alwaysTrue());
        }
    
        public Predicate getValidator()
        {
            return this.validator;
        }
    }
}
