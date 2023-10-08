package dev.sammy99jsp.fabric.integrations.rei;

import java.util.List;
import java.util.Optional;

import dev.sammy99jsp.fabric.block.Blocks;
import dev.sammy99jsp.fabric.integrations.rei.gui.element.ScaleIcons;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.entry.EntryRegistry;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.EntryDefinition;
import me.shedaniel.rei.api.common.entry.type.EntryType;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;

public class ReiIntegration implements REIClientPlugin {

    @Override
    public String getPluginProviderName() {
        return new TranslatableText("mod.name").asString();
    }

    @Override
    public void registerCategories(CategoryRegistry registry) {
        PulverizerCategory pcat = new PulverizerCategory();
        registry.add(pcat);
        registry.addWorkstations(PulverizerCategory.DISPLAY, EntryStacks.of(Blocks.PULVERIZER));
    }

    @Override
    public void registerEntries(EntryRegistry registry) {
        registry.addEntry(
            EntryStack.of(
                VanillaEntryTypes.ITEM,
                new ItemStack(net.minecraft.item.Items.ACACIA_BOAT, 2)
            )
        );
    }

    @Override
    public void registerTransferHandlers(TransferHandlerRegistry registry) {
        registry.register(new PulverizerTransferHandler());
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        ScaleIcons.load();

        registry.add(
            new PulverizerCategory.PulverizerDisplay(
                List.of(
                    EntryIngredient.builder()
                        .add(
                            EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(Blocks.PULVERIZER.asItem()))
                        ).build()
                ),
                List.of(
                    EntryIngredient.builder()
                        .add(
                            EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(Blocks.PULVERIZER.asItem()))
                        ).build()
                ),
                Optional.empty()
            )
        );
    }
}
