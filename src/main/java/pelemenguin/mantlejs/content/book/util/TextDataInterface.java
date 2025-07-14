package pelemenguin.mantlejs.content.book.util;

import java.util.ArrayList;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import slimeknights.mantle.client.book.data.element.TextData;

public interface TextDataInterface {
    
    public static final TextData LINEBREAK = new TextData("\n");

    public static TextData literal(String string) {
        return new TextData(string);
    }

    private static String getText(MutableComponent component) {
        String result;
        ComponentContents contents = component.getContents();
        if (contents instanceof LiteralContents) {
            result = ((LiteralContents)contents).text();
        } else if (contents instanceof TranslatableContents) {
            result = Language.getInstance().getOrDefault((((TranslatableContents)contents).getKey()));
        } else { // TODO: Other components
            result = contents.toString();
        }
        return result;
    }

    private static TextData singleComponentToTextData(MutableComponent component) {
        Style style = component.getStyle();
        TextData result = literal(getText(component));
        result.bold = style.isBold();
        result.italic = style.isItalic();
        result.underlined = style.isUnderlined();
        result.strikethrough = style.isStrikethrough();
        result.obfuscated = style.isObfuscated();

        result.useOldColor = false;
        TextColor color = style.getColor();
        result.rgbColor = color == null ? 0 : color.getValue();

        // TODO: tooltip

        return result;
    }

    private static ArrayList<TextData> fromComponentToArrayList(MutableComponent component) {
        ArrayList<TextData> result = new ArrayList<>();
        result.add(singleComponentToTextData(component));
        for (Component c : component.getSiblings()) {
            result.addAll(fromComponentToArrayList(c.copy()));
        }
        
        return result;
    }

    public static TextData[] fromComponent(MutableComponent component) {
        return fromComponentToArrayList(component).toArray(new TextData[0]);
    }

}
