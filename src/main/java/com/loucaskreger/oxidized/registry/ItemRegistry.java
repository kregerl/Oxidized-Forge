package com.loucaskreger.oxidized.registry;

import com.loucaskreger.oxidized.Oxidized;
import com.loucaskreger.oxidized.item.CopperPulsarItem;
import com.loucaskreger.oxidized.item.ModBlockItem;
import com.loucaskreger.oxidized.tier.RoseGoldTier;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Oxidized.MOD_ID);

    public static final RoseGoldTier ROSE_GOLD = new RoseGoldTier();

    public static final RegistryObject<Item> ROSE_GOLD_PICKAXE = ITEMS.register("rose_gold_pickaxe",
            () -> new PickaxeItem(ROSE_GOLD, 1, -2.8F, new Item.Properties().tab(Oxidized.OXIDIZED_TAB)));
    public static final RegistryObject<Item> ROSE_GOLD_AXE = ITEMS.register("rose_gold_axe",
            () -> new AxeItem(ROSE_GOLD, 6.0F, -3.0F, new Item.Properties().tab(Oxidized.OXIDIZED_TAB)));
    public static final RegistryObject<Item> ROSE_GOLD_SHOVEL = ITEMS.register("rose_gold_shovel",
            () -> new ShovelItem(ROSE_GOLD, 1.5F, -3.0F, new Item.Properties().tab(Oxidized.OXIDIZED_TAB)));
    public static final RegistryObject<Item> ROSE_GOLD_HOE = ITEMS.register("rose_gold_hoe",
            () -> new HoeItem(ROSE_GOLD, 0, -3.0F, new Item.Properties().tab(Oxidized.OXIDIZED_TAB)));
    public static final RegistryObject<Item> ROSE_GOLD_SWORD = ITEMS.register("rose_gold_sword",
            () -> new SwordItem(ROSE_GOLD, 4, -2.4F, new Item.Properties().tab(Oxidized.OXIDIZED_TAB)));
    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget",
            () -> new Item(new Item.Properties().tab(Oxidized.OXIDIZED_TAB)));


    public static final RegistryObject<Item> COPPER_LANTERN = ITEMS.register("copper_lantern", () -> new ModBlockItem(BlockRegistry.COPPER_LANTERN.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_RAIL = ITEMS.register("copper_rail", () -> new ModBlockItem(BlockRegistry.COPPER_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_BUTTON = ITEMS.register("copper_button", () -> new ModBlockItem(BlockRegistry.COPPER_BUTTON.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_KILN = ITEMS.register("copper_kiln", () -> new ModBlockItem(BlockRegistry.COPPER_KILN.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PAN = ITEMS.register("copper_pan", () -> new ModBlockItem(BlockRegistry.COPPER_PAN.get(), new Item.Properties()));

    public static final RegistryObject<Item> VERTICAL_CUT_COPPER = ITEMS.register("vertical_cut_copper", () -> new ModBlockItem(BlockRegistry.VERTICAL_CUT_COPPER.get(), new Item.Properties()));
    public static final RegistryObject<Item> VERTICAL_EXPOSED_CUT_COPPER = ITEMS.register("vertical_exposed_cut_copper", () -> new ModBlockItem(BlockRegistry.VERTICAL_EXPOSED_CUT_COPPER.get(), new Item.Properties()));
    public static final RegistryObject<Item> VERTICAL_WEATHERED_CUT_COPPER = ITEMS.register("vertical_weathered_cut_copper", () -> new ModBlockItem(BlockRegistry.VERTICAL_WEATHERED_CUT_COPPER.get(), new Item.Properties()));
    public static final RegistryObject<Item> VERTICAL_OXIDIZED_CUT_COPPER = ITEMS.register("vertical_oxidized_cut_copper", () -> new ModBlockItem(BlockRegistry.VERTICAL_OXIDIZED_CUT_COPPER.get(), new Item.Properties()));

    public static final RegistryObject<Item> WAXED_VERTICAL_CUT_COPPER = ITEMS.register("waxed_vertical_cut_copper", () -> new ModBlockItem(BlockRegistry.WAXED_VERTICAL_CUT_COPPER.get(), new Item.Properties()));
    public static final RegistryObject<Item> WAXED_VERTICAL_EXPOSED_CUT_COPPER = ITEMS.register("waxed_vertical_exposed_cut_copper", () -> new ModBlockItem(BlockRegistry.WAXED_VERTICAL_EXPOSED_CUT_COPPER.get(), new Item.Properties()));
    public static final RegistryObject<Item> WAXED_VERTICAL_WEATHERED_CUT_COPPER = ITEMS.register("waxed_vertical_weathered_cut_copper", () -> new ModBlockItem(BlockRegistry.WAXED_VERTICAL_WEATHERED_CUT_COPPER.get(), new Item.Properties()));
    public static final RegistryObject<Item> WAXED_VERTICAL_OXIDIZED_CUT_COPPER = ITEMS.register("waxed_vertical_oxidized_cut_copper", () -> new ModBlockItem(BlockRegistry.WAXED_VERTICAL_OXIDIZED_CUT_COPPER.get(), new Item.Properties()));

    public static final RegistryObject<Item> COPPER_GOLEM_SPAWN_EGG = ITEMS.register("copper_golem_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypeRegistry.COPPER_GOLEM, 0x996613, 0xD8A654, new Item.Properties().tab(Oxidized.OXIDIZED_TAB)));

    public static final RegistryObject<Item> COPPER_PULSAR_ITEM = ITEMS.register("copper_pulsar",
            () -> new CopperPulsarItem(new Item.Properties().tab(Oxidized.OXIDIZED_TAB)));

}
