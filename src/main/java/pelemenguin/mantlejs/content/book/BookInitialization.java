package pelemenguin.mantlejs.content.book;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.resources.ResourceLocation;
import pelemenguin.mantlejs.MantleJS;
import slimeknights.mantle.client.book.BookLoader;
import slimeknights.mantle.client.book.data.BookData;

public class BookInitialization extends BookData {

    public static final Map<ResourceLocation, BookData> MANTLEJS_BOOKS = new HashMap<>();
    
    public static void initBook() {
        for (ResourceLocation r : BookDataJS.BOOKS.keySet()) {
            BookDataJS bookDataJS = BookDataJS.BOOKS.get(r);
            BookData bookData = BookLoader.registerBook(r, bookDataJS.appendIndex, bookDataJS.appendContentTable, bookDataJS.bookRepositories);
            MANTLEJS_BOOKS.put(r, bookData);
            MantleJS.LOGGER.info("Book registered: "+r.toString());
        }
        MantleJS.LOGGER.info("Book registration complete!");
    }

}
