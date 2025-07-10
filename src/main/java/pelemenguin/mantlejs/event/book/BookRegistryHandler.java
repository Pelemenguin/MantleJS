package pelemenguin.mantlejs.event.book;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import pelemenguin.mantlejs.MantleJS;
import pelemenguin.mantlejs.content.book.BookInitialization;
import pelemenguin.mantlejs.content.book.data.PageType;
import pelemenguin.mantlejs.content.book.transformer.TransformerInitialization;
import pelemenguin.mantlejs.event.MantleJSEventGroup;
import slimeknights.mantle.client.book.data.content.ContentBlank;
import slimeknights.mantle.client.book.data.content.ContentBlockInteraction;
import slimeknights.mantle.client.book.data.content.ContentCrafting;
import slimeknights.mantle.client.book.data.content.ContentImage;
import slimeknights.mantle.client.book.data.content.ContentImageText;
import slimeknights.mantle.client.book.data.content.ContentIndex;
import slimeknights.mantle.client.book.data.content.ContentPadding;
import slimeknights.mantle.client.book.data.content.ContentShowcase;
import slimeknights.mantle.client.book.data.content.ContentSmelting;
import slimeknights.mantle.client.book.data.content.ContentSmithing;
import slimeknights.mantle.client.book.data.content.ContentStructure;
import slimeknights.mantle.client.book.data.content.ContentText;
import slimeknights.mantle.client.book.data.content.ContentTextImage;
import slimeknights.mantle.client.book.data.content.ContentTextLeftImage;
import slimeknights.mantle.client.book.data.content.ContentTextRightImage;

@ParametersAreNonnullByDefault
public class BookRegistryHandler implements ResourceManagerReloadListener {
    public static void init() {

        // Built-in Page Types
        PageType.addPageType(ContentBlank.ID, ContentBlank.class);
        PageType.addPageType(ContentBlockInteraction.ID, ContentBlockInteraction.class);
        PageType.addPageType(ContentCrafting.ID, ContentCrafting.class);
        PageType.addPageType(ContentImage.ID, ContentImage.class);
        PageType.addPageType(ContentImageText.ID, ContentImageText.class);
        PageType.addPageType(ContentIndex.ID, ContentIndex.class);
        PageType.addPageType(ContentPadding.ID, ContentPadding.class);
        PageType.addPageType(ContentPadding.LEFT_ID, ContentPadding.ContentLeftPadding.class);
        PageType.addPageType(ContentPadding.RIGHT_ID, ContentPadding.ContentRightPadding.class);
        PageType.addPageType(ContentShowcase.ID, ContentShowcase.class);
        PageType.addPageType(ContentSmelting.ID, ContentSmelting.class);
        PageType.addPageType(ContentSmithing.ID, ContentSmithing.class);
        PageType.addPageType(ContentStructure.ID, ContentStructure.class);
        PageType.addPageType(ContentText.ID, ContentText.class);
        PageType.addPageType(ContentTextImage.ID, ContentTextImage.class);
        PageType.addPageType(ContentTextLeftImage.ID, ContentTextLeftImage.class);
        PageType.addPageType(ContentTextRightImage.ID, ContentTextRightImage.class);

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