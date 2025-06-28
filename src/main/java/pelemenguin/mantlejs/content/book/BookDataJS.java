package pelemenguin.mantlejs.content.book;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.resources.ResourceLocation;
import pelemenguin.mantlejs.MantleJS;
import slimeknights.mantle.client.book.repository.BookRepository;

public class BookDataJS{

    public static final Map<ResourceLocation, BookDataJS> BOOKS = new HashMap<>();
    
    public ResourceLocation id;
    public BookRepository[] bookRepositories;
    public boolean appendIndex;
    public boolean appendContentTable;

    public BookDataJS(BookBuilder builder) {
        this.id = builder.id;
        this.bookRepositories = builder.bookRepositories;
        this.appendIndex = builder.appendIndex;
        this.appendContentTable = builder.appendContentTable;
        BOOKS.put(this.id, this);
        MantleJS.LOGGER.info("A new book is built: "+this.id.toString());
    }

}
