package com.simplevisuals;

import com.simplevisuals.config.SimplevisualsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Simplevisuals implements ModInitializer {
	public static final String MOD_ID = "simplevisuals";
    private static SimplevisualsConfig CONFIG;

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
        LOGGER.info("Initializing Simplevisuals mod...");

        AutoConfig.register(SimplevisualsConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(SimplevisualsConfig.class).getConfig();
	}

    public static SimplevisualsConfig getConfig() { return CONFIG; }
}