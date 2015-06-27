/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiListButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class GuiBOPPageTable extends GuiBOPPageDelegate
{
    private final List<GuiBOPPageTable.GuiRowEntry> allRows = new ArrayList<GuiBOPPageTable.GuiRowEntry>();
    private final Map<Integer, Gui> fieldIdToGuiMap = new HashMap<Integer, Gui>();
    private final List<GuiTextField> allTextFieldGuis = new ArrayList<GuiTextField>();
    private final GuiBOPPageTable.GuiFieldEntry[] fields;
    private GuiBOPPageTable.GuiResponder responder;
    private Gui field_178075_A;
    
    public GuiBOPPageTable(GuiBOPPageManager pageManager, int pageNumber, GuiBOPPageTable.GuiResponder responder, GuiBOPPageTable.GuiFieldEntry... fields)
    {
        super(pageManager, pageNumber);

        this.responder = responder;
        this.fields = fields;
    }
    
    @Override
    public void onPageSwapActive() 
    {
        for (GuiBOPPageTable.GuiFieldEntry field : this.fields)
        {
            if (field != null)
            {
                Gui gui = this.fieldIdToGuiMap.get(field.getFieldId());
                this.setVisible(gui, true);
            }
        }
    }
    
    @Override
    public void onPageSwapInactive() 
    {
        for (GuiBOPPageTable.GuiFieldEntry field : this.fields)
        {
            if (field != null)
            {
                Gui gui = this.fieldIdToGuiMap.get(field.getFieldId());
                this.setVisible(gui, false);
            }
        }
    }
    
    private void setVisible(Gui gui, boolean isVisible)
    {
        if (gui instanceof GuiButton)
        {
            ((GuiButton)gui).visible = isVisible;
        }
        else if (gui instanceof GuiTextField)
        {
            ((GuiTextField)gui).setVisible(isVisible);
        }
        else if (gui instanceof GuiLabel)
        {
            ((GuiLabel)gui).visible = isVisible;
        }
    }
    
    @Override
    public void setup()
    {
        // go through the fields in twos (so they go in 2 columns)
        for (int k = 0; k < fields.length; k += 2)
        {
            GuiBOPPageTable.GuiFieldEntry fieldLeft = fields[k];
            GuiBOPPageTable.GuiFieldEntry fieldRight = k < fields.length - 1 ? fields[k + 1] : null;

            Gui guiLeft = this.createGui(fieldLeft, 0, fieldRight == null);
            Gui guiRight = this.createGui(fieldRight, 160, fieldLeft == null);

            GuiBOPPageTable.GuiRowEntry row = new GuiBOPPageTable.GuiRowEntry(guiLeft, guiRight);
            this.allRows.add(row);

            if (fieldLeft != null && guiLeft != null)
            {
                this.fieldIdToGuiMap.put(fieldLeft.getFieldId(), guiLeft);

                if (guiLeft instanceof GuiTextField)
                {
                    this.allTextFieldGuis.add((GuiTextField)guiLeft);
                }
            }

            if (fieldRight != null && guiRight != null)
            {
                this.fieldIdToGuiMap.put(fieldRight.getFieldId(), guiRight);

                if (guiRight instanceof GuiTextField)
                {
                    this.allTextFieldGuis.add((GuiTextField)guiRight);
                }
            }
        }
        
        this.resetRows();
    }
    
    private void resetRows()
    {
        this.allRows.clear();

        for (int i = 0; i < this.fields.length; i += 2)
        {
            GuiBOPPageTable.GuiFieldEntry guilistentryLeft = this.fields[i];
            GuiBOPPageTable.GuiFieldEntry guilistentryRight = i < this.fields.length - 1 ? this.fields[i + 1] : null;
            Gui guiLeft = (Gui)this.fieldIdToGuiMap.get(guilistentryLeft.getFieldId());
            Gui guiRight = guilistentryRight != null ? (Gui)this.fieldIdToGuiMap.get(guilistentryRight.getFieldId()) : null;
            GuiBOPPageTable.GuiRowEntry guientry = new GuiBOPPageTable.GuiRowEntry(guiLeft, guiRight);
            this.allRows.add(guientry);
        }
    }
    
    public Gui func_178056_g()
    {
        return this.field_178075_A;
    }
    
    public Gui getGui(int fieldId)
    {
        return (Gui)this.fieldIdToGuiMap.get(fieldId);
    }
    
    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseEvent)
    {
        boolean flag = super.mouseClicked(mouseX, mouseY, mouseEvent);
        int l = this.getSlotIndexFromScreenCoords(mouseX, mouseY);

        if (l >= 0)
        {
            GuiBOPPageTable.GuiRowEntry row = this.getRow(l);

            if (this.field_178075_A != row.focusedGui && this.field_178075_A != null && this.field_178075_A instanceof GuiTextField)
            {
                ((GuiTextField)this.field_178075_A).setFocused(false);
            }

            this.field_178075_A = row.focusedGui;
        }

        return flag;
    }
    
    private Gui createGui(GuiBOPPageTable.GuiFieldEntry field, int xOffset, boolean hasNoNeighbor)
    {
        if (field instanceof GuiBOPPageTable.GuiSlideEntry)
        {
            return (Gui)this.createSlider(this.width / 2 - 155 + xOffset, 0, (GuiBOPPageTable.GuiSlideEntry)field);
        }
        else if (field instanceof GuiBOPPageTable.GuiButtonEntry)
        {
            return (Gui)this.createListButton(this.width / 2 - 155 + xOffset, 0, (GuiBOPPageTable.GuiButtonEntry)field);
        }
        else if (field instanceof GuiBOPPageTable.EditBoxEntry)
        {
            return (Gui)this.createTextField(this.width / 2 - 155 + xOffset, 0, (GuiBOPPageTable.EditBoxEntry)field);
        }
        else if (field instanceof GuiBOPPageTable.GuiLabelEntry)
        {
            return (Gui)this.createLabel(this.width / 2 - 155 + xOffset, 0, (GuiBOPPageTable.GuiLabelEntry)field, hasNoNeighbor);
        }
        else if (field instanceof GuiBOPPageTable.GuiEnumButtonEntry)
        {
            return (Gui)this.createEnumButton(this.width / 2 - 155 + xOffset, 0, (GuiBOPPageTable.GuiEnumButtonEntry)field);
        }
        else
        {
            return  null;
        }
    }
    
    //Mouse clicked
    
    private GuiSlider createSlider(int xPosition, int yPosition, GuiBOPPageTable.GuiSlideEntry field)
    {
        GuiSlider guislider = new GuiSlider(this.responder, field.getFieldId(), xPosition, yPosition, field.getLabelText(), field.getMin(), field.getMax(), field.getValue(), field.getFormatHelper());
        guislider.visible = field.isVisible();
        return guislider;
    }

    private GuiListButton createListButton(int xPosition, int yPosition, GuiBOPPageTable.GuiButtonEntry field)
    {
        GuiListButton guilistbutton = new GuiListButton(this.responder, field.getFieldId(), xPosition, yPosition, field.getLabelText(), field.getValue());
        guilistbutton.visible = field.isVisible();
        return guilistbutton;
    }
    
    private GuiEnumButton createEnumButton(int xPosition, int yPosition, GuiBOPPageTable.GuiEnumButtonEntry field)
    {
        GuiEnumButton guienumbutton = new GuiEnumButton(this.responder, field.getFieldId(), xPosition, yPosition, field.getLabelText(), field.getValue());
        guienumbutton.visible = field.isVisible();
        return guienumbutton;
    }

    private GuiTextField createTextField(int xPosition, int yPosition, GuiBOPPageTable.EditBoxEntry field)
    {
        GuiTextField guitextfield = new GuiTextField(field.getFieldId(), this.mc.fontRendererObj, xPosition, yPosition, 150, 20);
        guitextfield.setText(field.getLabelText());
        guitextfield.func_175207_a(this.responder); // setResponder
        guitextfield.setVisible(field.isVisible());
        guitextfield.func_175205_a(field.getValidator()); // setValidator
        return guitextfield;
    }

    private GuiLabel createLabel(int xPosition, int yPosition, GuiBOPPageTable.GuiLabelEntry field, boolean hasNoNeighbor)
    {
        GuiLabel guilabel;

        if (hasNoNeighbor)
        {
            guilabel = new GuiLabel(this.mc.fontRendererObj, field.getFieldId(), xPosition, yPosition, this.width - xPosition * 2, 20, -1);
        }
        else
        {
            guilabel = new GuiLabel(this.mc.fontRendererObj, field.getFieldId(), xPosition, yPosition, 150, 20, -1);
        }

        guilabel.visible = field.isVisible();
        guilabel.func_175202_a(field.getLabelText()); // setText
        guilabel.setCentered();
        return guilabel;
    }

    public void func_178062_a(char p_178062_1_, int p_178062_2_)
    {
        if (this.field_178075_A instanceof GuiTextField)
        {
            GuiTextField guitextfield = (GuiTextField)this.field_178075_A;
            int j;

            if (!GuiScreen.isKeyComboCtrlV(p_178062_2_))
            {
                if (p_178062_2_ == 15)
                {
                    guitextfield.setFocused(false);
                    int j1 = this.allTextFieldGuis.indexOf(this.field_178075_A);

                    if (GuiScreen.isShiftKeyDown())
                    {
                        if (j1 == 0)
                        {
                            j1 = this.allTextFieldGuis.size() - 1;
                        }
                        else
                        {
                            --j1;
                        }
                    }
                    else if (j1 == this.allTextFieldGuis.size() - 1)
                    {
                        j1 = 0;
                    }
                    else
                    {
                        ++j1;
                    }

                    this.field_178075_A = (Gui)this.allTextFieldGuis.get(j1);
                    guitextfield = (GuiTextField)this.field_178075_A;
                    guitextfield.setFocused(true);
                    int k1 = guitextfield.yPosition + this.slotHeight;
                    j = guitextfield.yPosition;

                    if (k1 > this.bottom)
                    {
                        this.amountScrolled += (float)(k1 - this.bottom);
                    }
                    else if (j < this.top)
                    {
                        this.amountScrolled = (float)j;
                    }
                }
                else
                {
                    guitextfield.textboxKeyTyped(p_178062_1_, p_178062_2_);
                }
            }
            else
            {
                String s = GuiScreen.getClipboardString();
                String[] astring = s.split(";");
                j = this.allTextFieldGuis.indexOf(this.field_178075_A);
                int k = j;
                String[] astring1 = astring;
                int l = astring.length;

                for (int i1 = 0; i1 < l; ++i1)
                {
                    String s1 = astring1[i1];
                    ((GuiTextField)this.allTextFieldGuis.get(k)).setText(s1);

                    if (k == this.allTextFieldGuis.size() - 1)
                    {
                        k = 0;
                    }
                    else
                    {
                        ++k;
                    }

                    if (k == j)
                    {
                        break;
                    }
                }
            }
        }
    }
    
    public GuiBOPPageTable.GuiRowEntry getRow(int rowNum)
    {
        return (GuiBOPPageTable.GuiRowEntry)this.allRows.get(rowNum);
    }

    @Override
    public int getSize()
    {
        return this.allRows.size();
    }

    @Override
    public int getListWidth()
    {
        return 400;
    }

    @Override
    protected int getScrollBarX()
    {
        return super.getScrollBarX() + 32;
    }

    @Override
    public GuiListExtended.IGuiListEntry getListEntry(int rowNum)
    {
        return this.getRow(rowNum);
    }

    @SideOnly(Side.CLIENT)
    public static class EditBoxEntry extends GuiBOPPageTable.GuiFieldEntry
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

    @SideOnly(Side.CLIENT)
    public static class GuiButtonEntry extends GuiBOPPageTable.GuiFieldEntry
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
    public static class GuiEnumButtonEntry<T extends Enum> extends GuiBOPPageTable.GuiFieldEntry
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
    public static class GuiRowEntry implements GuiListExtended.IGuiListEntry
    {
        private final Minecraft minecraft = Minecraft.getMinecraft();
        private final Gui guiLeft;
        private final Gui guiRight;
        private Gui focusedGui;

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
    public static class GuiLabelEntry extends GuiBOPPageTable.GuiFieldEntry
    {
        public GuiLabelEntry(int fieldId, String labelText, boolean isVisible)
        {
            super(fieldId, labelText, isVisible);
        }
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
    public interface GuiResponder extends GuiPageButtonList.GuiResponder
    {
        void handleEnumSelection(int fieldId, int ordinal);

        void handleBooleanSelection(int fieldId, boolean value);

        void handleFloatSelection(int fieldId, float value);

        void handleStringSelection(int fieldId, String value);

        void handleIntSelection(int fieldId, int value);   

    }

    @SideOnly(Side.CLIENT)
    public static class GuiSlideEntry extends GuiBOPPageTable.GuiFieldEntry
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
}
