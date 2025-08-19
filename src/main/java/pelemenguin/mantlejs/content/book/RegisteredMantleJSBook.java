package pelemenguin.mantlejs.content.book;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.resources.ResourceLocation;
import slimeknights.mantle.client.book.BookLoader;
import slimeknights.mantle.client.book.data.BookData;

public interface RegisteredMantleJSBook {
    // List of all registered book from KubeJS
    public static final Map<ResourceLocation, BookData> MANTLEJS_BOOKS = new HashMap<>();

    public static BookData getBook(ResourceLocation bookLocation) {
        if (MANTLEJS_BOOKS.containsKey(bookLocation)) {
            return MANTLEJS_BOOKS.get(bookLocation);
        } else {
            return BookLoader.registerBook(bookLocation);
        }
    }
}
