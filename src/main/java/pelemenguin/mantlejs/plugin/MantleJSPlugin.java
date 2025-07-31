package pelemenguin.mantlejs.plugin;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import pelemenguin.mantlejs.content.book.BookPageInterface;
import pelemenguin.mantlejs.content.book.element.BookElementInterface;
import pelemenguin.mantlejs.content.book.transformer.BuiltinTransformer;
import pelemenguin.mantlejs.content.book.util.BookScreenInterface;
import pelemenguin.mantlejs.content.book.util.TextComponentDataInterface;
import pelemenguin.mantlejs.content.book.util.TextDataInterface;
import pelemenguin.mantlejs.event.MantleJSEventGroup;
import pelemenguin.mantlejs.event.book.BookRegistryHandler;
import pelemenguin.mantlejs.item.book.BookItemBuilder;
import slimeknights.mantle.client.book.data.element.ImageData;

public class MantleJSPlugin extends KubeJSPlugin {

    @Override
    public void init() {
        RegistryInfo.ITEM.addType("mantle:book", BookItemBuilder.class, BookItemBuilder::new);
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
        event.add("BookTextComponentData", TextComponentDataInterface.class);
        event.add("BookScreen", BookScreenInterface.class);
        event.add("BookElement", BookElementInterface.class);
        event.add("ImageData", ImageData.class);
    }

    @Override
    public void onServerReload() {
        BookRegistryHandler.init();
    }

}
