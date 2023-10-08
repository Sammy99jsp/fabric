package dev.sammy99jsp.fabric.integrations.rei;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;

import dev.sammy99jsp.fabric.Mod;
import dev.sammy99jsp.fabric.block.Blocks;
import dev.sammy99jsp.fabric.integrations.rei.gui.element.EnergyUsage;
import dev.sammy99jsp.fabric.integrations.rei.gui.element.PaddedSlot;
import dev.sammy99jsp.fabric.integrations.rei.gui.element.ScaleIcons;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class PulverizerCategory implements DisplayCategory<PulverizerCategory.PulverizerDisplay> {
    public static final CategoryIdentifier<PulverizerDisplay> DISPLAY = CategoryIdentifier.of(Mod.MOD_ID, "machine_pulverizer");

    @Override
    public CategoryIdentifier<PulverizerDisplay> getCategoryIdentifier() {
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

    private static Point relPoint(Rectangle rect, int x, int y) {
        Point p = rect.getLocation().clone();
        p.translate(x, y);
        return p;
    }

    private static Rectangle relRect(Rectangle rect, int x, int y, int width, int height) {
        Rectangle r = rect.clone();
        r.translate(x, y);
        r.setSize(width, height);
        return r;
    }


    @Override
    public List<Widget> setupDisplay(PulverizerDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();
        List<EntryIngredient> inputs  = display.getInputEntries();
        List<EntryIngredient> outputs = display.getOutputEntries();

        widgets.add(Widgets.createRecipeBase(bounds));

        widgets.addAll(
            new EnergyUsage(relPoint(bounds, 14, 8), 5_000, 20_000, 20_000)
                .into()
        );

        widgets.addAll(
            new PaddedSlot(relRect(bounds, 44, 12, 20, 20))
                .slot(s -> s.entries(inputs.get(0)).markInput())   
                .into()
        );


        widgets.add(
            ScaleIcons.Crush.of(relRect(bounds, 46, 34, 16, 16), 5_000)
        );

        widgets.addAll(
            new PaddedSlot(relRect(bounds, 112, 8, 24, 24), 4)
                .slot(s -> s.entries(outputs.get(0)).markOutput())
                .into()
        );

        widgets.addAll(
            new PaddedSlot(relRect(bounds, 114, 36, 20, 20), 2)
                .slot(s -> s.entries(outputs.get(0)).markOutput())
                .into()
        );

        

        widgets.add(Widgets.createArrow(relPoint(bounds, 76, 20)).animationDurationMS(5_000));

        widgets.add(
            Widgets
                .createLabel(
                    relPoint(bounds, 112, 46),
                    Text.of("12.5%")
                )
                .rightAligned()
                .color(0xFF404040, 0xFFBBBBBB)
                .shadow(false)
        );
        
        return widgets;
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
