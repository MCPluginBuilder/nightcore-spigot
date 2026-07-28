package su.nightexpress.nightcore.bridge.dialog.wrap.body;

import java.util.function.UnaryOperator;

import org.jspecify.annotations.NullMarked;

import su.nightexpress.nightcore.bridge.dialog.adapter.DialogBodyAdapter;

@NullMarked
public interface WrappedDialogBody {

    <D> D adapt(DialogBodyAdapter<D> adapter);

    @Deprecated
    WrappedDialogBody replace(UnaryOperator<String> operator);
}
