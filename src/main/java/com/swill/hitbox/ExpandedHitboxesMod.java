package com.swill.hitbox;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpandedHitboxesMod implements ModInitializer {
    public static final String MOD_ID = "expandedhitboxes";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[SWILL] Expanded Hitboxes 1.20.4 loaded.");
    }
}
