package com.loucaskreger.oxidized.registry;

import com.loucaskreger.oxidized.Oxidized;
import com.loucaskreger.oxidized.screen.container.CopperKilnContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ContainerRegistry {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Oxidized.MOD_ID);

    public static final RegistryObject<MenuType<CopperKilnContainer>> COPPER_KILN = CONTAINERS.register("copper_kiln",
            () -> IForgeMenuType.create((windowId, inv, data) -> new CopperKilnContainer(windowId, inv)));
}
