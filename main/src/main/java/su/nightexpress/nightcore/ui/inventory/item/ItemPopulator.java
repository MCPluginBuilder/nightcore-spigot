package su.nightexpress.nightcore.ui.inventory.item;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.jspecify.annotations.NullMarked;

import io.th0rgal.oraxen.shaded.jetbrains.annotations.Nullable;
import su.nightexpress.nightcore.ui.inventory.action.MenuItemAction;
import su.nightexpress.nightcore.ui.inventory.item.populator.SlotProvider;
import su.nightexpress.nightcore.ui.inventory.viewer.MenuViewer;
import su.nightexpress.nightcore.ui.inventory.viewer.ViewerContext;
import su.nightexpress.nightcore.util.bukkit.NightItem;

@NullMarked
public record ItemPopulator<T>(SlotProvider slotProvider,
                               BiFunction<ViewerContext, T, @Nullable NightItem> itemProvider,
                               Function<T, MenuItemAction> actionProvider) {

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static <T> Builder<T> builder(Class<T> type) {
        return new Builder<>();
    }

    public void populateTo(ViewerContext context, Collection<T> items,
                           List<MenuItem> targetItems) {
        MenuViewer viewer = context.getViewer();

        int itemsSize = items.size();
        int[] slots = this.slotProvider.getSlots(itemsSize);
        int limit = slots.length;
        int pages = (int) Math.ceil(itemsSize / (double) limit);

        viewer.setTotalPages(pages);
        viewer.setCurrentPage(Math.min(viewer.getCurrentPage(), viewer.getTotalPages()));

        int skip = (viewer.getCurrentPage() - 1) * limit;

        List<T> list = items.stream().skip(skip).limit(limit).toList();

        int slotCount = 0;
        for (T object : list) {
            NightItem item = this.itemProvider.apply(context, object);
            if (item == null) continue;

            MenuItem menuItem = MenuItem.custom()
                .defaultState(item, this.actionProvider.apply(object))
                .slots(slots[slotCount])
                .build();

            targetItems.add(menuItem);
            slotCount++;
        }
    }

    public static class Builder<T> {

        private SlotProvider                            slotProvider;
        private BiFunction<ViewerContext, T, NightItem> itemProvider;
        private Function<T, MenuItemAction>             actionProvider;

        Builder() {

        }

        public ItemPopulator<T> build() {
            Objects.requireNonNull(this.slotProvider, "No slots defined");
            Objects.requireNonNull(this.itemProvider, "No item provider defined");
            Objects.requireNonNull(this.actionProvider, "No action provider defined");

            return new ItemPopulator<>(this.slotProvider, this.itemProvider, this.actionProvider);
        }

        public Builder<T> slots(int... slots) {
            return this.slots(size -> slots);
        }

        public Builder<T> slots(SlotProvider slotProvider) {
            this.slotProvider = slotProvider;
            return this;
        }

        public Builder<T> itemProvider(BiFunction<ViewerContext, T, NightItem> itemProvider) {
            this.itemProvider = itemProvider;
            return this;
        }

        public Builder<T> actionProvider(Function<T, MenuItemAction> actionProvider) {
            this.actionProvider = actionProvider;
            return this;
        }
    }
}
