package dev.sammy99jsp.fabric.integrations.rei;

import java.util.List;
import java.util.Optional;

import dev.sammy99jsp.fabric.block.Blocks;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;

public class ReiIntegration implements REIClientPlugin {

    @Override
    public String getPluginProviderName() {
        return new TranslatableText("mod.name").asString();
    }

    @Override
    public void registerCategories(CategoryRegistry registry) {
        // PulverizerCategory pcat = new PulverizerCategory();
        // registry.add(pcat);
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        // registry.add(
        //     new PulverizerCategory.PulverizerDisplay(
        //         List.of(
        //             EntryIngredient.builder()
        //                 .add(
        //                     EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(Blocks.PULVERIZER.asItem()))
        //                 ).build()
        //         ),
        //         List.of(
        //             EntryIngredient.builder()
        //                 .add(
        //                     EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(Blocks.PULVERIZER.asItem()))
        //                 ).build()
        //         ),
        //         Optional.empty()
        //     )
        // );
    }
}
