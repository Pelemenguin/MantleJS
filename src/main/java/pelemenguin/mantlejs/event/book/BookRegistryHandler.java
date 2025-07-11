package pelemenguin.mantlejs.event.book;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import pelemenguin.mantlejs.MantleJS;
import pelemenguin.mantlejs.content.book.BookInitialization;
import pelemenguin.mantlejs.content.book.page.PageTypeInitialization;
import pelemenguin.mantlejs.content.book.transformer.TransformerInitialization;
import pelemenguin.mantlejs.event.MantleJSEventGroup;

@ParametersAreNonnullByDefault
public class BookRegistryHandler implements ResourceManagerReloadListener {
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

    @Override
    public void onResourceManagerReload(ResourceManager p_10758_) {
        MantleJS.LOGGER.info("MantleJS reloading...");
        init();
    }
}