package pelemenguin.mantlejs.event.book;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import pelemenguin.mantlejs.content.book.BookInitialization;
import pelemenguin.mantlejs.content.book.transformer.TransformerInitialization;
import pelemenguin.mantlejs.event.MantleJSEventGroup;

public class BookRegistryHandler {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        TransformerRegistryEventJS transformerRegister = new TransformerRegistryEventJS();
        BookRegisterEventJS bookRegister = new BookRegisterEventJS();
        MantleJSEventGroup.TRANSFORMER_REGISTRY.post(transformerRegister);
        MantleJSEventGroup.BOOK_REGISTRY.post(bookRegister);
        TransformerInitialization.initTransformer();
        BookInitialization.initBook();
    }
}