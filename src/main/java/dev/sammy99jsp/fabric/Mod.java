package dev.sammy99jsp.fabric;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.sammy99jsp.fabric.block.BlockEntities;
import dev.sammy99jsp.fabric.block.Blocks;
import dev.sammy99jsp.fabric.item.Items;


public class Mod implements ModInitializer {
	public static final String MOD_ID = "sammy99jsp";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID); 

	public static Identifier identifier(String name) {
		return new Identifier(MOD_ID, name);
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Registering...");
		Blocks.registerBlocks();
		Items.registerItems();
		BlockEntities.registerEntites();
		LOGGER.info("Done Registering!");
		
		LOGGER.info("Hello Fabric world!");
	}
}
