package pelemenguin.mantlejs.event;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import pelemenguin.mantlejs.event.book.BookRegisterEventJS;
import pelemenguin.mantlejs.event.book.TransformerRegistryEventJS;

public interface MantleJSEventGroup {
    
    EventGroup GROUP = EventGroup.of("MantleJSEvents");

    EventHandler BOOK_REGISTRY = GROUP.startup("bookRegistry", () -> BookRegisterEventJS.class);
    EventHandler TRANSFORMER_REGISTRY = GROUP.startup("transformerRegistry", () -> TransformerRegistryEventJS.class);

}
