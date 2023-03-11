package dev.sammy99jsp.fabric.item;

import dev.sammy99jsp.fabric.Mod;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items  {
    private static Item register(String name, Item item) {
        return Registry.register(
            Registry.ITEM, new Identifier(Mod.MOD_ID, name),
            item
        );
    }

    

    public static void registerItems() {
        // Do nothing, but load modded stuff.
    }

}
