package pelemenguin.mantlejs.content.book;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import pelemenguin.mantlejs.MantleJS;
import slimeknights.mantle.client.book.repository.BookRepository;
import slimeknights.mantle.client.book.repository.FileRepository;

public class BookBuilder {

    public ResourceLocation id;
    public BookRepository[] bookRepositories;
    public boolean appendIndex = true;
    public boolean appendContentTable = true;
    
    public BookBuilder(String id) {
        this.id = MantleJS.createKubeJSLocation(id);
    }

    /**
     * Set the book's repositories' paths
     */
    @Info("Set the book's repositories' paths.\n\nFor example, If you have book contents under `kubejs/assets/kubejs/book/test/`, then you should use `.setBookRepositories(\"kubejs:book/test\")`")
    public BookBuilder setBookRepositories(ResourceLocation... repositories) {
        BookRepository[] bookRepositories = new BookRepository[repositories.length];
        for (int r = 0; r < repositories.length; r++) {
            bookRepositories[r] = new FileRepository(repositories[r]);
        }
        this.bookRepositories = bookRepositories;
        return this;
    }

    /**
     * Chaining this method, the book will not append an index.
     */
    @Info("Disables index.")
    public BookBuilder noAppendIndex() {
        this.appendIndex = false;
        return this;
    }

    /**
     * Chaining this method, the book will not append a content table.
     */
    @Info("Disables content table.")
    public BookBuilder noContentTable() {
        this.appendContentTable = false;
        return this;
    }

    /**
     * Set the id.
     */
    @Info("Set the id of the book. Prefix `kubejs` will be automatically added.\n\nThis is used when you want to continue modifying after `build()`. After modification, call another `build()` to create a new book.")
    public BookBuilder id(String newId) {
        this.id = MantleJS.createKubeJSLocation(newId);
        return this;
    }

    /**
     * Build the book.
     */
    @Info("Build the book.\n\nAfter building the book, you can still continue modifying and create more books.\nIf you want to continue modifying, you should chain a `id()` after this to give it a different id.")
    public BookBuilder build() {
        if (this.bookRepositories == null) {
            this.bookRepositories = new BookRepository[0];
        }
        MantleJS.LOGGER.info("A `build()` is called! id: ", this.id.toString());
        new BookDataJS(this);
        return this;
    }

}
