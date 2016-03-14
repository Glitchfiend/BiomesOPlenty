/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
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

import org.lwjgl.input.Mouse;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiListButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.gui.GuiTextField;

public class GuiBOPPageTable extends GuiBOPPageList
{
    private final List<GuiBOPPageList.GuiRowEntry> allRows = new ArrayList<GuiBOPPageList.GuiRowEntry>();
    private final Map<Integer, Gui> fieldIdToGuiMap = new HashMap<Integer, Gui>();
    private final List<GuiTextField> allTextFieldGuis = new ArrayList<GuiTextField>();
    private final GuiBOPPageList.GuiFieldEntry[] fields;
    private GuiBOPPageList.GuiResponder responder;
    private Gui focusedGui;
    
    public GuiBOPPageTable(int width, int height, int top, int bottom, int slotHeight, int pageNumber, GuiBOPPageList.GuiResponder responder, GuiBOPPageList.GuiFieldEntry... fields)
    {
        super(width, height, top, bottom, slotHeight, pageNumber);

        this.responder = responder;
        this.fields = fields;
    }
    
    @Override
    public void setVisible(boolean isVisible)
    {
        if (isVisible)
        {
            for (GuiBOPPageList.GuiFieldEntry field : this.fields)
            {
                if (field != null)
                {
                    Gui gui = this.fieldIdToGuiMap.get(field.getFieldId());
                    this.setVisible(gui, true);
                }
            }
        }
        else
        {
            for (GuiBOPPageList.GuiFieldEntry field : this.fields)
            {
                if (field != null)
                {
                    Gui gui = this.fieldIdToGuiMap.get(field.getFieldId());
                    this.setVisible(gui, false);
                }
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
            GuiBOPPageList.GuiFieldEntry fieldLeft = fields[k];
            GuiBOPPageList.GuiFieldEntry fieldRight = k < fields.length - 1 ? fields[k + 1] : null;

            Gui guiLeft = this.createGui(fieldLeft, 0, fieldRight == null);
            Gui guiRight = this.createGui(fieldRight, 160, fieldLeft == null);

            GuiBOPPageList.GuiRowEntry row = new GuiBOPPageList.GuiRowEntry(guiLeft, guiRight);
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
            GuiBOPPageList.GuiFieldEntry guilistentryLeft = this.fields[i];
            GuiBOPPageList.GuiFieldEntry guilistentryRight = i < this.fields.length - 1 ? this.fields[i + 1] : null;
            Gui guiLeft = (Gui)this.fieldIdToGuiMap.get(guilistentryLeft.getFieldId());
            Gui guiRight = guilistentryRight != null ? (Gui)this.fieldIdToGuiMap.get(guilistentryRight.getFieldId()) : null;
            GuiBOPPageList.GuiRowEntry guientry = new GuiBOPPageList.GuiRowEntry(guiLeft, guiRight);
            this.allRows.add(guientry);
        }
    }
    
    @Override
    public Gui getFocusedGuiElement()
    {
        return this.focusedGui;
    }
    
    @Override
    public Gui getGui(int fieldId)
    {
        return (Gui)this.fieldIdToGuiMap.get(fieldId);
    }
    

    
    private Gui createGui(GuiBOPPageList.GuiFieldEntry field, int xOffset, boolean hasNoNeighbor)
    {
        if (field instanceof GuiBOPPageList.GuiSlideEntry)
        {
            return (Gui)this.createSlider(this.width / 2 - 155 + xOffset, 0, (GuiBOPPageList.GuiSlideEntry)field);
        }
        else if (field instanceof GuiBOPPageList.GuiButtonEntry)
        {
            return (Gui)this.createListButton(this.width / 2 - 155 + xOffset, 0, (GuiBOPPageList.GuiButtonEntry)field);
        }
        else if (field instanceof GuiBOPPageList.EditBoxEntry)
        {
            return (Gui)this.createTextField(this.width / 2 - 155 + xOffset, 0, (GuiBOPPageList.EditBoxEntry)field);
        }
        else if (field instanceof GuiBOPPageList.GuiLabelEntry)
        {
            return (Gui)this.createLabel(this.width / 2 - 155 + xOffset, 0, (GuiBOPPageList.GuiLabelEntry)field, hasNoNeighbor);
        }
        else if (field instanceof GuiBOPPageList.GuiEnumButtonEntry)
        {
            return (Gui)this.createEnumButton(this.width / 2 - 155 + xOffset, 0, (GuiBOPPageList.GuiEnumButtonEntry)field);
        }
        else
        {
            return  null;
        }
    }
    
    //Mouse clicked
    
    private GuiSlider createSlider(int xPosition, int yPosition, GuiBOPPageList.GuiSlideEntry field)
    {
        GuiSlider guislider = new GuiSlider(this.responder, field.getFieldId(), xPosition, yPosition, field.getLabelText(), field.getMin(), field.getMax(), field.getValue(), field.getFormatHelper());
        guislider.visible = field.isVisible();
        return guislider;
    }

    private GuiListButton createListButton(int xPosition, int yPosition, GuiBOPPageList.GuiButtonEntry field)
    {
        GuiListButton guilistbutton = new GuiListButton(this.responder, field.getFieldId(), xPosition, yPosition, field.getLabelText(), field.getValue());
        guilistbutton.visible = field.isVisible();
        return guilistbutton;
    }
    
    private GuiEnumButton createEnumButton(int xPosition, int yPosition, GuiBOPPageList.GuiEnumButtonEntry field)
    {
        GuiEnumButton guienumbutton = new GuiEnumButton(this.responder, field.getFieldId(), xPosition, yPosition, field.getLabelText(), field.getValue());
        guienumbutton.visible = field.isVisible();
        return guienumbutton;
    }

    private GuiTextField createTextField(int xPosition, int yPosition, GuiBOPPageList.EditBoxEntry field)
    {
        GuiTextField guitextfield = new GuiTextField(field.getFieldId(), this.mc.fontRendererObj, xPosition, yPosition, 150, 20);
        guitextfield.setText(field.getLabelText());
        guitextfield.setGuiResponder(this.responder);
        guitextfield.setVisible(field.isVisible());
        guitextfield.setValidator(field.getValidator());
        return guitextfield;
    }

    private GuiLabel createLabel(int xPosition, int yPosition, GuiBOPPageList.GuiLabelEntry field, boolean hasNoNeighbor)
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

    @Override
    public void keyTyped(char typedChar, int keyCode)
    {
        if (this.focusedGui instanceof GuiTextField)
        {
            GuiTextField guitextfield = (GuiTextField)this.focusedGui;
            int j;

            //Check if aren't we are pasting text
            if (!GuiScreen.isKeyComboCtrlV(keyCode))
            {
                if (keyCode == 15) //Tab is pressed
                {
                    guitextfield.setFocused(false);
                    int focusedGuiIndex = this.allTextFieldGuis.indexOf(this.focusedGui);

                    if (GuiScreen.isShiftKeyDown())
                    {
                        if (focusedGuiIndex == 0) //Jump back to the end of the list when at the start
                        {
                            focusedGuiIndex = this.allTextFieldGuis.size() - 1;
                        }
                        else
                        {
                            --focusedGuiIndex; //Cycle backwards through the text fields
                        }
                    }
                    else if (focusedGuiIndex == this.allTextFieldGuis.size() - 1) //Jump back to the start of the list when at the end
                    {
                        focusedGuiIndex = 0;
                    }
                    else
                    {
                        ++focusedGuiIndex; //Cycle forwards through the text fields
                    }

                    this.focusedGui = (Gui)this.allTextFieldGuis.get(focusedGuiIndex);
                    guitextfield = (GuiTextField)this.focusedGui;
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
                    guitextfield.textboxKeyTyped(typedChar, keyCode);
                }
            }
            else
            {
                String s = GuiScreen.getClipboardString();
                String[] astring = s.split(";");
                j = this.allTextFieldGuis.indexOf(this.focusedGui);
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
    
    public GuiBOPPageList.GuiRowEntry getRow(int rowNum)
    {
        return (GuiBOPPageList.GuiRowEntry)this.allRows.get(rowNum);
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

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        super.mouseClicked(mouseX, mouseY, Mouse.getEventButton());
        int l = this.getSlotIndexFromScreenCoords(mouseX, mouseY);

        if (l >= 0)
        {
            GuiBOPPageTable.GuiRowEntry row = this.getRow(l);

            if (this.focusedGui != row.focusedGui && this.focusedGui != null && this.focusedGui instanceof GuiTextField)
            {
                ((GuiTextField)this.focusedGui).setFocused(false);
            }

            this.focusedGui = row.focusedGui;
        }
    }
}
