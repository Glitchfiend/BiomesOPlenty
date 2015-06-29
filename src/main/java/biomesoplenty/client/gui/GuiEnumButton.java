package biomesoplenty.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEnumButton<T extends Enum> extends GuiButton
{
    private T value;
    private String localizationStr;
    private final GuiBOPPageList.GuiResponder guiResponder;

    public GuiEnumButton(GuiBOPPageList.GuiResponder responder, int fieldId, int x, int y, String localizationStr, T initialValue)
    {
        super(fieldId, x, y, 150, 20, "");
        this.localizationStr = localizationStr;
        this.value = initialValue;
        this.displayString = this.buildDisplayString();
        this.guiResponder = responder;
    }

    private String buildDisplayString()
    {
        return I18n.format(this.localizationStr, new Object[] {this.value.toString()});
    }

    public void setValue(T value)
    {
        this.value = value;
        this.displayString = this.buildDisplayString();
        this.guiResponder.handleEnumSelection(this.id, value.ordinal());
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        if (super.mousePressed(mc, mouseX, mouseY))
        {
            Object[] values = this.value.getClass().getEnumConstants();
            int len = values.length;
            this.value = (T)(values[(this.value.ordinal() + 1) % len]);
            this.displayString = this.buildDisplayString();
            this.guiResponder.handleEnumSelection(this.id, this.value.ordinal());
            return true;
        }
        else
        {
            return false;
        }
    }
}