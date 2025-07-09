package pelemenguin.mantlejs.event.book;

import dev.latvian.mods.kubejs.event.StartupEventJS;
import dev.latvian.mods.kubejs.typings.Info;
import pelemenguin.mantlejs.content.book.transformer.TransformerBuilder;

public class TransformerRegistryEventJS extends StartupEventJS {
    
    @Info("Creates a new book transformer. Accepts an `id` for the transformer.\n\n")
    public TransformerBuilder create(String id) {
        return new TransformerBuilder(id);
    }
    
}
