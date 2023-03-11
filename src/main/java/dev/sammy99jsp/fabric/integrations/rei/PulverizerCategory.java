package dev.sammy99jsp.fabric.integrations.rei;

import java.util.List;
import java.util.Optional;

import dev.sammy99jsp.fabric.Mod;
import dev.sammy99jsp.fabric.block.Blocks;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class PulverizerCategory implements DisplayCategory<PulverizerCategory.PulverizerDisplay> {
    public static final CategoryIdentifier<PulverizerDisplay> DISPLAY = CategoryIdentifier.of(Mod.MOD_ID, "machine_pulverizer");

    @Override
    public CategoryIdentifier<PulverizerDisplay> getCategoryIdentifier() {
        // TODO Auto-generated method stub
        return DISPLAY;
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(Blocks.PULVERIZER.asItem());
    }

    @Override
    public Text getTitle() {
        return new TranslatableText("mod.rei.category");
    }


    public static class PulverizerDisplay extends BasicDisplay {
        public PulverizerDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs,
                Optional<Identifier> location) {
            super(inputs, outputs, location);
        }

        @Override
        public CategoryIdentifier<?> getCategoryIdentifier() {
            return DISPLAY;
        }

    }
    
}
