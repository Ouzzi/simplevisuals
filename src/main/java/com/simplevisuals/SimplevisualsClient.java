package com.simplevisuals;

import com.simplevisuals.client.gui.BiomeNotificationHud;
import com.simplevisuals.client.gui.ElytraPitchHud;
import com.simplevisuals.client.gui.PickupNotifierHud;
import com.simplevisuals.client.gui.SpeedLinesOverlay;
import com.simplevisuals.client.gui.tooltip.MapTooltipComponent;
import com.simplevisuals.client.gui.tooltip.MapTooltipData;
import com.simplevisuals.client.renderer.DamageIndicatorRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents;

public class SimplevisualsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new ElytraPitchHud());
        HudRenderCallback.EVENT.register(new SpeedLinesOverlay());

        BiomeNotificationHud biomeHud = new BiomeNotificationHud();
        HudRenderCallback.EVENT.register(biomeHud);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!client.isPaused()) {
                PickupNotifierHud.tick();
                biomeHud.tick();
            }
        });

        WorldRenderEvents.END_MAIN.register(DamageIndicatorRenderer::render);


        TooltipComponentCallback.EVENT.register(data -> {
            if (Simplevisuals.getConfig().visuals.enableMapTooltips && data instanceof MapTooltipData mapData) {
                return new MapTooltipComponent(mapData);
            }
            return null;
        });

        com.simplevisuals.client.cit.CitRegistry.register(
                net.minecraft.item.Items.STICK,
                "Test",
                net.minecraft.util.Identifier.ofVanilla("diamond")
        );
    }
}