package su.nightexpress.nightcore.ui.dialog.build;

import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NullMarked;

import su.nightexpress.nightcore.bridge.dialog.wrap.body.WrappedItemDialogBody;
import su.nightexpress.nightcore.bridge.dialog.wrap.body.WrappedPlainMessageDialogBody;
import su.nightexpress.nightcore.locale.entry.DialogElementLocale;
import su.nightexpress.nightcore.locale.entry.TextLocale;
import su.nightexpress.nightcore.util.bukkit.NightItem;

@NullMarked
public final class DialogBodies {

    private DialogBodies() {
    }

    public static WrappedItemDialogBody.Builder item(NightItem item) {
        return new WrappedItemDialogBody.Builder(item.getItemStack());
    }

    public static WrappedItemDialogBody.Builder item(NightItem item,
                                                     DialogElementLocale description) {
        return new WrappedItemDialogBody.Builder(item.getItemStack()).description(plainMessage(description));
    }

    public static WrappedItemDialogBody.Builder item(ItemStack itemStack) {
        return new WrappedItemDialogBody.Builder(itemStack);
    }

    public static WrappedItemDialogBody.Builder item(ItemStack itemStack,
                                                     DialogElementLocale description) {
        return new WrappedItemDialogBody.Builder(itemStack).description(plainMessage(description));
    }

    public static WrappedPlainMessageDialogBody.Builder plain() {
        return new WrappedPlainMessageDialogBody.Builder();
    }

    public static WrappedPlainMessageDialogBody.Builder plain(String... contents) {
        return plain().contents(contents);
    }

    public static WrappedPlainMessageDialogBody.Builder plain(TextLocale locale) {
        return plain().contents(locale.text());
    }

    public static WrappedPlainMessageDialogBody.Builder plain(DialogElementLocale locale) {
        return plain().contents(locale.contents());
    }

    @Deprecated
    public static WrappedPlainMessageDialogBody plainMessage(DialogElementLocale locale) {
        return new WrappedPlainMessageDialogBody(locale.contents(), locale.width());
    }

    public static WrappedPlainMessageDialogBody plainMessage(TextLocale locale) {
        return plainMessage(locale.text());
    }

    public static WrappedPlainMessageDialogBody plainMessage(String contents) {
        return new WrappedPlainMessageDialogBody(contents);
    }

    public static WrappedPlainMessageDialogBody plainMessage(TextLocale locale, int width) {
        return plainMessage(locale.text(), width);
    }

    public static WrappedPlainMessageDialogBody plainMessage(String contents, int width) {
        return new WrappedPlainMessageDialogBody(contents, width);
    }
}
