package dev.sammy99jsp.fabric.integrations.jade.machine;

import dev.sammy99jsp.fabric.Mod;
import dev.sammy99jsp.fabric.block.tile.MachinePulverizerTile;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;

public enum MachinePulveriserJade implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor block, IPluginConfig config) {
        MachinePulverizerTile ent = (MachinePulverizerTile)block.getBlockEntity();

        IElementHelper elements = tooltip.getElementHelper();
        IElement icon = elements.item(new ItemStack(Items.REDSTONE), 1.0f);
        tooltip.add(icon);
        
        tooltip.append(Text.of("Power: " + ent.getPower()));
    }

    @Override
    public Identifier getUid() {
        return Mod.identifier("machine_pulverizer");
    }
    
}
