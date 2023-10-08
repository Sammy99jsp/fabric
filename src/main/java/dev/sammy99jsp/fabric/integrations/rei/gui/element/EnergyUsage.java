package dev.sammy99jsp.fabric.integrations.rei.gui.element;

import java.util.List;

import me.shedaniel.math.Dimension;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.widgets.Panel;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.WidgetWithBounds;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class EnergyUsage {
    public static final String UNIT = "RF";

    public static final Dimension DIMENSIONS = new Dimension(16, 52);
    

    private final int energy; 
    private final int capacity;
    private final int duration;
    private final Panel background;
    private final WidgetWithBounds bar;

    public EnergyUsage(final Point location, final int duration, final int capacity, final int energy) {
        this.duration = duration;
        this.capacity = capacity;
        this.energy   = energy;
        this.background = Widgets.createSlotBase(
            new Rectangle(location.x, location.y, DIMENSIONS.width, DIMENSIONS.height)
        );

        this.bar =  new EnergyBar(
                new Rectangle(location.x+1, location.y+1, DIMENSIONS.width-2, DIMENSIONS.height-2),
                this.duration, this.capacity, this.energy
            ).withTooltip();
            
    }

    public List<Widget> into() {
        return List.of(this.background, this.bar);
    }

    private static class EnergyBar extends ModReiElement {
        private final int capacity;
        private final int energy;
        private final double maxHeight;

        private final double scaleX;
        private final double scaleY;

        protected EnergyBar(final Rectangle bounds,  final double animationDuration, final int capacity, final int energy) {
            super(
                bounds,
                new Rectangle(1, 145, 12, 40),
                new Rectangle(17, 145, 12, 40),
                animationDuration
            );

            this.capacity  = capacity;
            this.energy    = energy; 
            this.maxHeight = (((double)energy / (double)this.capacity)  * (double)this.beforeFrame.height);
            this.scaleY    = (double)bounds.height / (double)super.beforeFrame.height;
            this.scaleX    = (double)bounds.width  / (double)super.beforeFrame.width;
        }

        @Override
        protected void renderBackground(MatrixStack matrices, boolean dark, float alpha) {
            super.setupRenderer(dark, alpha);

            double height = (double)this.maxHeight - (
                (System.currentTimeMillis() / (animationDuration / this.maxHeight) % (double)this.maxHeight)
            );

            drawTexture(
                matrices, 
                this.bounds.x, this.bounds.y,
                this.bounds.width, this.bounds.height,
                this.beforeFrame.x, this.beforeFrame.y,
                this.beforeFrame.width, this.beforeFrame.height,
                ModReiElement.TEXTURE_SIZE[0], ModReiElement.TEXTURE_SIZE[1]
            );

            drawTexture(
                matrices, 
                this.bounds.x, this.bounds.y + this.bounds.height - (int)Math.ceil(height * this.scaleY),
                this.bounds.width, (int)Math.ceil(height * this.scaleY),
                this.afterFrame.x, this.afterFrame.y + this.afterFrame.height - (int)Math.ceil(height),
                this.afterFrame.width, (int)Math.ceil(height),
                ModReiElement.TEXTURE_SIZE[0], ModReiElement.TEXTURE_SIZE[1]
            );
        }

        public WidgetWithBounds withTooltip() {
            return Widgets.withTooltip(
                this, 
                Text.of(this.energy + " RF")
            );
        }
    }
}
