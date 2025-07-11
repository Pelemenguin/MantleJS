package pelemenguin.mantlejs.plugin;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import pelemenguin.mantlejs.content.book.BookPageInterface;
import pelemenguin.mantlejs.content.book.element.BookElementInterface;
import pelemenguin.mantlejs.content.book.transformer.BuiltinTransformer;
import pelemenguin.mantlejs.content.book.util.BookScreenInterface;
import pelemenguin.mantlejs.content.book.util.TextDataInterface;
import pelemenguin.mantlejs.event.MantleJSEventGroup;
import pelemenguin.mantlejs.item.book.BookItemBuilder;

public class MantleJSPlugin extends KubeJSPlugin {

    @Override
    public void init() {
        RegistryInfo.ITEM.addType("tconstruct:book", BookItemBuilder.class, BookItemBuilder::new);
    }
    
    @Override
    public void registerEvents() {
        MantleJSEventGroup.GROUP.register();
    }

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("BuiltinTransformer", BuiltinTransformer.class);
        event.add("BookPage", BookPageInterface.class);
        event.add("BookTextData", TextDataInterface.class);
        event.add("BookScreen", BookScreenInterface.class);
        event.add("BookElement", BookElementInterface.class);
   }

}
