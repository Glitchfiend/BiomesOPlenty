package biomesoplenty.forge.common.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum QuarterProperty implements StringRepresentable {
    SOUTH_EAST("south_east"),
    SOUTH_WEST("south_west"),
    NORTH_WEST("north_west"),
    NORTH_EAST("north_east");

    private final String name;

    QuarterProperty(String p_61743_) {
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