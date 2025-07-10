package pelemenguin.mantlejs.content.book.util;

import slimeknights.mantle.client.book.data.element.TextData;

public interface TextDataInterface {
    
    public static final TextData LINEBREAK = new TextData("\n");

    public static TextData literal(String string) {
        return new TextData(string);
    }

}
