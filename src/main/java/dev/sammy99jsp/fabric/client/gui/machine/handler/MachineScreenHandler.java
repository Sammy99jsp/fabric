package dev.sammy99jsp.fabric.client.gui.machine.handler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public abstract class MachineScreenHandler extends ScreenHandler {
    protected Inventory inv;
    
    protected MachineScreenHandler(ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return inv.canPlayerUse(player);
    }

    // TODO: Transfer Slots
    
}
