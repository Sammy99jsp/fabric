package dev.sammy99jsp.fabric.block;

import dev.sammy99jsp.fabric.Mod;
import dev.sammy99jsp.fabric.block.tile.MachinePulverizerTile;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder.*;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockEntities {
    private static <E extends BlockEntity> BlockEntityType<E> register(String name, Factory<E> fact, Block block) {
        return Registry.register(
            Registry.BLOCK_ENTITY_TYPE, new Identifier(Mod.MOD_ID, name), 
            FabricBlockEntityTypeBuilder.create(
                fact,
                block
            ).build(null)
        );
    }

    public static final BlockEntityType<MachinePulverizerTile> PULVERIZER = register(
        "machine_pulverizer_entity", MachinePulverizerTile::new, Blocks.PULVERIZER
    );

    public static void registerEntites() {
        // Java, please initialize these!
    }
}
