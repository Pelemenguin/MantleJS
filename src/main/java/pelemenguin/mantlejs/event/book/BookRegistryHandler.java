package pelemenguin.mantlejs.event.book;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import pelemenguin.mantlejs.content.book.BookInitialization;
import pelemenguin.mantlejs.content.book.page.PageTypeInitialization;
import pelemenguin.mantlejs.content.book.transformer.TransformerInitialization;
import pelemenguin.mantlejs.event.MantleJSEventGroup;

@ParametersAreNonnullByDefault
@EventBusSubscriber
public class BookRegistryHandler {
    public static void init() {

        PageTypeRegistryEventJS pageTypeRegistrer = new PageTypeRegistryEventJS();
        MantleJSEventGroup.PAGE_TYPE_REGISTRY.post(pageTypeRegistrer);
        PageTypeInitialization.initPageTypes();
        TransformerRegistryEventJS transformerRegister = new TransformerRegistryEventJS();
        MantleJSEventGroup.TRANSFORMER_REGISTRY.post(transformerRegister);
        TransformerInitialization.initTransformer();
        BookRegisterEventJS bookRegister = new BookRegisterEventJS();
        MantleJSEventGroup.BOOK_REGISTRY.post(bookRegister);
        BookInitialization.initBook();

    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        init();
    }

}