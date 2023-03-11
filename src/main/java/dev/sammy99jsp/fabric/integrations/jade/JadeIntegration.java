package dev.sammy99jsp.fabric.integrations.jade;

import dev.sammy99jsp.fabric.block.machine.MachinePulverizer;
import dev.sammy99jsp.fabric.integrations.jade.machine.MachinePulveriserJade;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadeIntegration implements IWailaPlugin {

    @Override
    public void register(IWailaCommonRegistration registration) {
        // TODO Auto-generated method stub
        // registration.registerBlockDataProvider(null, null);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        // TODO Auto-generated method stub
        registration.registerBlockComponent(MachinePulveriserJade.INSTANCE, MachinePulverizer.class);
    }
    
}