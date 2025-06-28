package pelemenguin.mantlejs.event;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import pelemenguin.mantlejs.event.book.BookRegisterEventJS;

public interface MantleJSEventGroup {
    
    EventGroup GROUP = EventGroup.of("MantleJSEvents");

    EventHandler BOOK_REGISTRY = GROUP.startup("bookRegistry", () -> BookRegisterEventJS.class);

}
