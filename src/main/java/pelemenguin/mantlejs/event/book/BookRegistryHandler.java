package pelemenguin.mantlejs.event.book;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import pelemenguin.mantlejs.MantleJS;
import pelemenguin.mantlejs.content.book.BookInitialization;
import pelemenguin.mantlejs.content.book.transformer.TransformerInitialization;
import pelemenguin.mantlejs.event.MantleJSEventGroup;

@ParametersAreNonnullByDefault
public class BookRegistryHandler implements ResourceManagerReloadListener {
    public static void init() {

        TransformerRegistryEventJS transformerRegister = new TransformerRegistryEventJS();
        BookRegisterEventJS bookRegister = new BookRegisterEventJS();
        MantleJSEventGroup.TRANSFORMER_REGISTRY.post(transformerRegister);
        MantleJSEventGroup.BOOK_REGISTRY.post(bookRegister);
        TransformerInitialization.initTransformer();
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