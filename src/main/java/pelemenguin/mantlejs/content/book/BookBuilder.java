package pelemenguin.mantlejs.content.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import pelemenguin.mantlejs.content.book.transformer.BuiltinTransformer;
import slimeknights.mantle.client.book.repository.BookRepository;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.mantle.client.book.transformer.BookTransformer;

public class BookBuilder {

    public static final Map<String, BookBuilder> BOOK_BUILDERS = new HashMap<>();

    public String id;
    public List<BookRepository> bookRepositories = new ArrayList<BookRepository>();
    public List<BookTransformer> bookTransformers = new ArrayList<BookTransformer>();
    public boolean appendIndex = true;
    public boolean appendContentTable = true;
    
    public BookBuilder(String id) {
        this.id = KubeJS.appendModId(id);
        BOOK_BUILDERS.put(this.id, this);
    }

    /**
     * Add a book repository
     */
    @Info("Add a book repository.\n\nFor example, If you have book contents under `kubejs/assets/kubejs/book/test/`, then you should use `.setBookRepositories(\"kubejs:book/test\")`")
    public BookBuilder addBookRepository(ResourceLocation repository) {
        this.bookRepositories.add(new FileRepository(repository));
        return this;
    }

    /**
     * Add a book transformer
     */
    @Info("Add a book transformer.\n\nYou need to `Java.loadClass()` yourself if you want to use this method. Otherwise use `addTransformer(MantleTransformer)`")
    public BookBuilder addTransformer(BookTransformer transformer) {
        this.bookTransformers.add(transformer);
        return this;
    }
    @Info("Add a book transformer")
    public BookBuilder addTransformer(BuiltinTransformer transformer) {
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
    @Info("Reset the id of the book.")
    public BookBuilder id(String newId) {
        // this.id = MantleJS.createKubeJSLocation(newId);
        this.id = KubeJS.appendModId(newId);
        return this;
    }

}
