package dev.sammy99jsp.fabric.client.gui;

import dev.sammy99jsp.fabric.Mod;
import dev.sammy99jsp.fabric.client.gui.machine.handler.MachinePulverizerScreenHandler;
import dev.sammy99jsp.fabric.client.gui.machine.screen.MachinePulverizerScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.registry.Registry;
import  net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;;

public class ScreenHandlers {
    public static ScreenHandlerType<MachinePulverizerScreenHandler> PULVERIZER = register(
        "machine_pulverizer", MachinePulverizerScreenHandler::new, MachinePulverizerScreen::new
        );

    private static <S extends ScreenHandler, T extends Screen & ScreenHandlerProvider<S>> ScreenHandlerType<S> register(
        String name, ScreenHandlerType.Factory<S> factory, HandledScreens.Provider<S, T> provider
        ) {
		ScreenHandlerType<S> type = new ScreenHandlerType<S>((syncId, inventory) -> factory.create(syncId, inventory));
        ScreenHandlerType<S> tmp = Registry.register(Registry.SCREEN_HANDLER, new Identifier(Mod.MOD_ID, name), type);
        HandledScreens.register(tmp, provider);
    
        return tmp;
    }


    public static void registerScreenHandlers() {
        Mod.LOGGER.info("Registering Screens!"); 
    }
}
