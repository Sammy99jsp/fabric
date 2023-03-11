package dev.sammy99jsp.fabric.block;

import dev.sammy99jsp.fabric.Mod;
import dev.sammy99jsp.fabric.block.machine.MachinePulverizer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {


    public static final Block PULVERIZER = register(
        "machine_pulverizer", 
        new MachinePulverizer(FabricBlockSettings.copy(net.minecraft.block.Blocks.POLISHED_ANDESITE))
    );

    private static Block register(String name, Block block) {

        // Register block item too!
        Registry.register(
            Registry.ITEM, new Identifier(Mod.MOD_ID, name),
            new BlockItem(block, new Settings())
        );

        return Registry.register(
            Registry.BLOCK, new Identifier(Mod.MOD_ID, name),
            block
        );
    }

    public static void registerBlocks() {
        // Just so that Java actually uses this.
    }
    
}
