package dev.sammy99jsp.fabric.integrations.rei.gui.element;

import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.sammy99jsp.fabric.Mod;
import me.shedaniel.clothconfig2.api.animator.NumberAnimator;
import me.shedaniel.clothconfig2.api.animator.ValueAnimator;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.REIRuntime;
import me.shedaniel.rei.api.client.gui.widgets.WidgetWithBounds;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;

public abstract class ModReiElement extends WidgetWithBounds {
    private static final String TEXTURE = "textures/gui/rei_handler.png";
    private static final String TEXTURE_DARK = "textures/gui/rei_handler_dark.png";
    public static final int[] TEXTURE_SIZE = {256, 256};

    private double scaling = 1.0;

    protected final Rectangle bounds;

    protected final Rectangle beforeFrame;
    protected final Rectangle afterFrame;
    protected double animationDuration = 123.0;

    private final NumberAnimator<Float> darkBackgroundAlpha = ValueAnimator.ofFloat()
        .withConvention(() -> REIRuntime.getInstance().isDarkThemeEnabled() ? 1.0F : 0.0F, ValueAnimator.typicalTransitionTime())
        .asFloat();

    protected ModReiElement(final Rectangle bounds, final Rectangle before, final Rectangle after, double animationDuration) {
        this.bounds = bounds;
        this.beforeFrame = before;
        this.afterFrame = after;
        this.animationDuration = animationDuration;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.darkBackgroundAlpha.update(delta);
        renderBackground(matrices, false, 1.0F);
        if(darkBackgroundAlpha.value() > 0) {
            renderBackground(matrices, true, darkBackgroundAlpha.value());
        }
    }

    protected void setupRenderer(boolean dark, float alpha) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
        RenderSystem.setShaderTexture(0, Mod.identifier(dark ? TEXTURE_DARK : TEXTURE));
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(770, 771, 1, 0);
        RenderSystem.blendFunc(770, 771);
    }

    protected void renderBackground(MatrixStack matrices, boolean dark, float alpha) {
        this.setupRenderer(dark, alpha);

        // if(getAnimationDuration() > 0) {
            int height = this.beforeFrame.height - (int)Math.ceil(
                (System.currentTimeMillis() / (animationDuration / this.beforeFrame.height) % (double)this.beforeFrame.height)
            );
            drawTexture(
                matrices, 
                this.bounds.x, this.bounds.y,
                this.bounds.width, this.bounds.height - height,
                this.beforeFrame.x, this.beforeFrame.y,
                this.beforeFrame.width, this.beforeFrame.height - height,
                TEXTURE_SIZE[0], TEXTURE_SIZE[1]
            );

        this.setupRenderer(dark, alpha);
        
            drawTexture(
                matrices, 
                this.bounds.x, this.bounds.y,
                this.bounds.width, height,
                this.afterFrame.x, this.afterFrame.y + height,
                this.afterFrame.width, height,
                TEXTURE_SIZE[0], TEXTURE_SIZE[1]
            );
            // TODO: Other
        // } else {
        //     drawTexture(
        //         matrices, 
        //         this.bounds.x, this.bounds.y,
        //         this.bounds.width, this.bounds.height,
        //         this.beforeFrame.x, this.beforeFrame.y,
        //         this.beforeFrame.width, this.beforeFrame.height,
        //         TEXTURE_SIZE[0], TEXTURE_SIZE[1]
        //     );
        // }
    }

    private int getAnimationDuration() {
        return 0;
    }

    @Override
    public List<? extends net.minecraft.client.gui.Element> children() {
        return List.of();
    }

    @Override
    public Rectangle getBounds() {
        return this.bounds;
    }
}
