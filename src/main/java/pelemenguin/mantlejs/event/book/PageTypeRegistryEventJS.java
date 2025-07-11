package pelemenguin.mantlejs.event.book;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.typings.Info;
import pelemenguin.mantlejs.content.book.page.PageTypeBuilder;

public class PageTypeRegistryEventJS extends EventJS {

    @Info("Creates a new page type builder. Accepts an `id` for the page type.")
    public PageTypeBuilder create(String id) {
        return new PageTypeBuilder(id);
    }

}
