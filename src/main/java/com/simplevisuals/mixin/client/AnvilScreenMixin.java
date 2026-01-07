package com.simplevisuals.mixin.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreen.class)
public abstract class AnvilScreenMixin extends HandledScreen<AnvilScreenHandler> {

    public AnvilScreenMixin(AnvilScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    //Todo: not showing
    @Inject(method = "drawForeground", at = @At("TAIL"))
    private void renderAnvilUses(DrawContext context, int mouseX, int mouseY, CallbackInfo ci) {
        ItemStack stack = this.handler.getSlot(0).getStack();

        if (!stack.isEmpty()) {
            int repairCost = stack.getOrDefault(DataComponentTypes.REPAIR_COST, 0);

            // Berechnung: uses = log2(repairCost + 1)
            int uses = 0;
            if (repairCost > 0) {
                uses = (int) (Math.log(repairCost + 1) / Math.log(2));
            }

            if (uses > 0) {
                int color = 0x80FF20; // Hellgrün
                
                // HIER: Nutzung des Übersetzungsschlüssels mit Platzhalter (%s wird durch 'uses' ersetzt)
                Text text = Text.translatable("simplevisuals.screen.anvil_uses", uses);
                
                context.drawText(this.textRenderer, text, 60, 70, color, true);
            }
        }
    }
}