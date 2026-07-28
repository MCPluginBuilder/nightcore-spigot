package su.nightexpress.nightcore.util.format.adaptive;

import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@FunctionalInterface
@NullMarked
public interface VariableReplacer<T> {

    String replace(@NonNull T source, @Nullable Player player);
}