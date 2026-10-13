package com.jesusreiner365.energyshieldmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnergyShieldMod implements ModInitializer {
    public static final String MOD_ID = "energyshieldmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static KeyBinding toggleShieldKey;
    private static boolean shieldActive = false;
    private static int crouchCount = 0;
    private static int lastCrouchTime = 0;

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Energy Shield Mod");
        toggleShieldKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.energyshieldmod.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                "category.energyshieldmod.main"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleShieldKey.wasPressed()) {
                toggleShield();
            }

            if (client.player != null) {
                if (client.player.isSneaking()) {
                    if (client.player.age - lastCrouchTime > 10) {
                        crouchCount++;
                        lastCrouchTime = client.player.age;
                    }
                    if (crouchCount >= 2) {
                        toggleShield();
                        crouchCount = 0;
                    }
                } else {
                    crouchCount = 0;
                }
            }
        });
    }

    private void toggleShield() {
        shieldActive = !shieldActive;
        if (shieldActive) {
            LOGGER.info("Energy Shield Activated");
        } else {
            LOGGER.info("Energy Shield Deactivated");
        }
    }

    public static boolean isShieldActive() {
        return shieldActive;
    }
}