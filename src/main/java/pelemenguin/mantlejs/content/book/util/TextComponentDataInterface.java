package pelemenguin.mantlejs.content.book.util;

import net.minecraft.network.chat.Component;
import slimeknights.mantle.client.book.data.element.TextComponentData;

public interface TextComponentDataInterface {
    
    public static final TextComponentData LINEBREAK = new TextComponentData("\n");

    public static TextComponentData literal(String string) {
        return new TextComponentData(string);
    }

    public static TextComponentData of(Component component) {
        return new TextComponentData(component);
    }

}
