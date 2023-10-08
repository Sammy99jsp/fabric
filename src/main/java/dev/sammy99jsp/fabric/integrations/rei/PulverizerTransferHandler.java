package dev.sammy99jsp.fabric.integrations.rei;

import java.util.List;

import dev.sammy99jsp.fabric.Mod;
import dev.sammy99jsp.fabric.client.gui.machine.handler.MachinePulverizerScreenHandler;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandler;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;

public class PulverizerTransferHandler implements TransferHandler {


    @Override
    public Result handle(Context context) {
        List<EntryIngredient> ingredients = context.getDisplay().getInputEntries();
        if( !(context.getMenu() instanceof MachinePulverizerScreenHandler) ||
            !context.getDisplay().getCategoryIdentifier().equals(PulverizerCategory.DISPLAY)
        ) {
            return Result.createNotApplicable();    
        }

        boolean crafting = context.isActuallyCrafting();

        
        if(!crafting) {
            return Result.createSuccessful();
        }
        
        MachinePulverizerScreenHandler h = (MachinePulverizerScreenHandler)context.getMenu();

        ItemStack item = (ItemStack)ingredients.get(0).get(0).getValue();

    


        Mod.LOGGER.info("Yo, got: " + h);

        h.slots.get(0).setStack(new ItemStack(Items.ACACIA_BOAT, 2));
        return Result.createSuccessful()
            .blocksFurtherHandling(true);

        // screen.slots.get(0)
        //     .setStack(new ItemStack(context.));

            
        
    }
    
}
