package biomesoplenty.forge.common.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum ConnectedProperty implements StringRepresentable {
    MIDDLE("middle"),
    BOTTOM("bottom"),
    TOP("top");

    private final String name;

    ConnectedProperty(String p_61743_) {
        this.name = p_61743_;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}