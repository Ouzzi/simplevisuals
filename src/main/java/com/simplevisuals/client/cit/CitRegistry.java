package com.simplevisuals.client.cit;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class CitRegistry {

    private static final Map<Item, Map<String, Identifier>> ENTRIES = new HashMap<>();

    public static void register(Item item, String name, Identifier modelId) {
        ENTRIES.computeIfAbsent(item, k -> new HashMap<>()).put(name, modelId);
    }

    public static ItemModel getModel(ItemStack stack) {
        if (stack.isEmpty()) return null;

        // Prüfung auf Custom Name Component (1.21.4)
        if (!stack.contains(DataComponentTypes.CUSTOM_NAME)) return null;

        String name = stack.getName().getString();

        Map<String, Identifier> names = ENTRIES.get(stack.getItem());
        if (names == null) return null;

        Identifier modelId = names.get(name);
        if (modelId != null) {
            BakedModelManager modelManager = MinecraftClient.getInstance().getBakedModelManager();
            // In 1.21.4: getItemModel(Identifier)
            return modelManager.getItemModel(modelId);
        }

        return null;
    }
}