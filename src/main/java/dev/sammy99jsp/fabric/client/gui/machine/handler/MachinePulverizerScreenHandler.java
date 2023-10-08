package dev.sammy99jsp.fabric.client.gui.machine.handler;

import java.util.Optional;
import java.util.function.Function;

import dev.sammy99jsp.fabric.block.tile.MachinePulverizerTile;
import dev.sammy99jsp.fabric.client.gui.ScreenHandlers;
import dev.sammy99jsp.fabric.util.machine.MachineIO;
import dev.sammy99jsp.fabric.util.machine.MachineIO.Medium;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

public class MachinePulverizerScreenHandler extends MachineScreenHandler {
    private PlayerInventory playerInv = null;

    public MachinePulverizerScreenHandler(ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }
    

    public MachinePulverizerScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(MachinePulverizerTile.ITEM_SLOTS));
    }

    public MachinePulverizerScreenHandler(int syncId, PlayerInventory playerInv, Inventory inv) {
        super(ScreenHandlers.PULVERIZER, syncId);
        checkSize(inv, MachinePulverizerTile.ITEM_SLOTS);
        super.inv = inv;
        this.playerInv = playerInv;

        this.addSlot(new Slot(inv, 0, 53, 26));
        this.addSlot(new Slot(inv, 1, 116, 26));
        this.addSlot(new Slot(inv, 2, 116, 53));
        this.addSlot(new Slot(inv, 3, 8, 53));
    }

    public Optional<Medium.Item> inputItem(int index) {
        if(index != 0)
            return Optional.empty();

        if(this.inv instanceof MachineIO) {
            MachineIO io = (MachineIO)this.inv;
            return io.inputs()
                .filter(a -> Medium.isItem(a.get()).isPresent())
                    .skip(index)
                    .map(a -> (Medium.Item)a.get())
                    .findFirst();
            }
        return Optional.empty();
    }


    



    
}
