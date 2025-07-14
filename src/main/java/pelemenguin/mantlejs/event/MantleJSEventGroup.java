package pelemenguin.mantlejs.event;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import pelemenguin.mantlejs.event.book.BookRegistryEventJS;
import pelemenguin.mantlejs.event.book.PageTypeRegistryEventJS;
import pelemenguin.mantlejs.event.book.TransformerRegistryEventJS;

public interface MantleJSEventGroup {
    
    EventGroup GROUP = EventGroup.of("MantleJSEvents");

    EventHandler BOOK_REGISTRY = GROUP.startup("bookRegistry", () -> BookRegistryEventJS.class);
    EventHandler TRANSFORMER_REGISTRY = GROUP.startup("transformerRegistry", () -> TransformerRegistryEventJS.class);
    EventHandler PAGE_TYPE_REGISTRY = GROUP.startup("pageTypeRegistry", () -> PageTypeRegistryEventJS.class);

}
