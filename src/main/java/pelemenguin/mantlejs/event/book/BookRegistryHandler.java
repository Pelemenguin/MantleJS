package pelemenguin.mantlejs.event.book;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import pelemenguin.mantlejs.content.book.BookInitialization;
import pelemenguin.mantlejs.event.MantleJSEventGroup;

public class BookRegistryHandler {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        BookRegisterEventJS eventJs = new BookRegisterEventJS();
        MantleJSEventGroup.BOOK_REGISTRY.post(eventJs);
        BookInitialization.initBook();
    }
}