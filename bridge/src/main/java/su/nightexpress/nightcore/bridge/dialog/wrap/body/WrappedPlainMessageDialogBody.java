package su.nightexpress.nightcore.bridge.dialog.wrap.body;

import java.util.function.UnaryOperator;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import su.nightexpress.nightcore.bridge.dialog.DialogDefaults;
import su.nightexpress.nightcore.bridge.dialog.adapter.DialogBodyAdapter;
import su.nightexpress.nightcore.bridge.placeholder.PlaceholderReplacer;
import su.nightexpress.nightcore.util.text.night.wrapper.TagWrappers;

@NullMarked
public record WrappedPlainMessageDialogBody(String contents, int width) implements WrappedDialogBody {

    public WrappedPlainMessageDialogBody(String contents) {
        this(contents, DialogDefaults.DEFAULT_PLAIN_BODY_WIDTH);
    }

    public WrappedPlainMessageDialogBody(String contents, int width) {
        this.contents = contents;
        this.width = DialogDefaults.clampWidth(width);
    }

    @Override
    public <D> D adapt(DialogBodyAdapter<D> adapter) {
        return adapter.adaptBody(this);
    }

    @Override
    @Deprecated
    public WrappedPlainMessageDialogBody replace(UnaryOperator<String> operator) {
        return new WrappedPlainMessageDialogBody(operator.apply(this.contents), this.width);
    }

    public static class Builder {

        private String contents = "";
        private int    width    = 400;

        @Nullable
        private PlaceholderReplacer replacer;

        public WrappedPlainMessageDialogBody build() {
            PlaceholderReplacer replacer = this.replacer;
            String content = this.contents;

            if (replacer != null) {
                content = replacer.apply(content);
            }

            return new WrappedPlainMessageDialogBody(content, this.width);
        }

        public Builder contents(String... contents) {
            this.contents = String.join(TagWrappers.BR, contents);
            return this;
        }

        public Builder width(int width) {
            this.width = Math.clamp(width, DialogDefaults.MIN_WIDTH, DialogDefaults.MAX_WIDTH);
            return this;
        }

        public Builder placeholders(PlaceholderReplacer replacer) {
            this.replacer = replacer;
            return this;
        }
    }
}
