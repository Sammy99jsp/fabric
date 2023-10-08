package dev.sammy99jsp.fabric.block.tile;

import dev.sammy99jsp.fabric.block.BlockEntities;
import dev.sammy99jsp.fabric.client.gui.machine.handler.MachinePulverizerScreenHandler;
import dev.sammy99jsp.fabric.util.machine.MachineIO;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class MachinePulverizerTile extends MachineTile {
    public static final int ITEM_SLOTS = 4; 

    private int power = 0;

    protected MachinePulverizerTile(BlockEntityType<?> type, BlockPos pos, BlockState state, MachineIO io) {
        super(type, pos, state, io);
    }

    public MachinePulverizerTile(BlockPos pos, BlockState state) {
        this(BlockEntities.PULVERIZER, pos, state, 
            new MachineIO()
                .add(MachineIO.Input.of(new MachineIO.Medium.Item()).tag("input"))     
                .add(MachineIO.Input.of(new MachineIO.Medium.Item()).tag("energy_slot"))                    
                .add(MachineIO.Input.of(new MachineIO.Medium.Energy()).tag("energy"))
                .add(MachineIO.Output.of(new MachineIO.Medium.Item()).tag("output1"))
                .add(MachineIO.Output.of(new MachineIO.Medium.Item()).tag("output2"))
        );
    }

    @Override
    public void nbtRead(NbtCompound nbt) {
        this.power = nbt.getInt("power");
    }

    @Override
    public void nbtWrite(NbtCompound nbt) {
        nbt.putInt("power", this.power);
    }

    public void incrementPower() {
        this.power++;
    }

    public int getPower() {
        return this.power;
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return super.io.getItems();
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInv, PlayerEntity player) {
        return new MachinePulverizerScreenHandler(syncId, playerInv, super.io);
    }
}
