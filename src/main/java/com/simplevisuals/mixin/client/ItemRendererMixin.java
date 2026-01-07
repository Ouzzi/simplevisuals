package com.simplevisuals.mixin.client;

import com.simplevisuals.client.cit.CitRegistry;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    // Wir fangen den Moment ab, in dem das Model benutzt wird ("update" wird aufgerufen).
    // Das umgeht das Problem, dass das Model vielleicht nie in einer Variable gespeichert wird.
    @Redirect(
            method = "update",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/item/model/ItemModel;update(Lnet/minecraft/client/render/item/ItemRenderState;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/BakedModelManager;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/world/ClientWorld;Lnet/minecraft/entity/LivingEntity;I)V"
            )
    )
    private void redirectUpdate(ItemModel instance, ItemRenderState state, ItemStack stack, BakedModelManager manager, ItemDisplayContext context, ClientWorld world, LivingEntity entity, int seed) {
        // 'instance' ist das originale Vanilla Model

        // 1. Prüfen ob wir ein Custom Model haben
        ItemModel customModel = CitRegistry.getModel(stack);

        if (customModel != null) {
            // 2. Custom Model updaten (benutzen)
            customModel.update(state, stack, manager, context, world, entity, seed);
        } else {
            // 3. Fallback auf Original
            instance.update(state, stack, manager, context, world, entity, seed);
        }
    }
}