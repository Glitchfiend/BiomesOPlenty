package biomesoplenty.common.enums;

public enum BOPClimates {

    ICE_CAP,
    FROZEN_DESERT,
    TUNDRA,
    COLD_DESERT,
    BOREAL,
    COLD_SWAMP,
    WET_TEMPERATE,
    DRY_TEMPERATE,
    COOL_TEMPERATE,
    WARM_TEMPERATE,
    HOT_SWAMP,
    TROPICAL,
    MEDITERANEAN,
    SAVANNA,
    HOT_DESERT;
    
    private static BOPClimates[] values = BOPClimates.values();
    
    public static BOPClimates lookup(int i) {return values[i];}

}
