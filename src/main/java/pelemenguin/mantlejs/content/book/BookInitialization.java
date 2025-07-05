package pelemenguin.mantlejs.content.book;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.resources.ResourceLocation;
import pelemenguin.mantlejs.MantleJS;
import slimeknights.mantle.client.book.BookLoader;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.repository.BookRepository;
import slimeknights.mantle.client.book.transformer.BookTransformer;

public class BookInitialization extends BookData {

    public static final Map<ResourceLocation, BookData> MANTLEJS_BOOKS = new HashMap<>();
    
    public static void initBook() {
        for (String r : BookBuilder.BOOK_BUILDERS.keySet()) {
            ResourceLocation loc = ResourceLocation.parse(r);
            BookBuilder bookDataJS = BookBuilder.BOOK_BUILDERS.get(r);
            BookData bookData = BookLoader.registerBook(loc, bookDataJS.appendIndex, bookDataJS.appendContentTable, bookDataJS.bookRepositories.toArray(new BookRepository[0]));
            for (BookTransformer t : bookDataJS.bookTransformers) {
                bookData.addTransformer(t);
            }
            MANTLEJS_BOOKS.put(loc, bookData);
            MantleJS.LOGGER.info("Book registered: "+r.toString());
        }
        MantleJS.LOGGER.info("Book registration complete!");
    }

}
