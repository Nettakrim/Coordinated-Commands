package com.nettakrim.coordinated_commands;

import net.fabricmc.api.ClientModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoordinatedCommandsClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("coordinated_commands");

	@Override
	public void onInitializeClient() {
		LOGGER.info("Hello Fabric world!");
	}
}