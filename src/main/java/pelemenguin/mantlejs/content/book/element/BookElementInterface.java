package pelemenguin.mantlejs.content.book.element;

import slimeknights.mantle.client.book.data.element.TextData;
import slimeknights.mantle.client.screen.book.element.TextElement;

public interface BookElementInterface {
    
    public static TextElement Text(int x, int y, int width, int height, TextData... text) {
        return new TextElement(x, y, width, height, text);
    }

    // TODO: More Mantle Elements

}
