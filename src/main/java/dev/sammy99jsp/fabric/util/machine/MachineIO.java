package dev.sammy99jsp.fabric.util.machine;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import dev.sammy99jsp.fabric.util.inventory.ModInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class MachineIO implements ModInventory {
    public static abstract class IO<M extends Medium> {
        public abstract M get();

        public <O extends Medium> boolean contains(Class<O> clazz) {
            return this.get().getClass().equals(clazz);
        }
    }

    public static class Input<M extends Medium> extends IO<M>{
        private M inner;
        private String tag;

        private Input(M inner) {
            this.inner = inner;
        }

        @Override
        public M get() {
            return this.inner;
        }

        public static <M extends Medium> Input<M> of(M inner) {
            return new Input<M>(inner);
        }

        public Input<M> tag(String tag) {
            this.tag = tag;
            return this;
        }
    } 

    public static class Output<M extends Medium> extends IO<M> {
        private M inner;private String tag;
        

        private Output(M inner) {
            this.inner = inner;
        }

        @Override
        public M get() {
            return this.inner;
        }

        public static <M extends Medium> Output<M> of(M inner) {
            return new Output<M>(inner);
        }

        public Output<M> tag(String tag) {
            this.tag = tag;
            return this;
        }
    } 

    public static abstract class Medium {

        public static Optional<Item> isItem(Medium m) {
            return  (m instanceof Item) ? Optional.of((Item)m) : Optional.empty();
        }

        public static class Item extends Medium {
            private int slotIndex;

            public <I extends Inventory> ItemStack get(I inventory) {
                return inventory.getStack(this.slotIndex);
            }

            public <I extends Inventory> ItemStack replace(I inv, ItemStack stack) {
                ItemStack prev = inv.removeStack(this.slotIndex);
                inv.setStack(this.slotIndex, stack);
                return prev;
            } 
 
            public int slot() {
                return this.slotIndex;
            }
        }

        public static class Fluid extends Medium {
        }

        public static class Energy extends Medium {
        }
    }

    private DefaultedList<ItemStack> itemInventory =  DefaultedList.of();
    private ArrayList<IO<? extends Medium>> contents = new ArrayList<>();

    public MachineIO() {}

    public <M extends Medium, I extends IO<M>> MachineIO add(I io) {
        if (io.get() instanceof Medium.Item) {
            Medium.Item itemInput = (Medium.Item)io.get();

            long index = this.contents.stream()
                .filter((in) -> in.get() instanceof Medium.Item)
                .count();

            itemInput.slotIndex = (int)index;
        }
        contents.add(io);
        itemInventory.add(ItemStack.EMPTY);
        return this;
    }

    public Stream<Input<? extends Medium>> inputs() {
        return this.contents.stream()
            .filter((i) -> i instanceof Input<?>)
            .map((i) -> (Input<? extends Medium>)(i));
    }

    public Stream<Output<? extends Medium>> outputs() {
        return this.contents.stream()
            .filter((i) -> i instanceof Output<?>)
            .map((i) -> (Output<? extends Medium>)(i));
    }

    /// Inventory Methods.

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.itemInventory;
    }
}
