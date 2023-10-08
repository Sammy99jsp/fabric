package dev.sammy99jsp.fabric.block.machine;

import org.jetbrains.annotations.NotNull;

import dev.sammy99jsp.fabric.block.BlockEntities;
import dev.sammy99jsp.fabric.block.tile.MachinePulverizerTile;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MachinePulverizer extends Machine {


    public MachinePulverizer(Settings settings) {
        super(settings);
    }

    @Override
    @NotNull
    protected BlockEntityProvider tile() { return MachinePulverizerTile::new; }

    @Override
    @NotNull
    protected BlockEntityType<? extends BlockEntity> tileType() { return BlockEntities.PULVERIZER; }

    @Override
    @NotNull
    protected <E extends BlockEntity> BlockEntityTicker<? super E> ticker() {
        return (w, p, s, b) -> MachinePulverizer.tick(w, p, s, (MachinePulverizerTile)b);
    }


    private static void tick(World world, BlockPos pos, BlockState state, MachinePulverizerTile be) {
        be.incrementPower();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        if(!world.isClient) {
            NamedScreenHandlerFactory screenHFactory = state.createScreenHandlerFactory(world, pos);

            if(screenHFactory != null)
                player.openHandledScreen(screenHFactory);

            }
        return ActionResult.SUCCESS;
    }
    
}
