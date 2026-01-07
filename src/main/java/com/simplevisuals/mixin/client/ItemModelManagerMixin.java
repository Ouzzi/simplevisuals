package com.simplevisuals.mixin.client;

import com.simplevisuals.client.cit.CitRegistry;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HeldItemContext; // Wichtig: Neuer Import
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemModelManager.class)
public class ItemModelManagerMixin {
    
    // Wir suchen die Methode in ItemModelManager, die das Model aktualisiert.
    // Da wir die exakte Signatur der Manager-Methode nicht kennen, nutzen wir nur den Methodennamen "update".
    // Sollte das fehlschlagen, entferne 'method = "update"' komplett, um global in der Klasse zu suchen.
    @Redirect(
            method = "update",
            at = @At(
                    value = "INVOKE",
                    // Exakte Signatur basierend auf deiner ItemModel.java:
                    target = "Lnet/minecraft/client/render/item/model/ItemModel;update(Lnet/minecraft/client/render/item/ItemRenderState;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/item/ItemModelManager;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/world/ClientWorld;Lnet/minecraft/util/HeldItemContext;I)V"
            )
    )
    private void redirectModelUpdate(ItemModel instance, ItemRenderState state, ItemStack stack, ItemModelManager resolver, ItemDisplayContext displayContext, ClientWorld world, HeldItemContext heldItemContext, int seed) {
        // 1. Prüfen, ob ein Custom Model (CIT) existiert
        ItemModel customModel = CitRegistry.getModel(stack);

        if (customModel != null) {
            // 2. Custom Model updaten
            customModel.update(state, stack, resolver, displayContext, world, heldItemContext, seed);
        } else {
            // 3. Fallback: Das originale Model updaten
            instance.update(state, stack, resolver, displayContext, world, heldItemContext, seed);
        }
    }
}