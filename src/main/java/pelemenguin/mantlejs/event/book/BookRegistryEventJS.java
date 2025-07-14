package pelemenguin.mantlejs.event.book;

import dev.latvian.mods.kubejs.event.StartupEventJS;
import dev.latvian.mods.kubejs.typings.Info;
import pelemenguin.mantlejs.content.book.BookBuilder;

public class BookRegistryEventJS extends StartupEventJS {
    
    @Info("Creates a new book builder. Accepts an `id` for the book.")
    public BookBuilder create(String id) {
        return new BookBuilder(id);
    }

}
