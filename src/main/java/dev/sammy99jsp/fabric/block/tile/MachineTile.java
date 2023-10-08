package dev.sammy99jsp.fabric.block.tile;

import java.util.stream.Stream;

import blue.endless.jankson.annotation.Nullable;
import dev.sammy99jsp.fabric.util.inventory.ModInventory;
import dev.sammy99jsp.fabric.util.machine.MachineIO;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

// TODO(@Sammy99jsp): Implement SidedInventory! 

public abstract class MachineTile extends BlockEntity implements ModInventory, NamedScreenHandlerFactory {
    protected final MachineIO io;

    public MachineTile(BlockEntityType<?> type, BlockPos pos, BlockState state, MachineIO io) {
        super(type, pos, state);
        this.io = io;
    }    

    public abstract void nbtRead(NbtCompound nbt);
    public abstract void nbtWrite(NbtCompound nbt);

    /// I/O Functions

    public <M extends MachineIO.Medium> Stream<MachineIO.Input<M>> inputsOfKind(Class<M> clazz) {
        return this.io.inputs()
            .filter(i -> i.contains(clazz))
            .map(i -> (MachineIO.Input<M>)i);
    }

    public <M extends MachineIO.Medium> Stream<MachineIO.Output<M>> outputsOfKind(Class<M> clazz) {
        return this.io.outputs()
            .filter(i -> i.contains(clazz))
            .map(i -> (MachineIO.Output<M>)i);
    }

    /// Minecraft Things

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.getItems());
        this.nbtRead(nbt);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        this.nbtWrite(nbt);
        Inventories.writeNbt(nbt, this.getItems());
        super.writeNbt(nbt);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    @Override
    @Nullable
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.io.getItems();
    }

    /// Screen Handler things
    
    @Override
    public Text getDisplayName() {
        return new TranslatableText(this.getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public abstract ScreenHandler createMenu(int syncId, PlayerInventory playerInv, PlayerEntity player);
}
