package pelemenguin.mantlejs.content.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import pelemenguin.mantlejs.content.book.transformer.MantleJSTransformer;
import slimeknights.mantle.client.book.repository.BookRepository;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.mantle.client.book.transformer.BookTransformer;

public class BookBuilder {

    public static final Map<String, BookBuilder> BOOK_BUILDERS = new HashMap<>();

    public String id;
    public List<BookRepository> bookRepositories = new ArrayList<BookRepository>();
    public List<BookTransformer> bookTransformers = new ArrayList<BookTransformer>();
    public List<String> items = new ArrayList<String>();
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
    @Info("Add a book transformer created in `MantleJSEvents.transformerRegistry`")
    public BookBuilder addTransformer(String transformerId) {
        this.bookTransformers.add(new MantleJSTransformer(ResourceLocation.parse(KubeJS.appendModId(transformerId))));
        return this;
    }

    @Info("Add a book transformer from a Java class.\n\nThis should be used together with `Java.loadClass()` for advanced customization.")
    public BookBuilder addJavaTransformer(BookTransformer bookTransformer) {
        this.bookTransformers.add(bookTransformer);
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

}
