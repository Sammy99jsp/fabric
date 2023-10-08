package dev.sammy99jsp.fabric.integrations.rei.gui.element;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import me.shedaniel.math.Rectangle;
import net.minecraft.client.util.math.MatrixStack;

public class ScaleIcons {
    private static List<List<Rectangle>> SCALES = IntStream.range(0, 12)
        .boxed()
        .map(i -> Arrays.asList(
            new Rectangle(224, i * 16, 16, 16),
            new Rectangle(240, i * 16, 16, 16)
        ))
        .toList();
    
    private static class ScaleIcon extends ModReiElement {
        private final double scaleX; 
        private final double scaleY;

        protected ScaleIcon(Rectangle bounds, Rectangle before, Rectangle after, double animationDuration) {
            super(bounds, before, after, animationDuration);

            this.scaleX = (double)bounds.width  / (double)before.width ;
            this.scaleY = (double)bounds.height / (double)before.height;
        }

        @Override
        protected void renderBackground(MatrixStack matrices, boolean dark, float alpha) {
            super.setupRenderer(dark, alpha);
            
            double height = this.beforeFrame.height - Math.floor(
                (System.currentTimeMillis() / (animationDuration / this.beforeFrame.height) % (double)this.beforeFrame.height)
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
                this.bounds.x, this.bounds.y + (int)((this.afterFrame.height - height) * scaleY),
                this.bounds.width, (int)height,
                this.afterFrame.x, this.afterFrame.y + this.afterFrame.height - (int)height,
                this.afterFrame.width, (int)height,
                ModReiElement.TEXTURE_SIZE[0], ModReiElement.TEXTURE_SIZE[1]
            );
        }
    }

    public static void load() {}

    public static final class Alchemy extends ScaleIcon {
        private Alchemy(Rectangle bounds, double animationDuration) {
            super(bounds, SCALES.get(0).get(0), SCALES.get(0).get(1), animationDuration);
        }

        public static Alchemy of(Rectangle r, double anim) {
            return new ScaleIcons.Alchemy(r, anim);
        }
    }
    public static final class Bubble extends ScaleIcon {
        private Bubble(Rectangle bounds, double animationDuration) {
            super(bounds, SCALES.get(1).get(0), SCALES.get(1).get(1), animationDuration);
        }

        public static Bubble of(Rectangle r, double anim) {
            return new ScaleIcons.Bubble(r, anim);
        }
    }
    public static final class Compact extends ScaleIcon {
        private Compact(Rectangle bounds, double animationDuration) {
            super(bounds, SCALES.get(2).get(0), SCALES.get(2).get(1), animationDuration);
        }

        public static Compact of(Rectangle r, double anim) {
            return new ScaleIcons.Compact(r, anim);
        }
    }
    public static final class Crush extends ScaleIcon {
        private Crush(Rectangle bounds, double animationDuration) {
            super(bounds, SCALES.get(3).get(0), SCALES.get(3).get(1), animationDuration);
        }

        public static Crush of(Rectangle r, double anim) {
            return new ScaleIcons.Crush(r, anim);
        }
    }
    public static final class Flame extends ScaleIcon {
        private Flame(Rectangle bounds, double animationDuration) {
            super(bounds, SCALES.get(4).get(0), SCALES.get(4).get(1), animationDuration);
        }

        public static Flame of(Rectangle r, double anim) {
            return new ScaleIcons.Flame(r, anim);
        }
    }
    public static final class FlameGreen extends ScaleIcon {
        private FlameGreen(Rectangle bounds, double animationDuration) {
            super(bounds, SCALES.get(5).get(0), SCALES.get(5).get(1), animationDuration);
        }
        
        public static FlameGreen of(Rectangle r, double anim) {
            return new ScaleIcons.FlameGreen(r, anim);
        }
    }
    public static final class Flux extends ScaleIcon {
        private Flux(Rectangle bounds, double animationDuration) {
            super(bounds, SCALES.get(6).get(0), SCALES.get(6).get(1), animationDuration);
        }

        public static Flux of(Rectangle r, double anim) {
            return new ScaleIcons.Flux(r, anim);
        }
    }
    public static final class Saw extends ScaleIcon {
        private Saw(Rectangle bounds, double animationDuration) {
            super(bounds, SCALES.get(7).get(0), SCALES.get(7).get(1), animationDuration);
        }

        public static Saw of(Rectangle r, double anim) {
            return new ScaleIcons.Saw(r, anim);
        }
    }
    public static final class Spin extends ScaleIcon {
        private Spin(Rectangle bounds, double animationDuration) {
            super(bounds, SCALES.get(8).get(0), SCALES.get(8).get(1), animationDuration);
        }

        public static Spin of(Rectangle r, double anim) {
            return new ScaleIcons.Spin(r, anim);
        }
        
    }
    public static final class Sun extends ScaleIcon {
        private Sun(Rectangle bounds, double animationDuration) {
            super(bounds, SCALES.get(9).get(0), SCALES.get(9).get(1), animationDuration);
        }

        public static Sun of(Rectangle r, double anim) {
            return new ScaleIcons.Sun(r, anim);
        }
    }
    public static final class Snowflake extends ScaleIcon {
        private Snowflake(Rectangle bounds, double animationDuration) {
            super(bounds, SCALES.get(10).get(0), SCALES.get(10).get(1), animationDuration);
        }
        public static Snowflake of(Rectangle r, double anim) {
            return new ScaleIcons.Snowflake(r, anim);
        }
    }
    public static final class Book extends ScaleIcon {
        private Book(Rectangle bounds, double animationDuration) {
            super(bounds, SCALES.get(11).get(0), SCALES.get(11).get(1), animationDuration);
        }
        public static Book of(Rectangle r, double anim) {
            return new ScaleIcons.Book(r, anim);
        }
    }
}
