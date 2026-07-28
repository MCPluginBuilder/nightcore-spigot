package su.nightexpress.nightcore.integration.item.adapter.impl;

import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import net.momirealms.craftengine.bukkit.api.CraftEngineItems;
import net.momirealms.craftengine.bukkit.item.BukkitItemDefinition;
import net.momirealms.craftengine.core.util.Key;
import su.nightexpress.nightcore.integration.item.adapter.IdentifiableItemAdapter;
import su.nightexpress.nightcore.integration.item.data.ItemIdData;

@NullMarked
public class CraftEngineAdapter extends IdentifiableItemAdapter {

    public CraftEngineAdapter() {
        super("craftengine");
    }

    @Override
    @Nullable
    public String getItemId(ItemStack itemStack) {
        Key itemId = CraftEngineItems.getCustomItemId(itemStack);
        return itemId != null ? itemId.asString() : null;
    }

    @Override
    @Nullable
    public ItemStack createItem(String itemId) {
        BukkitItemDefinition customItem = CraftEngineItems.byId(Key.of(itemId));
        return customItem != null ? customItem.buildBukkitItem() : null;
    }

    @Override
    public boolean canHandle(ItemStack itemStack) {
        return CraftEngineItems.isCustomItem(itemStack);
    }

    @Override
    public boolean canHandle(ItemIdData data) {
        return CraftEngineItems.byId(Key.of(data.getItemId())) != null;
    }
}
