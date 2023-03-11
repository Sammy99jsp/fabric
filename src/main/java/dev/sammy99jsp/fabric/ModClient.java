package dev.sammy99jsp.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import dev.sammy99jsp.fabric.client.gui.ScreenHandlers;
import net.fabricmc.api.ClientModInitializer;

@Environment(EnvType.CLIENT)
public class ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenHandlers.registerScreenHandlers();
    }
    
}
