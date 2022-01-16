package com.loucaskreger.oxidized.registry;

import com.loucaskreger.oxidized.Oxidized;
import com.loucaskreger.oxidized.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.ToIntFunction;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Oxidized.MOD_ID);

    public static final RegistryObject<Block> COPPER_LANTERN = BLOCKS.register("copper_lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((light) -> 15).noOcclusion()));
    public static final RegistryObject<Block> COPPER_BUTTON = BLOCKS.register("copper_button",
            () -> new CopperButton(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.COPPER)));
    public static final RegistryObject<Block> COPPER_RAIL = BLOCKS.register("copper_rail",
            () -> new CopperRailBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.7F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> COPPER_KILN = BLOCKS.register("copper_kiln",
            () -> new CopperKilnBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(3.5F, 6.0F).lightLevel(blockLightLevel(13)).sound(SoundType.COPPER).noOcclusion()));
    public static final RegistryObject<Block> COPPER_PAN = BLOCKS.register("copper_pan",
            () -> new CopperPanBlock(Block.Properties.of(Material.METAL).randomTicks()));

    // Vertical Copper
    public static final RegistryObject<Block> VERTICAL_CUT_COPPER = BLOCKS.register("vertical_cut_copper",
            () -> new OxidizableBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.COPPER).randomTicks(), WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> VERTICAL_EXPOSED_CUT_COPPER = BLOCKS.register("vertical_exposed_cut_copper",
            () -> new OxidizableBlock(Block.Properties.copy(VERTICAL_CUT_COPPER.get()), WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> VERTICAL_WEATHERED_CUT_COPPER = BLOCKS.register("vertical_weathered_cut_copper",
            () -> new OxidizableBlock(Block.Properties.copy(VERTICAL_CUT_COPPER.get()), WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> VERTICAL_OXIDIZED_CUT_COPPER = BLOCKS.register("vertical_oxidized_cut_copper",
            () -> new OxidizableBlock(Block.Properties.copy(VERTICAL_CUT_COPPER.get()), WeatheringCopper.WeatherState.OXIDIZED));

    // Vertical Waxed Copper
    public static final RegistryObject<Block> WAXED_VERTICAL_CUT_COPPER = BLOCKS.register("waxed_vertical_cut_copper",
            () -> new OxidizableBlock(Block.Properties.copy(VERTICAL_CUT_COPPER.get()), WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> WAXED_VERTICAL_EXPOSED_CUT_COPPER = BLOCKS.register("waxed_vertical_exposed_cut_copper",
            () -> new OxidizableBlock(Block.Properties.copy(VERTICAL_CUT_COPPER.get()), WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> WAXED_VERTICAL_WEATHERED_CUT_COPPER = BLOCKS.register("waxed_vertical_weathered_cut_copper",
            () -> new OxidizableBlock(Block.Properties.copy(VERTICAL_CUT_COPPER.get()), WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> WAXED_VERTICAL_OXIDIZED_CUT_COPPER = BLOCKS.register("waxed_vertical_oxidized_cut_copper",
            () -> new OxidizableBlock(Block.Properties.copy(VERTICAL_CUT_COPPER.get()), WeatheringCopper.WeatherState.OXIDIZED));

    private static ToIntFunction<BlockState> blockLightLevel(int litLevel) {
        return (state) -> {
            return (Boolean) state.getValue(CopperKilnBlock.LIT) ? litLevel : 0;
        };
    }

}

