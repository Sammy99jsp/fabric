package dev.sammy99jsp.fabric.integrations.rei.gui.element;

import java.util.List;
import java.util.stream.Stream;

import com.google.common.base.Function;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.widgets.Panel;
import me.shedaniel.rei.api.client.gui.widgets.Slot;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;

public class PaddedSlot {
    private final int PADDING;
    private Panel background;
    private Slot slot;

    public PaddedSlot(Rectangle rect) {
        this(rect, 2);
    }

    public PaddedSlot(Rectangle rect, final int padding) {
        PADDING = padding;
        this.background = Widgets.createSlotBase(rect.clone());
        rect.translate(PADDING, PADDING);
        this.slot = Widgets.createSlot(rect.getLocation()).disableBackground();
    }

    public PaddedSlot slot(Function<Slot, Slot> func) {
        func.apply(this.slot);
        return this;
    }

    public List<Widget> into() {
        return List.of(background, slot);
    }
    
}
