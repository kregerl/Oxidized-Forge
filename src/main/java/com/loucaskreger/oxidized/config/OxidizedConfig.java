package com.loucaskreger.oxidized.config;


import net.minecraftforge.common.ForgeConfigSpec;

public class OxidizedConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static ForgeConfigSpec.ConfigValue<Integer> copperRailRange;
    public static ForgeConfigSpec.ConfigValue<Integer> pulsarReach;
    public static ForgeConfigSpec.ConfigValue<Float> ironNuggetChance;
    public static ForgeConfigSpec.ConfigValue<Float> goldNuggetChance;
    public static ForgeConfigSpec.ConfigValue<Float> sandChance;
    public static ForgeConfigSpec.ConfigValue<Float> gravelChance;
    public static ForgeConfigSpec.ConfigValue<Float> emeraldChance;
    public static ForgeConfigSpec.ConfigValue<Integer> copperGolemButtonProbability;

    static {
        BUILDER.push("Panning Drop Chances");

        ironNuggetChance = BUILDER.define("Iron Nugget", 0.15F);
        goldNuggetChance = BUILDER.define("Gold Nugget", 0.15F);
        emeraldChance = BUILDER.define("Emerald", 0.02F);
        sandChance = BUILDER.define("Sand", 0.24F);
        gravelChance = BUILDER.define("Gravel", 0.12F);

        BUILDER.pop();

        BUILDER.push("Misc");

        copperRailRange = BUILDER.comment("The range in blocks that copper rails will be powered for.").define("Copper Rail Range", 50);
        pulsarReach = BUILDER.comment("The range in blocks that the copper pulsar can retrieve items from.").define("Pulsar Reach", 10);

        BUILDER.pop();

        BUILDER.push("Copper Golem");

        copperGolemButtonProbability = BUILDER.define("Copper Golem Button Press Probability", 30);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }


}
