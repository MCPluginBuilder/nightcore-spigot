package su.nightexpress.nightcore.bridge.spigot.entity;

import org.bukkit.entity.TextDisplay;
import org.jspecify.annotations.NullMarked;

import su.nightexpress.nightcore.bridge.entity.WrappedTextDisplay;
import su.nightexpress.nightcore.util.bridge.wrapper.NightComponent;

@NullMarked
public class SpigotTextDisplay implements WrappedTextDisplay {

    private final TextDisplay bukkit;

    public SpigotTextDisplay(TextDisplay bukkit) {
        this.bukkit = bukkit;
    }

    @Override
    public TextDisplay getBukkit() {
        return this.bukkit;
    }

    @Override
    public String getText() {
        return this.bukkit.getText();
    }

    @Override
    public void setText(String text) {
        this.bukkit.setText(text);
    }

    @Override
    public void setText(NightComponent component) {
        this.bukkit.setText(component.toLegacy());
    }
}
