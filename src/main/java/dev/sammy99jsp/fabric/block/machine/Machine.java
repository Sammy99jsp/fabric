package dev.sammy99jsp.fabric.block.machine;

import java.util.stream.Stream;

import org.jetbrains.annotations.NotNull;

import dev.sammy99jsp.fabric.util.machine.MachineIO;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public abstract class Machine extends BlockWithEntity {
    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");


    public Machine(Settings settings) {
        super(settings);
		setDefaultState(getDefaultState()
            .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
            .with(ACTIVE, false)
        );
    }

    /// Our API

    /**
     * Gets the BlockEntityData for this machine.
     * @return The constructor for the entity data (tile).
     */
    @NotNull
    protected abstract BlockEntityProvider tile();
    
    @NotNull
    protected abstract BlockEntityType<? extends BlockEntity> tileType();

    @NotNull
    protected abstract <E extends BlockEntity> BlockEntityTicker<? super E> ticker(); 

    /// Normal Minecraft Methods

    @Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(Properties.HORIZONTAL_FACING);
        builder.add(Machine.ACTIVE);
	}

    @Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return super.getPlacementState(ctx)
            .with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
	}

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
 
            if (screenHandlerFactory != null) {
                //With this call the server will request the client to open the appropriate Screenhandler
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return this.tile().createBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
            BlockEntityType<T> type) {
                return checkType(type, this.tileType(), this.ticker());
    }
}
