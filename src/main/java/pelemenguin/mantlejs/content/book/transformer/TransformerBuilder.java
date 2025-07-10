package pelemenguin.mantlejs.content.book.transformer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import pelemenguin.mantlejs.content.book.transformer.data.BookDataJS;

public class TransformerBuilder {

    public static final Map<String, TransformerBuilder> TRANSFORMER_BUILDERS = new HashMap<>();

    private String id;
    protected Consumer<BookDataJS> transformer;

    public TransformerBuilder(String id) {
        this.id = id;
        TRANSFORMER_BUILDERS.put(this.id, this);
    }
    
    public TransformerBuilder transform(Consumer<BookDataJS> transformer) {
        this.transformer = transformer;
        return this;
    }

}
